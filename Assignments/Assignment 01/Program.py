import math
import cmath
import matplotlib.pyplot as plt 
import numpy as np

def get_variables():
	
	print("Input the values to find the roots of ax^2 + bx + c. or in place of \"a\" type 'close' to end.")
	
	a = input("a = ")
	try:
		a = float(a)
	except:
		print("It looks like you entered an invalid value.")
		
	b = input("b = ")
	try:	
		b = float(b)
	
	except:
		print("It looks like you entered an invalid value.")

	c = input("c = ")
	try:
		c = float(c)
	except:
		print("It looks like you entered an invalid value.")
		
	variables = [a, b, c]
	quadratic_equation(variables)
		
def quadratic_equation(variables):
	
	
	d = ((variables[1]**2) - (4*variables[0]*variables[2]))
	root1 = ((-1*variables[1])+(d**0.5)) / (2*variables[0])	
	root2 = ((-1*variables[1])-(d**0.5)) / (2*variables[0]) 

	if root1==root2:
		print(f"{root1} is the only root.\n")

	else:
			print(f"({root1}) and ({root2}) are the roots.\n")
			
def graph_parabola():
	
	print("Input the values in the form of ax^2 + bx + c. Only enter ints, and too big of a number will make the graph  look weird. ")
	
	a = int(input("a = "))
	
	try:
		a = int(a)
	except:
		print("It looks like you entered an invalid value.")
	
		
	b = input("b = ")
	
	try:	
		b = int(b)
	
	except:
		print("It looks like you entered an invalid value.")
	

	c = input("c = ")

	try:
		c = int(c)
	except:
		print("It looks like you entered an invalid value")
	
		
	x = np.linspace(-100, 100, 100)
	y = (x*a)**2 + b*x + c
	fig, ax = plt.subplots()
	ax.plot(x, y)
	plt.show()

def main():

	while True:		
		x = input("Type in the number besides a thing to do that thing: \n 0: Cancel \n 1: Quadratic Equation \n 2: Graph a Parabola \n")
		
		try:
			x = int(x)
			
		except:
			print("You did not enter a valid value.")
		
		
		if x==0:
			break
		
		if x==1:
			get_variables()
		
		if x==2:
			graph_parabola()

if __name__ == "__main__":
	main()


#x = (-b +- sqrt/b^2 - 4ac)/2a


