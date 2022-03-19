import random
import numpy as np
from datetime import datetime
from scipy.ndimage.measurements import label

#N = NxN grid, N precincts, etc
#CAPYBARA = who wins, squirrel or capybara 
N = 10
CAPYBARA = False

#Solution obj
class CountyMap:
	def __init__(self, n=N):
		x = list(range(n))*n #gives nums 0-n, n times
		random.shuffle(x)
		
		y = []
		if CAPYBARA:
			y = [56, 50,51, 51, 66, 58, 62, 62, 66, 66, 48, 54, 58, 50, 36, 65, 42,54, 42, 64, 56, 56, 35, 57, 56, 54, 44, 53, 38, 43, 40, 59, 45, 40, 33, 58, 45, 50, 36, 58, 60, 69, 57, 36, 43, 28, 61, 62, 56, 48, 52, 34, 48, 54, 63, 51, 47, 50, 58, 44, 48, 75, 52, 34, 40, 39, 54, 53, 50, 45, 51, 73, 44, 67, 50, 40, 45, 48, 60, 49, 37, 32, 53, 54, 59, 46, 46, 58, 43, 46, 61, 41, 38, 56, 66, 66, 44, 39, 68, 32]
		
		else: 
			#squirrel votes are just 100-[capybara votes]
			y = [44, 50, 49, 49, 34, 42, 38, 38, 34, 34, 52, 46, 42, 50, 64, 35, 58, 46, 58, 36, 44, 44, 65, 43, 44, 46, 56, 47, 62, 57, 60, 41, 55, 60, 67, 42, 55, 50, 64, 42, 40, 31, 43, 64, 57, 72, 39, 38, 44, 52, 48, 66, 52, 46, 37, 49, 53, 50, 42, 56, 52, 25, 48, 66, 60, 61, 46, 47, 50, 55, 49, 27, 56, 33, 50, 60, 55, 52, 40, 51, 63, 68, 47, 46, 41, 54, 54, 42, 57, 54, 39, 59, 62, 44, 34, 34, 56, 61, 32, 68]

		self.grid = np.reshape(x,(n,n)) #makes the linear nums list a square
		self.votes = np.reshape(y,(n,n)) #makes the vote list a square
	
	def mutate(self):
		
		g = self.grid
		n = len(g)
		
		num = random.random()
		i = random.randrange(n)
		j = random.randrange(n)
		x = random.randrange(n)
		y = random.randrange(n)

		g[i,j],g[x,y] = g[x,y],g[i,j]

	def fitness(self):	#checks for shared edges
		g = self.grid
		n = len(g)
		
		v = 0
		for i in range(len(g)):
			island, count = label(g==i)
			m = max((island==i+1).sum() for i in range(count)) #ASK about this for loop in class
			v += m/count

		
		return v
		
	
	def fitness2(self): #NUMBER TWO	
		v = self.fitness()
		margins = self.margin()
		votes = (sum(margins))
		
		# ~ votes = self.totalVotes
		# ~ votes = totalVotes.sum()
		
		return votes+v
		
	def cross(self, other):
		x = np.random.randint(0, 2, self.grid.shape) #makes a grid of 0s and 1s. wherever there are 1s, a is kept. then be fills in the 0s
		# ~ #c = a*x+b*(1-x) 
		output = CountyMap()
		output.grid = self.grid*x+other.grid*(1-x) 
		output._fix()
		
		return output
		
	def _fix(self):
		n = len(self.grid)
		counts = [ (self.grid == i).sum() for i in range(n) ]
		#print(counts) #prints out how many 0s, 1s, and 2s, etcs there are
		
		while counts.count(n) < n: #if n(length of grid) appears in counts less than 3 times
			i = random.randrange(n)
			j = random.randrange(n)
			ID = self.grid[i,j]
			
			if counts[ID] > n:
				minID = np.argmin(counts) #gives minimum index of the value in counts e.g. it gives the smallest number in counts
				counts[ID] -= 1
				counts[minID] += 1
				self.grid[i, j] = minID 
	
		
	def margin(self): #gets totalVotes and assigns a 1 to a precinct if desired candidate won
		n = len(self.grid)
		
		total = self.totalVotes()

		precinctMargins = []
		for i in range(n):
			if total[i] >= 51*n:
				precinctMargins.append(1)
			else:
				precinctMargins.append(0)

		return precinctMargins

	def totalVotes(self):
		n = len(self.grid)	
		mapOfPrecincts = [0]*n
		voteMap = [0]*n
	
		for i in range(len(mapOfPrecincts)):
			mapOfPrecincts[i] = self.grid == i
			voteMap[i] = self.votes[mapOfPrecincts[i]].sum()
						
		return voteMap
	
	def __str__(self):
		output = ""
		for row in self.grid:
			for cell in row:
				output += f" {cell} "
			output += "\n"
		
		return output

#GA Funcs
def getPopulation(n = 25):
	return [CountyMap() for i in range(n)]

def getBest(population, n = 25):
	population.sort(key=CountyMap.fitness)
	return population[-n:]
	
def getBest2(population, n = 25): #NUMBER 2
	population.sort(key=CountyMap.fitness2)
	return population[-n:]

def rePopulate(pop):
	n = []
	
	while len(pop) + len(n) < 100:
		a = random.choice(pop)
		b = random.choice(pop)
		n.append(a.cross(b))

	return n+pop

def mutatePopulation(pop):
	for p in pop[:-10]:
		for i in range(1):
			p.mutate()

def mutatePopulation2(pop):
	for p in pop[:-5]:
		for i in range(1):
			p.mutate()

def writeToFile(pop):
	f = open("output.txt", "a")
	candidate = ""
	if CAPYBARA:
		candidate = "CAPYBARA"
	else:
		candidate = "SQUIRREL"
	
	string = f"SOLUTION FOR {candidate} \n {pop[-1]} \n fitness ={pop[-1].fitness()} \n fitness2 ={pop[-1].fitness2()} \n {pop[-1].margin()} \n {pop[-1].totalVotes()} \n"
	f.write(string)
	f.write("\n")
	f.close()
	
def main():
	pop = getPopulation()
	
	startTime = datetime.now()
	start_Time = startTime.strftime("%H:%M:%S")
	print(start_Time)
	
	while pop[0].fitness() < N*N-4:
		
		pop = getBest(pop)
		print(pop[-1].fitness())
		pop = rePopulate(pop)
		mutatePopulation(pop)

		
	print(pop[0])
	print(pop[0].totalVotes())
	print(pop[0].margin())		
	
	while pop[-1].fitness2() != (N*N)+N-3:
		
		pop = getBest2(pop)
		#print(pop[-1])
		print(pop[-1].fitness2())
		pop = rePopulate(pop)
		mutatePopulation2(pop)
	
	now = datetime.now()
	current_time = now.strftime("%H:%M:%S")
	print(f"START: {start_Time}")
	print(f"END: {current_time}")
	x
	print()
	
	print(pop[-1])
	print(pop[-1].fitness())
	print(pop[-1].fitness2())
	print(pop[-1].margin())
	print(pop[-1].totalVotes())
	
	writeToFile(pop)
	
	
	
	
if __name__ == "__main__":
	main()
    
