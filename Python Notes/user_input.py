'''
x = input("How many puppies do you have? ")

while x != int:
	print("You dummy. Type in a number.")
	x = input()

age = input(f"Out of {x} puppies, how old is the oldest? ")

try:
	age = int(age)
	print(age*-1)

except ValueError:
	print(f"You idiot.{age} was not entered as a number")
'''


'''
decimal degrees to degrees, minutes, seconds
'''

dec = input("enter decimal degrees ")
dec = float(dec)

MINUTES_PER_DEGREE = 60
SECONDS_PER_MINUTE = 60

remainder = dec%1
degrees = int(dec)

minutes = remainder*MINUTES_PER_DEGREE
remainder = minutes%1
minutes = int(minutes)

seconds = remainder*SECONDS_PER_MINUTE

print(f"Degrees: {degrees} Minutes: {minutes} Seconds: {seconds} ")


"""
more matplotlib things
https://matplotlib.org/api/pyplot_summary.html


TUPLE METHODS
Method 	Description
append()	Adds an element at the end of the list
clear()	Removes all the elements from the list
copy()	Returns a copy of the list
count()	Returns the number of elements with the specified value
extend()	Add the elements of a list (or any iterable), to the end of the current list
index()	Returns the index of the first element with the specified value
insert()	Adds an element at the specified position
pop()	Removes the element at the specified position
remove()	Removes the item with the specified value
reverse()	Reverses the order of the list
sort()	Sorts the lists
"""
