 import sys
import math	

x=math.pi	
print(x)
for i in range(3):
	print(i)

#PRIMATIVE TYPES
#Ints, Floats, Booleans, Strings

n=37

#this shows what variable n is
print(type(n)) 

#how much memory it takes up in bytes
print(sys.getsizeof(n))

x=26.9994913112000

print(x)
#only shows the most significant numbers

#prints x to however many digits
print(format(x, '.20g'))

#integer division = //
print(4//2)
print(4/2)

#\t and \n

y = "hi there"
z = 3
print(str(z) + y)

print("{} and {}" .format(z, y))
print(3**2)
print(f"{y}+{z}")

money = 10.01
hi = "I have "

print(f"{hi}${money:2.2f}")

name = "Johnny Beepbop"
print(f"Your name isn't{name:>20}")

#for unicode characters
a ="\u03b1"
print(a)

print('I am 6\'0"{}2"' .format(u'\u00b1'))
print('I am 6\'0" \u00b1 2"' .format(u'\u00b1'))

#Taking User Input	

x = input("dogs\n")

print(type(x))

#python Prints_Unicode.py 3.5 7 
name, num1, num2 = sys.argv

num1 = float(num1)
num2 = float(num2)

	
