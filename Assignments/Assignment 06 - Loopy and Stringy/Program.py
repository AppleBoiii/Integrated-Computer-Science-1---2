import random as rd
import math
import numpy as np
import webbrowser
try: 
	import cv2
except:
	print("why don't you have cv2? i'm assuming its you dr jack and not seward smhh ig you won't see the surprise")

def show(img, wait):
    img = np.uint8(img)
    cv2.imshow("image",img)
    cv2.waitKey(wait)

def rickRoll():
	img = cv2.imread("image.png")
	h, w = img.shape[:2]
	j = 0
	k = 0 
	
	webbrowser.open_new_tab("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
	for loop in range(5):
		for i in range(100):
		
			angle = math.pi*i/100+math.pi/2*np.cos(k*.075);
			scaleFactor = 1+.5*np.cos(j*.1)
			
			T1 = np.float64([[1,0,-w/2],[0,1,-h/2],[0,0,1]])
			T2 = np.float64([[1,0,w/2],[0,1,h/2],[0,0,1]])
			S = np.float64([[scaleFactor,0,0],[0,scaleFactor,0],[0,0,1]])
			R = np.float64([[np.cos(angle),-np.sin(angle),0],[np.sin(angle),np.cos(angle),0],[0,0,1]])
			M = T2@R@S@T1
			
			#M = np.float64([[1.5, -0.11817, 82.7], [0.11817, 1.5, -82.7], [0, 0, 1.5]])
			
			j += 1
			k += 1



			img2 = cv2.warpPerspective(img,M,(w,h))
			show(img2, 25)
		loop += 1
		
		
def factor(x):
	
	factors = []
	
	for i in range(0, x+1):
		for j in range(0,x+1):
			if i*j == x:
				factors.append(i)
							
	factorSet = set(factors)
	factorSet = sorted(factorSet)
	
	factorString = str(factorSet[:len(factorSet)-1])
	factorString += f", and {factorSet[len(factorSet)-1:]}"
	factorString = factorString.replace("[", "")
	factorString = factorString.replace("]", "")

	s = f"The factors of {x} are {factorString}"
	return s
	
def hiLo():
	key = rd.randint(0, 100)
	
	print("hiLo: guess the INT that's 1 - 100. Three nums are SEXY aka they reset tries, but ONLY once. \n")
	
	
	moLife = [rd.randint(0,100), rd.randint(0,100), rd.randint(0, 100)]
	for num in range(len(moLife)):
		if moLife[num] == key:
			moLife[num] = rd.randint(0,100)
		print(f"There is a SEXY num between {moLife[num]-(moLife[num]%10)} and {moLife[num]-(moLife[num]%10)+10}")
			
	result = "\nGood job, son!\n"
	wrongMsg = "That was not the answer. Try again: "
	
	guess = "" #int(input("Give me a guess: "))
	lives = 0
	reset = False
	
	while guess != key:
			
		guess = int(input("\nGive a guess: "))
		
		if lives > 0:
			for i in range(len(moLife)):
				if guess == moLife[i]:
					print("You got a sexy number! You're # of tries have reset.")
					lives = 0
					reset = True
					moLife[i] = -1
		if reset:
			reset = False
			continue			
				
		if guess > key:
			if lives == 4:
				result = "\nYou failed!! sucks to suck loser.\n"
				break
				
			print("You're too high!(ugly) "+wrongMsg)
			lives += 1
		
		if guess < key:
			if lives == 4:
				result = "\nYou failed!! sucks to suck loser.\n"
				break
				
			print("You're too low!(sexy) "+wrongMsg)
			lives += 1
		
		
	print(result)

def exponet():
	nums = [1, 2, 3, 4 , 5 , 6 , 7, 8, 9, 10]
	for i in range(len(nums)):
		print (f"{nums[i]}\t|", end="")
	
	print("")
	
	for i in range(2, 7):
		for j in range(len(nums)):
			if i == 6 and j == 9:
				print(f"{nums[j]**i}|", end="")
			elif i != 7:
				print (f"{nums[j]**i}\t|", end ="")
		print()

def menu(men):
	
	choice = input("Press 1 to factor, 2 to play hiLo, and 3 to show an exponentiation table. 0 exits. DON'T type another num besides these(well, you can butttt) :> \n 0.)Quit \n 1.)Factor \n 2.)hiLo \n 3.)Exponet Table \n")	
	print()
	
	while True:
		try:
			choice = int(choice)
			break
			
		except:
			choice = input("You did not type an int. retype it: ")
			print()
	
	if choice == 0:
		return False
		
	elif choice == 1:
		x = input("\ngive an int you want to factor: ")
		print()
		while True:
			try:
				x = int(x)
				break
			except:
				x = input("type an int before you get hecking hecced: ")
				print()
		print( factor(x) )
		return True
	elif choice == 2:
		hiLo()
		return True
		
	elif choice == 3:
		exponet()
		return True
	else:
		rickRoll()
		return False

def main():
	men = True
	while men == True:
		men = menu(men)

if __name__ == "__main__":
	main()

'''


 '''
 
