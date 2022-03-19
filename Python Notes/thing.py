import turtle

l = list(range(10))

while l:
	print(f"Give me {l.pop()} leave {l}")

flag = None #turtle.Turtle()

if flag:
	print("truthy")
else:
	print("falsy")
		
#Truthy = 
#False => ints, floats 0
#Empty Strings => falsey, !empty = truthy
#Lists, dicts, sets, tuples not empty = truthy

