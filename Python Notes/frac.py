import math

#instance variable: num, denom
#constructor: n, d
#methods: add, subtract, reciprical, etc
def gcd(a, b):
		while a*b:
			if a > b:
				a = a % b
			
			else:
				b = b % a
		return max(a,b)
		
class Fraction:
	def __init__(self, n, d):
		self.n = n 
		self.d = d
		g = gcd(n, d)
		self.n//=g
		self.d//=g
	
	def __mul__ (self, other):
		return Fraction(self.n*other.n, self.d*other.d)
	
	def __add__ (self, other):
		return Fraction(self.n*other.d+other.n*self.d, self.d*other.d)
	
	def __str__(self):
		return f"{self.n}/{self.d}"

f = Fraction(1,2)
print(f)
print(f*f)
print(f+f+f)

		
