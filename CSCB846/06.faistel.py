# -*- coding:UTF-8 -*-
from numpy import *
class Feistel:
    def __init__(self, len, h, f):
        # Размерът на елементите, които ще се криптират
        self.len = len
        # Броя стъпки в криптирането
        self.h = h
        # Криптиращата функция
        self.f = f
 
    def split_message(self, message):
        '''
        Прави от първоначалния низ лява и дясна част от числа.
        '''
        message = [ord(letter) - ord('0') for letter in message]
        half = len(message)//2
        left, right = message[:half], message[half:]
        #print("split_message() message: {} left: {} right: {}".format(message, left, right))
        return tuple(left), tuple(right)
 
    def assemble_message(self, left, right):
        '''
        Сглобява лява и дясна част от числа в низ.
        '''
        left = [chr(num + ord('0')) for num in left]
        right = [chr(num + ord('0')) for num in right]
        message = "".join(left + right)
        #print("assemble_message() message: {} left: {} right: {}".format(message, left, right))
        return message
 
    def hash(self, left, right, n):
        '''
        Изчислява една стъпка от алгоритъма на Feistel.
        '''
        print("hash() called left: {} right {}".format(left, right))
        def XOR(la, lb):
            '''
            Приема 2 списъка и връща списък, с елементи XOR-натите
            съответни техни елементи.
            '''
            message = [la[x]^lb[x] for x in range(len(la))]
            # print("\txor() message: {} la: {} lb: {}".format(message, la, lb))
            return message
        def func(l, n):
            k = [self.f[n][l[x:x+self.len]] for x in range(0,len(l),self.len)]
            ttup = tuple(a for b in k for a in b )
            # print("\tfunc() l: {0} n: {1} k[{1}]: {2} ttup: {3}".format(l, n, k, ttup))
            return k, ttup
        k, right_next = func(right, n)
        ttup = tuple(XOR(left, right_next))
        #print("# Feistel Round [{0}]: L[{0}] = {1}, R[{0}] = {2}, K[{0}] = {3}".format(n, left, right, k))
        #print("## L[{0}] = R{1} = {2}".format(n+1, n, right))
        #print("## R[{0}] = L{1} XOR(F( R[{1}], K[{1}] ) = {2} XOR(F( {3}, {4} ))".format(n+1, n-1, left, right, k))
        print("hash() output right: {} left: {} k: {}".format(right, ttup, k))
        print()
        return right, ttup
 
    def encrypt(self, message):
        left, right = self.split_message(message)
        for x in range(0, self.h):
            left, right = self.hash(left, right, x)
        return self.assemble_message(left, right)
 
    def decrypt(self, message):
        left, right = self.split_message(message)
        for x in reversed(list(range(0, self.h))):
            right, left = self.hash(right, left, x)
        return self.assemble_message(left, right)
 
# Знам, знам - това е много дълго. Функцията е много дълга, какво да направя :)
feistel = Feistel(2,6, [
                        {(0,0) : (1,0),
                        (0,1) : (1,0),
                        (1,0) : (0,0),
                        (1,1) : (0,1)},
 
                        {(0,0) : (0,1),
                        (0,1) : (1,0),
                        (1,0) : (1,0),
                        (1,1) : (0,0)},
 
                        {(0,0) : (1,1),
                        (0,1) : (0,0),
                        (1,0) : (0,1),
                        (1,1) : (1,0)},
 
                        {(0,0) : (1,1),
                        (0,1) : (1,0),
                        (1,0) : (1,1),
                        (1,1) : (0,1)},
 
                        {(0,0) : (1,1),
                        (0,1) : (0,0),
                        (1,0) : (0,0),
                        (1,1) : (1,0)},
 
                        {(0,0) : (1,1),
                        (0,1) : (1,1),
                        (1,0) : (0,1),
                        (1,1) : (1,0)}]
        )

print('Original text: ')
text_orig = '0100'
print(text_orig)
print()

print('# Encrypting')
text_encrypted = feistel.encrypt(text_orig)
print(text_encrypted)
print()

print('# Decrypting')
text_decrypted = feistel.decrypt(text_encrypted)
print(text_decrypted)
print()
print()

def test(feistel):
    for x in range(0,16):
        l = ""
        for y in range(4):
            l += chr(ord('0') + x % 2)
            x /= 2
        if feistel.decrypt(feistel.encrypt(l)) != l:
            print("ERROR!")
        return False
    return True
