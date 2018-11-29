#!/bin/env python3
# -*- coding: utf-8 -*-


class Node():
    '''
    Клас за възлите
    '''

    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None


class AVLTree():
    '''
    Клас за цялото АВЛ дърво
    '''

    def __init__(self, *args):
        '''
        Инициализация на празно дърво
        '''
        self.node = None
        self.height = -1
        self.balance = 0

        if len(args) == 1:
            '''
            Ако дървото е инициализирано с множество елементи, вмъкваме всеки
            един от тях.
            '''
            for i in args[0]:
                self.insert(i)

    def height(self):
        '''
        Функция, която връща височина на дървото
        '''
        if self.node:
            return self.node.height
        else:
            return 0

    def is_leaf(self):
        '''
        Функция за проверка дали е листо
        '''
        return (self.height == 0)

    def insert(self, key):
        '''
        Функция за вмъкване на нови възли
        '''
        tree = self.node
        new_node = Node(key)

        if tree is None:
            '''
            Ако дървото е празно, създаваме ново под дърво
            '''
            self.node = new_node
            self.node.left = AVLTree()
            self.node.right = AVLTree()

        elif key < tree.key:
            '''
            Ако този ключ е по-малък от текущия в дървото, го вмъкваме в ляво
            '''
            self.node.left.insert(key)

        elif key > tree.key:
            '''
            Ако този ключ е по-голям от текущия в дървото, го вмъкваме в дясно
            '''
            self.node.right.insert(key)

        else:
            '''
            Този елемент съществува, няма нужда да го вмъкваме.
            '''
            return

        '''
        След като приключим операцията по вмъкването ребалансираме дървото
        '''
        self.rebalance()

    def rebalance(self):
        '''
        Функция за ребалансиране на дърво или поддърво
        '''
        self.update_heights(False)
        self.update_balances(False)
        while self.balance < -1 or self.balance > 1:
            '''
            Докато дървото небалансирано проверяваме под дърветата и правим
            съответните ротации, след което обновяваме височините.
            '''
            if self.balance > 1:
                if self.node.left.balance < 0:
                    self.node.left.rotate(right=False)
                    self.update()
                self.rotate()
                self.update()

            if self.balance < -1:
                if self.node.right.balance > 0:
                    self.node.right.rotate()
                    self.update()
                self.rotate(right=False)
                self.update()

    def rotate(self, right=True):
        '''
        Функция за ротация на ключ спрямо позиция по подразбиране надясно.
        '''
        if right:
            '''
            Ротация надясно
            '''
            A = self.node
            B = self.node.left.node
            T = B.right.node
            self.node = B
            B.right.node = A
            A.left.node = T
        else:
            '''
            Ротация наляво
            '''
            A = self.node
            B = self.node.right.node
            T = B.left.node
            self.node = B
            B.left.node = A
            A.right.node = T

    def update_heights(self, recurse=True):
        '''
        Функция за обновяване на височината на дървото
        '''
        if self.node is not None:
            if recurse:
                '''
                Ако го извикваме рекурсивно, проверяваме за поддърва и
                съответно ги обновяваме и тях.
                '''
                if self.node.left is not None:
                    self.node.left.update_heights()
                if self.node.right is not None:
                    self.node.right.update_heights()

            self.height = max(self.node.left.height,
                              self.node.right.height) + 1
        else:
            self.height = -1

    def update_balances(self, recurse=True):
        '''
        Функция за обновяване на баланса на дървото
        '''
        if self.node is not None:
            if recurse:
                '''
                Ако го извикваме рекурсивно, проверяваме за поддърва и
                съответно ги обновяваме и тях.
                '''
                if self.node.left is not None:
                    self.node.left.update_balances()
                if self.node.right is not None:
                    self.node.right.update_balances()
            '''
            Баланса на дървото е равен на височината на под дървата от ляво
            и от дясно.
            '''
            self.balance = self.node.left.height - self.node.right.height
        else:
            self.balance = 0

    def update(self):
        '''
        Функция за обновяване на индексите на дървото - височина и баланс.
        Тази функция е просто опаковка (wrapper).
        '''
        self.update_heights()
        self.update_balances()

    def delete(self, key):
        '''
        Функция за изтриване на елемент от дървото.
        Ако открием, че елемента е листо просто го трием, при условие, че има
        само едно поддърво го разменяме, а когато има две поддървета откриваме
        подходящ наследник, усвояваме стойността на наследника в текущя възел и
        трием наслендика.
        '''
        if self.node is not None:
            if self.node.key == key:
                if (self.node.left.node is None and
                        self.node.right.node is None):
                    '''
                    Ако е листо просто го трием
                    '''
                    self.node = None
                elif self.node.left.node is None:
                    self.node = self.node.right.node
                elif self.node.right.node is None:
                    self.node = self.node.left.node
                else:
                    replacement = self.logical_successor(self.node)
                    if replacement is not None:
                        self.node.key = replacement.key
                        self.node.right.delete(replacement.key)
                '''
                Задъжително ребалансираме след тази операция
                '''
                self.rebalance()
                return
            elif key < self.node.key:
                self.node.left.delete(key)
            elif key > self.node.key:
                self.node.right.delete(key)
            self.rebalance()
        else:
            return

    def logical_predecessor(self, node):
        '''
        Намиране на най-големият възел наляво
        '''
        node = node.left.node
        if node is not None:
            while node.right is not None:
                if node.right.node is None:
                    return node
                else:
                    node = node.right.node
        return node

    def logical_successor(self, node):
        '''
        Намиране на най-малкият възел надясно
        '''
        node = node.right.node
        if node is not None:
            while node.left is not None:
                if node.left.node is None:
                    return node
                else:
                    node = node.left.node
        return node

    def check_balanced(self):
        '''
        Функция за проверка дали дървото е балансирано
        '''
        if self is None or self.node is None:
            '''
            Несъществуващо/празно дърво е балансирано
            '''
            return True
        '''
        Преди да проверим баланса ще обновим индексите за баланс и височина
        '''
        self.update()
        '''
        Ако абсолютната стойност на баланса на дървото е < 2 и
        рекурсивно поддърветата са също балансирани връщаме True.
        '''
        return ((abs(self.balance) < 2) and
                self.node.left.check_balanced() and
                self.node.right.check_balanced())

    def traverse(self):
        '''
        Траверсиране на дърво и връщане на подреден списък
        '''
        if self.node is None:
            return []

        inlist = []
        l = self.node.left.traverse()
        '''
        Извикваме рекурсивно за елементите от ляво
        '''
        for i in l:
            inlist.append(i)

        inlist.append(self.node.key)

        l = self.node.right.traverse()
        '''
        Извикваме рекурсивно за елементите от дясно
        '''
        for i in l:
            inlist.append(i)

        return inlist

    def display_tree(self, level=0):
        '''
        Функция за представяне на дървото на конзолата
        '''
        if(self.node is not None):
            if self.node.right is not None:
                '''
                Ако има дясно поддърво рекурсивно го принтираме на конзолата.
                '''
                self.node.right.display_tree(level + 1)
                if not self.is_leaf():
                    print('\t' * level, '    /')
            '''
            Отпечатване на съответния възел във следния формат: "(K) [H:B]"
            Където съответно имаме: K - стойност, H - височина, B - баланс
            '''
            print(('\t' * level), '({}) [{}:{}]'.format(self.node.key,
                                                        str(self.height),
                                                        str(self.balance)))

            if self.node.left is not None:
                '''
                Ако има ляво поддърво рекурсивно го принтираме на конзолата.
                '''
                if not self.is_leaf():
                    print('\t' * level, '    \\')
                self.node.left.display_tree(level + 1)


if __name__ == "__main__":
    '''
    При извикване на програмния код самостоятелно, без използването на
    класовете ще изпълним следния примерен код за да
    демонстрираме функционалността.
    '''
    print ()
    a = AVLTree()
    print ("----- Вмъкване на елементи -------")
    elementi = [23, 15, 22, 1, 0, 14, 7, 15, 3]
    for i in elementi:
        a.insert(i)
    print ("Входящи елементи: ", elementi)
    a.display_tree()
    print()

    print ("----- Изтриване на елементи -------")
    print ("изтриване 15 ...       ", 15)
    '''
    Тук ще се наложи цялостно ребалансиране на дървото.
    '''
    a.delete(15)
    print ("изтриване 1 ...       ", 1)
    a.delete(1)
    a.display_tree()
    print ()
    print ("Траверсиране по ред:", a.traverse())
    print ()
