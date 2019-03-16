#!/usr/bin/python
# -*- coding:UTF-8 -*-
import re
 
class Playfair:
    def __init__(self, cypher = ""):
        '''
        TODO: Сложи варианта, в който Q се изпуска.
        '''
        # J се изпуска
        self.alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"
        self.set_cypher(cypher)
 
    def __str__(self):
        key = "\n".join(" ".join(self.table[(x,y)] for y in range(0, 5))
            for x in range(0,5))
        return '''Playfair Cypher.
Key:
%s''' % key
 
    def set_cypher(self, keyword):
        x = 0
        self.table = {}
        self.cypher = {}
        for letter in keyword:
            if letter == " " or letter in self.cypher:
                continue
            self.table[(x/5,x%5)] = letter
            self.cypher[letter] = (x/5,x%5)
            x += 1
        for letter in self.alphabet:
            if not letter in keyword:
                self.table[(x/5,x%5)] = letter
                self.cypher[letter] = (x/5,x%5)
                x += 1
 
    def get_letter(self, x, y):
        return self.table[(x%5, y%5)]
 
    def decrypt(self, message):
        ans = ""
        for x in range(0,len(message),2):
            ca = self.cypher[message[x]]
            cb = self.cypher[message[x+1]]
            if ca[0] == cb[0]:
                ans += self.get_letter(ca[0], ca[1]-1) + self.get_letter(cb[0], cb[1]-1)
            elif ca[1] == cb[1]:
                ans += self.get_letter(ca[0]-1, ca[1]) + self.get_letter(cb[0]-1, cb[1])
            else:
                ans +=  self.get_letter(ca[0], cb[1]) + self.get_letter(cb[0], ca[1])
        return ans
 
    def encrypt(self, message):
        def split_repeating_letters(m, x):
            '''
            Добавя x-ове между повтарящи се букви на съобщението m.
            '''
            if len(m) % 2 == 1:
                m = m + x
            for i in range(0, len(m), 2):
                if m[i] == m[i+1]:
                    return split_repeating_letters(m[:i+1] + x + m[i+1:], x)
            return m
 
        ans = ""
        message = split_repeating_letters(message, "X")
        for x in range(0,len(message),2):
            ca = self.cypher[message[x]]
            cb = self.cypher[message[x+1]]
            if ca[0] == cb[0]:
                ans += self.get_letter(ca[0], ca[1]+1) + self.get_letter(cb[0], cb[1]+1)
            elif ca[1] == cb[1]:
                ans += self.get_letter(ca[0]+1, ca[1]) + self.get_letter(cb[0]+1, cb[1])
            else:
                ans +=  self.get_letter(ca[0], cb[1]) + self.get_letter(cb[0], ca[1])
        return ans
 
pf = Playfair("HOW MANY ELKS")

# Задача 1.1
print pf.encrypt("APIGINACAGEONANTIBIOTICS")
print pf.decrypt(pf.encrypt("APIGINACAGEONANTIBIOTICS"))
# Задача 1.2
print pf.decrypt("BRUTVNGAKWUGOGKPAWPKQGMBVBUGWC")
