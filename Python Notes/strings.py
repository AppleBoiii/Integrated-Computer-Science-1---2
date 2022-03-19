def isItUpperCase(x):
	return 65 <= ord(x) <= 90
	
def isItLowerCase(x):
	return 97 <= ord(x) <= 122
	
def sayHi(name = ""):
	print("Howdy,"+name+"\n")
	
def main():
	sayHi()
	s = "Howdy do\t neighborinos\n"
	print(s)
	
	#s = ""
	#while s.lower() != 'q':
		#s = input("Give me your whole toes or q it to quit: ")
		#print('q' not in s.lower())
		
		#if 'q' in s.lower():
			#print("you have Q")
			#break
		#if 'q' == s.lower():
			#print("you have done the Q")
			#break
	
	i = 0
	while i<1e1+1:
		print(i)
		i+=1
	
	print("\n")
	
	s = "I can't think of a sentence"
	for i in range(0, 100, 10):
		print(s)
		
	for i in s:
		print(i)
		
	basket = ('apple', 'banana', 'child')
	for fruit in basket:
		print(fruit)
		
	for i in range(1, 13):
		s=""
		for j in range(1, 13):
			s += f"{i*j:6d}"
			print(s)
			#print(f"[{i} * {j}] = {i*j}")
			
	#s[:] prints whole thing
	#s[:8] prints until the 8th thing
	#s[4:] starts at the 4th thing and continues on.
	#s[-1] starts from the back
	#len(str) gives length
	
	s = "Hi my name is "
	x = s.find("name")
	print(x)
	
	#Falsy or Truthy
	#Ints, Floats: 0 is falsy. Everything else is truthy
	#Strings: empty string is falsy, everything else is truthy
	#Instances of Classes: None is falsy, all else is truthy.
	
	letter = input("Give me letter: ")
	
	if isItUpperCase(letter):
		print(f"{letter} is upper case!")
	else:
		print(f"{letter} is lower case!")
	
if __name__ == "__main__":
	main()

