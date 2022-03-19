#functions/methods in python

def sayHi(name):
	print(f"hi {name}\n")
	print ("hi again\n")
	
def mult(num1, num2):
	return num1*num2
		
name = input("Give me your name: ")

for i in range(0,3):
	sayHi(name)

mult(2,3)
mult(3, "Josh")

prod = mult(3, 12)
print(f"{prod}") 

prod = (mult(3,12) 3) #COMPOSITION
