from playsound import playsound
import threading

class Ball:
	def __init__(self):
		self.psi = 8
		self.hasHole = False

	def bounce(self):
		if self.hasHole:
			self.deflate()
		if self.psi>4:
			playsound("bounce.wav")
		else:
			playsound("thud.wav")
		
	def stab(self):
		self.hasHole = True
		playsound("stab.wav")
	
	def patch(self):
		self.hasHole = False
		playsound("patch.mp3")
	
	def inflate(self, n = 1):
		if n > 0:
			if not self.hasHole:
				self.psi += n
				playsound("inflate.wav")
		
		if self.psi > 9.5:
			playsound("burst.wav")
			print("your ball busted")
			self.hasHole = True
			self.psi = 0
	
	def deflate(self, n = 1):
		if self.psi > 0:
			self.psi -= n
			playsound("deflate.wav")
	
	def checkPressure(self):
		return self.psi

def interface(b):

	while(True):
		choice = int(input("Type a num to do a thing to your ball. \n 1) Bounce \n 2) Stab \n 3) Patch \n 4) Inflate \n 5) Deflate \n 6) Check PSI \n 7) Quit \n"))
				
		if choice == 1:
			b.bounce()
		elif choice == 2:
			b.stab()
		elif choice == 3:
			b.patch()
		elif choice == 4:
			b.inflate()
		elif choice == 5:
			b.deflate()
		elif choice == 6:
			print(f"The pressure is {b.checkPressure()} psi")
		elif choice == 7:
			break
		else:
			print("You entered a wrong value!")
def main():

	b = Ball()
	interface(b)
	


if __name__ == '__main__':
	main()
