#!/usr/bin/python
# -*- coding:UTF-8 -*-
from random import randint
from utils import gcd, reverse_element_in_ring
 
class MerkleHellman:
    def __init__(self, hypervector = None, multiplier = None, modulus = None):
        '''
        Реализира кодирането на Merkle-Hellman,
        използващо алгоритъма за раницата.
        Конструкторът приема хипернарастващ вектор,
        число, по което се умножават елементите му
        и модул(последните две се използват за генериране на публичния ключ).
        '''
 
        # Ако тези стойности не са дадени, произволни ще бъдат автоматично генерирани.
        if not multiplier:
            for x in range(modulus):
                if gcd(x, modulus) == 1:
                    self.multiplier = x
                    break
        else:
            self.multiplier = multiplier
 
        self.private_key = hypervector
        sum_vector = sum(hypervector)
        self.modulus = modulus or randint(sum_vector + 1, sum_vector * 2)
        if self.modulus < sum_vector:
            print('''Внимание! Кодирането се извършва по модул,
по-малък от сумата на числата във хипернарастващия вектор!
Това **ще** доведе до грешки и е силно непрепоръчително!''')
 
        self.public_key = [(element * multiplier) % modulus for element in hypervector]
 
    def __str__(self):
        return '''Шифър на Merkle-Hellman, с частен ключ:
%s %s %s''' % (self.private_key, self.multiplier, self.modulus)
 
    def encrypt(self, plaintext):
        code = 0
        for x in range(len(self.public_key)):
            if 2**x > plaintext:
                break
            if plaintext & (2**x):
                code += self.public_key[x]
        return code
 
    def decrypt(self, code):
        plaintext = 0
        reverse_multiplier = reverse_element_in_ring(self.multiplier, self.modulus)
        code = (code * reverse_multiplier) % self.modulus
        for x in reversed(list(range(len(self.private_key)))):
            if code >= self.private_key[x]:
                plaintext += 2**x
                code -= self.private_key[x]
        return plaintext
 
def preencode_word(word):
    '''
    Превръща една дума от букви в числа. Буква по буква.
    '''
    encoding = {
    'A':'00011', 'B':'00101', 'C':'00110', 'D':'00111', 'E':'01001',
    'F':'01010', 'G':'01011', 'H':'01100', 'I':'01101', 'J':'01110',
    'K':'01111', 'L':'10001', 'M':'10010', 'N':'10011', 'O':'10100',
    'P':'10101', 'Q':'10110', 'R':'10111', 'S':'11000', 'T':'11001',
    'U':'11010', 'V':'11011', 'W':'11100', 'X':'11101', 'Y':'11110',
    'Z':'11111'
    }
 
    return [int(encoding[letter], 2) for letter in word]
 
if __name__ == "__main__":
    vector = [2, 3, 7, 13, 27, 53, 106, 213, 425, 851]
    modulus = 1529
    multiplier = 64
 
    mh = MerkleHellman(vector, multiplier, modulus)
 
    word = preencode_word("CRYPTOGRAPHY")
    
    print(word)
    coded_word = [mh.encrypt(letter) for letter in word]
    decoded_word = [mh.decrypt(letter) for letter in coded_word]
    print(word, coded_word, decoded_word)
 
    if word == decoded_word:
        print("Съобщението беше успешно криптирано и декриптирано.")
    else:
        print("Има грешка в алгоритъма.")
 
    word_digraph = [word[x+1] + word[x]*32 for x in range(0, len(word),2)]
    coded_word_digraph = [mh.encrypt(letter) for letter in word_digraph]
    decoded_word_digraph = [mh.decrypt(letter) for letter in coded_word_digraph]
    print(word_digraph, coded_word_digraph, decoded_word_digraph)
 
    if word_digraph == decoded_word_digraph:
        print("Съобщението беше успешно криптирано и декриптирано в диграфи.")
    else:
        print("Има грешка в алгоритъма.")
