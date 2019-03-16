# -*- coding:UTF-8 -*-
# source: http://fmi.wikidot.com/krypthomework
 
def memoize(fun):
    '''
    Мемоизира функция.
    Този декоратор собственоръчно решава 1/3 от всички състезателни задачи въобще.
    '''
    memoized = {}
    def memo(*args, **kwargs):
        if not kwargs:
            key = args
        else:
            key = (args,str(kwargs))
        if not key in memoized:
            memoized[key] = fun(*args, **kwargs)
        return memoized[key]
    return memo
 
def gcd(a,b):
    '''
    Връща най-големия общ делител на A и B.
    '''
    while b:      
      a, b = b, a % b
    return a
 
def lcm(a,b):
    return a*b/gcd(a,b)
 
def egcd(a, b):
    '''
    Разширения алгоритъм на Евклид.
    Намира цели числа U и V такива, че U*A + V*B = 1, както и самото gcd(A,B)
    ''' 
    u, u1 = 1, 0
    v, v1 = 0, 1
    g, g1 = a, b
    while g1:
        # За училите C++ - това не е коментар, а целочислено деление
        q = g // g1
        u, u1 = u1, u - q * u1
        v, v1 = v1, v - q * v1
        g, g1 = g1, g - q * g1
    return u, v, g
 
def reverse_element_in_ring(number, ring):
    '''
    Връща число N такова, че number * N % ring == 1
    '''
    return (egcd(number, ring)[0] + ring) % ring
 
def find_prime_factors(num):
    '''Връща речник от простите делители на num.
Ключовете в този речник са делители, а стойностите - коефиценти.
find_prime_factors(12) = {2:2, 3:1}.
Сложност: O(N^(1/2))'''
    divisors = {}
    if not num % 2:
        divisors[2] = 0
        while not num % 2:
            num /= 2
            divisors[2] += 1
    x = 3
    while x*x <= num:
        if not num % x:
            divisors[x] = 0
            while not num % x:
                num /= x
                divisors[x] += 1
        x += 2
    if num > 1:
        divisors[num] = 1
    return divisors
 
def phi(n = None, factors = None):
    '''
    Функцията на Ойлер.
    Приема или числото N, или делителите му.
    '''
    if n == None and factors == None:
        return None
    if n != None:
        factors = find_prime_factors(n)
    return reduce(lambda x, y: x*y, [(factor - 1)*(factor**(quotent-1)) for factor, quotent in factors.items()])
 
class Fraction(object):
    '''
    Клас за работа с обикновени дроби. Не е завършен, но е сравнително функционален.
    '''
    def __init__(self, num = 0, denum = 1):
        if num.__class__ == Fraction:
            num, denum = num.num, num.denum
        GCD = gcd(num,denum)
        self.num = num/GCD
        self.denum = denum/GCD
    def __str__(self):
        if self.num == 0:
            return "0"
        if self.denum == 1:
            return str(self.num)
        return "%s/%s" % (self.num, self.denum)
    def __repr__(self):
        return self.__str__()
    def __neg__(self):
        return Fraction(-self.num, self.denum)
    def __add__(self, other):
        return Fraction(self.num*other.denum + other.num*self.denum, self.denum*other.denum)
    def __mul__(self, other):
        return Fraction(self.num*other.num, self.denum*other.denum)
    def __div__(self, other):
        return Fraction(self.num*other.denum, self.denum*other.num)
    def __eq__(self, other):
        return self.num * other.denum == self.denum*other.num
    def __hash__(self):
        return str(self).__hash__()
    def __pow__(self, y, modulo = None):
        return Fraction(
                pow(self.num, y, modulo),
                pow(self.denum, y, modulo)
                )
 
