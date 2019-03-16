## CSCB846 - Криптография

1. Даден е шифър на Plaifair с ключ:
```
H O W M A
N Y E L K
S B C D F
G I/J P Q R
T U V X Z
```

1.1 Да се шифрира: APIGINACAGEONANTIBIOTICS
Отговор: WRPIGYWFHRYWKHSHUIUYUGDB
1.2 Да се дешифрира: BRUTVNGAKWUGOGKPAWPKQGMBVBUGWC
Отговор: FITZTERHEATIHIERMOREPRODUCTIVE

2. 

3. Чрез Автоключ Vigenere и шифър: GXILBGLQQJAIPWBMRKAZBWYKKKUCRKG
да се намери текста, ако ключа е с размер m=6 и се знае, че текста съдържа "GESTURE".

https://www.guballa.de/vigenere-solver
Key: GLAUBE
Clear text: "AMIRACLEISAGESTUREWHICHGODMAKES"

CIPHER: GXILBGLQQJAIPWBMRKAZBWYKKKUCRKG
KEY: XXXXXX****GESTURE****


CIP: GXILBGLQQJA|IPWBMRK|AZBWYKKKUCRKG
KEY: GLAUBEAMIRA|CLEISAG|ESTUREWHICHGO
TEX: AMIRACLEISA|GESTURE|WHICHGODMAKES

Ако знаем, че текста съдържа GESTURE нека се опитаме да видим какви са вариантите в които имаме без да знаем ключа. За ключа знаем, че е с дължина 6, но не сме сигурни дали показаната дума е част от низ с дължина 6 или е по средата например. Използвайки Tabula Recta проверяваме възможните комбинации с примерната дума GESTURE като част от keystream.

Keystream: * * * * G E S T U R E * * * * = [6 4 18 19 20 17 4]
Ciphertext: G X I L B G L Q Q J A I P W B M R K A Z B W Y K K K U C R K G
To Num: 6 23 8 11 1 6 | 11 16 16 9 0 8 | 15 22 1 12 17 10 | 0 25 1 22 24 10 | 10 10 20 2 17 10 | 6
