import numpy as np
import cv2
import random

def show(img):
	cv2.imshow("img", img)
	cv2.waitKey(1)
	#cv2.destroyAllWindows()

def coverage(towers):
	img = getCoverageMap(towers)
	covered = (img==255).sum()
	h, w = img.shape
	total = h*w
	
	return covered/h/w
	#return covered

def getCoverageMap(towers):
	img = np.zeros((512, 512), dtype=np.uint8)
	for x, y, r in towers:
		cv2.circle(img, center=(int(x),int(y)), radius=int(r), color=255, thickness=-1)
	
	return img

def getPrettyMap(towers):
	img = np.zeros((512, 512), dtype=np.uint8)
	for x, y, r in towers:
		temp = np.zeros((512, 512), dtype=np.uint8)
		cv2.circle(temp, center=(int(x),int(y)), radius=int(r), color=255, thickness=-1)
		img[temp>0] += 32
		
	return img

def getOverlappingCount(towers):
	img = np.zeros((512, 512), dtype=np.uint8)
	for x, y, r in towers:
		temp = np.zeros((512, 512), dtype=np.uint8)
		cv2.circle(temp, center=(int(x),int(y)), radius=int(r), color=255, thickness=-1)
		img[temp>0] += 1

	return (img>1).sum()/512/512

def getPopulation(n=100):
	return [Solution() for i in range(n)]

def getBest(population, n = 25):
	population.sort(key=lambda x:x.fitness())
	return population[-n:]

def rePopulate(population, n=100):
	n = []
	while len(population + n) < 100:
		a = random.choice(population)
		b = random.choice(population)
		n.append(a.crossover(b))
		
	#mutatePopulation(n)
	return n+pop
	
def mutatePopulation(pop):
	for p in pop:
		p.mutate()
		
class Solution:
	def __init__(self, n=10):
		self.towers = [[random.uniform(0, 512), random.uniform(0, 512), random.uniform(50, 200)] for i in range(n)] #this is a list generator
	
	def fitness(self):
		c = coverage(self.towers)
		cost = sum( r*r for x,y,r in self.towers )
		overlaps = getOverlappingCount(self.towers)
		
		#return -cost/c
		return c-0.5*overlaps
	
	def crossover(self, other):
		s = Solution(n=0)
		for m in zip(self.towers, other.towers):
			s.towers.append(random.choice(m)[:]) #m[:] gets copy of towers
		
		return s
	
	def mutate(self):
		while random.random()<0.05:
			i = random.randrange(10)
			j = random.randrange(3)
			self.towers[i][j]*=0.2*random.random()+0.9
		
		self.fix()
	
	def fix(self):
		for i in range(len(self.towers)):
			x, y, r = self.towers[i]
			if r < 50:
				r = 50
			if r > 150:
				r = 150
	
	def show(self):
		show(getCoverageMap(self.towers))
		
	def showPretty(self):
		show(getPrettyMap(self.towers))
	

pop = getPopulation()
while 1:
	pop = getBest(pop)
	print(pop[-1].fitness())
	print(pop[-1].showPretty())
	
	
	pop = rePopulate(pop)
	mutatePopulation(pop)

print(s.fitness())
