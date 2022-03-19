def main():
	a = [1, 2, 3, 4, 5, 6, 7]
	b = a #they point to the same area in memory
	
	#how to copy lists
	x = list(range(len(a)))
	for i in range(len(a)):
		x[i] = a[i]
		
	#b = a.copy() #too bad they already have a method for that
	
	c = ["Timmy", "Tom", "Jenny"]
	c = [1, 4.33222, ["Jean", "Jacque"]]
	#tuple uses () list uses []
	
	t = (1, 3, 4, 5, 32)
	#tuple vs list: tuple is immutable. can't be changed
	l = list(t) #makes a tuple a list
	l[0] = 4122451
	t = tuple(l)
	
	l = [5, 2, 3, 4, 4, 5, 6, 6, 6, 6]
	s = set(l)
	#a set is an unorganized collection where each element appears once
	print(s)
	d = s.pop
	#s.add(2)
	
	slicee = l[0:3]
	print(f"{slicee}")
	
	 
if __name__ == "__main__":
	main()
