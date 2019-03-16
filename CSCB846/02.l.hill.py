# -*- coding:UTF-8 -*-
from numpy import *#array, dot
from numpy.linalg import *
from utils import gcd, reverse_element_in_ring
 
class Hill:
    def __init__(self, key):
        self.set_key(key)
 
    def inverse_matrix(self, mat):
 
        def remove(x,y):
            if(inv[x][y] == 0):
                #if(x<len(inv)-1):
                #    inv[x], inv[x+1] = inv[x+1].copy(), inv[x].copy()
                #print inv
                return
            GCD = gcd(inv[x][y], inv[y][y])
            LCM = inv[x][y]*inv[y][y]/GCD
            a = LCM / inv[x][y]
            b = LCM / inv[y][y]
            inv[x] = inv[x]*a - inv[y]*b
        inv = []
        for x in range(len(mat)):
            inv.append( array(mat[x] + [0] * x + [1] + [0]*(len(mat[x])-1 - x)))
        inv = array(inv)
 
        # Това е нужно, защото алгоритмите не работят добре с отрицателни числа
        # (а именно, GCD)
        def normalize(inv):
            tmp = []
            for row in inv:
                tmp.append(array([item % 26 for item in row]))
            return array(tmp)
 
        # Нулиране на елементите под главния диагонал
        for x in range(1,len(inv)):
            for y in range(0,x):
                remove(x,y)
 
        inv = normalize(inv)
 
        # Нулиране на елементите над главния диагонал
        for x in reversed(range(0,len(inv)-1)):
            for y in reversed(range(x+1,len(mat[0]),1)):
                remove(x,y)
 
        inv = normalize(inv)
 
        # Нормализиране на елементите в главния диагонал
        for x in range(len(inv)):
            inv[x] = inv[x] * reverse_element_in_ring(inv[x][x], 26)
 
        inv = normalize(inv)
        return array([line[3:] for line in inv])
 
    def set_key(self,key):
        self.key = array(key)
        self.invkey = self.inverse_matrix(key)
 
    def decrypt(self, message):
        nums = [int(ord(letter) - ord('A')) for letter in message]
        nums += [0,0]
        ans = ""
        for x in range(0, len(message), 3):
            word = array(nums[x:x+3])
            crypt = dot(self.invkey, word)
            ans += "".join(chr(ord('A') + letter % 26) for letter in crypt)
        return ans
 
    def encrypt(self, message):
        nums = [int(ord(letter) - ord('A')) for letter in message]
        nums += [0,0]
        ans = ""
        for x in range(0, len(message), 3):
            word = array(nums[x:x+3])
            crypt = dot(self.key, word)
            ans += "".join(chr(ord('A') + letter % 26) for letter in crypt)
        return ans
    def __str__(self):
        return '''L. Hill Cypher.
Key:
%s''' % self.key
 
def test():
    hill = Hill([[6,24,1],[13,16,10],[20,17,15]])
    print(hill.encrypt("ACT"))
    hill = Hill([[15, 9, 25],[0, 3, 5],[10, 25, 0]])
    print(hill)
    print(hill.decrypt("UZHBAY"))
    print(hill.decrypt(hill.encrypt("UZHBAY")))
 
key = [[5,2,2],[2,5,2],[2,2,5]]
hill = Hill(key)
print(hill.decrypt("QCPTKZ"))