class Polynomial:
    '''
    Клас за работа с полиноми.
    '''
    def __init__(self, quotients = [], _class = None):
        self.poly = {}
        if not _class:
            if not quotients:
                _class = int
            else:
                try:
                    _class = quotients[0][1].__class__
                except TypeError:
                    _class = int
        self._class = _class
        for monome in quotients:
            power, coefficent = monome
            self.poly[power] = _class(coefficent) + self.poly.get(power, _class(0))
 
    def normalize(self):
        [self.poly.pop(pow) for pow, coef in self if coef == self._class(0)]
        return self
 
    def __add__(self, other):
        p = Polynomial(_class = self._class)
        p.poly = {}
        for power, coefficent in self.poly.items():
            p.poly[power] = coefficent
        for power, coefficent in other.poly.items():
            p.poly[power] = coefficent + p.poly.get(power, self._class(0) )
        return p.normalize()
 
    def __mul__(self, other):
        p = Polynomial(_class = self._class)
        for p1, c1 in self.poly.items():
            for p2, c2 in other.poly.items():
                p.poly[p1+p2] = c1*c2 + p.poly.get(p1+p2, self._class(0) )
        return p.normalize()
 
    def __div__(self, other):
        return self.__divmod__(other)[0]
 
    def __mod__(self, other):
        return self.__divmod__(other)[1]
 
    def __divmod__(self, other):
        other = Polynomial([(pow, coef) for pow, coef in other])
        rem = Polynomial([(pow, coef) for pow, coef in self])
        res = Polynomial(_class = self._class)
        while len(rem) >= len(other):
            coef = rem[len(rem)] / other[len(other)]
            deg_dif = len(rem) - len(other)
            res[deg_dif] = coef
            old_rem_deg = len(rem)
            rem = rem - other * Polynomial([(deg_dif, coef)])
            assert rem[old_rem_deg] == rem._class(0), "BAD"
        return (res, rem)
 
    def __neg__(self):
        return Polynomial(
                [(power, -coefficent) for power, coefficent in self.poly.items()],
                _class = self._class)
 
    def __sub__(self, other):
        return (self + (-other)).normalize()
 
    def __str__(self):
        @memoize
        def helper(coef, power):
            if coef == self._class(0):
                if power == 0:
                    return "0"
                return None
            if power == 0:
                return "%s" % coef
            if power == 1:
                if coef == self._class(1):
                    return "x"
                return "%s*x" % coef
            if coef == self._class(1):
                return "x^%s" % power
            return "%s*x^%s" % (coef, power)
 
        # sorted(self.poly.items) ?
        poly = " + ".join([ helper(coef, power) for power, coef
                in reversed(self.poly.items())
                if helper(coef, power) != None])
        if len(self.poly) == 0:
            poly = "0"
        return "Polynomial %s" % poly
 
    def __repr__(self):
        return self.__str__()
 
    def __len__(self):
        if len(self.poly) == 0:
            return 0
        return max(pow for pow, coef in self if coef != 0)
 
    def __getitem__(self, ind):
        if ind in self.poly:
            return self.poly[ind]
        return self._class(0)
 
    def __setitem__(self, power, coef):
        self.poly[power] = self._class(coef)
 
    def __iter__(self):
        for power, coef in self.poly.items():
            yield (power, coef)
 
    def __call__(self, x):
        '''
        Връща f(x), където f е текущият полином.
        '''
        return sum(coef * (x**power) for power, coef in self)
 
    def __pow__(self, other):
        ans = Polynomial([(pow, coef) for pow, coef in self])
        for x in range(1, other):
            ans = ans * self
        return ans
 
    def __hash__(self):
        return str(self).__hash__()
 
class GaloaField:
    '''
    Полето на Галоа.
    Известно още като поле за сметки по модул 2.
    '''
    def __init__(self, coef):
        if coef.__class__ == GaloaField:
            self.coef = coef.coef % 2
        else:
            self.coef = coef % 2
    def __add__(self, other):
        return GaloaField(self.coef + other.coef)
    def __sub__(self, other):
        return GaloaField(self.coef - other.coef)
    def __mul__(self, other):
        return GaloaField(self.coef * other.coef)
    def __div__(self, other):
        return GaloaField(self.coef / other.coef)
    def __str__(self):
        return str(self.coef)
    def __divmod__(self, other):
        return self / other, self % other
    def __mod__(self, other):
        return GaloaField(self.coef % other.coef)
    def __neg__(self):
        return GaloaField(self.coef)
    def __eq__(self, other):
        return self.coef == other.coef
    def __hash__(self):
        return str(self).__hash__()
