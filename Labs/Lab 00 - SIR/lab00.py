import random as rand
import turtle
import math
import time
import matplotlib.pyplot as plt

'''
The parts used to make the water experiment are commented out.
'''


n = 100
SUSCEPTIBLE = 'green' 	
INFECTED = 'red'		
RECOVERED = 'grey'		
LOST =  'black'

X = 0
Y = 1
STATUS = 2
DIRECTION = 3
STEPS = 4

MOVE_DISTANCE = 1
INFECT_DISTANCE = [10, 6]
INFECT_FLOOR = 0.00001589

WIDTH=HEIGHT=300
screen = turtle.getscreen()
screen.tracer(0)
turtle.up()
turtle.ht()
turtle.update()

#This part is for the water source experiment
#SOURCES = 20 #number of water sources
#INFECTED_SOURCES = 10
#RADIUS = 20 #radius of a water source

WATERX = 0
WATERY = 1
QUALITY = 2

def display(population, WIDTH=200, HEIGHT=200, waterSources=[]):
	turtle.clear()
	turtle.goto(WIDTH,HEIGHT)
	turtle.down()
	turtle.seth(180)
	
	for i in range(4):
		turtle.forward(WIDTH*2)
		turtle.left(90)
		
	turtle.up()
	turtle.shapesize(2, 2)
	
	
	for waterX, waterY, quality in waterSources:
		turtle.goto(waterX, waterY)
		turtle.dot(RADIUS, quality)
	
	for x, y, status, direction, _ in population:
		turtle.goto(x,y)
		turtle.seth(direction)
		turtle.fillcolor(status)
		turtle.stamp()
		
	turtle.update()

def getPopulation(n, WIDTH=200, HEIGHT=200): #maybe use WIDTH and HEIGHT to change world size
	population = []
	
	for i in range(n):
		x = rand.uniform(-WIDTH, WIDTH)
		y =	rand.uniform(-HEIGHT, HEIGHT)
		status = SUSCEPTIBLE
		direction = rand.uniform(0, 360)
		steps = 0
		population.append([x, y, status, direction, steps])
		
	return population

'''
def getWaterSource(SOURCES = 6, WIDTH=200, HEIGHT=200):
	
	waterSources = []
	radius = 10
	
	infectedSources = 0
	for i in range(SOURCES):
		x = rand.uniform(-WIDTH+radius, WIDTH-radius)
		y = rand.uniform(-HEIGHT+radius, HEIGHT-radius)
		
		for waterX, waterY, *_ in waterSources:
			d = math.sqrt(((x-waterX)**2)+((y-waterY)**2))
			
			if d<75:
				x = rand.uniform(-WIDTH+radius, WIDTH-radius)
				y = rand.uniform(-HEIGHT+radius, HEIGHT-radius)
			
		waterX = x
		waterY = y
		quality = "blue"
		
		if infectedSources < INFECTED_SOURCES:
			quality = INFECTED
			infectedSources += 1
	
		waterSources.append([waterX, waterY, quality])
		
	return waterSources
'''	 	
def infectPopulation(population, n=1):
	while n: #while n: or make this n != 0 for a patient zero
		person = rand.choice(population)
		
		if person[STATUS] == SUSCEPTIBLE:
			person[STATUS] = INFECTED
			n -= 1

def step(population, waterSources = [], WIDTH=200, HEIGHT=200):
	for person in population:
		x, y, status, direction, steps = person
		
		if status == LOST:
			person[STEPS] += 1
			continue
		
		x += MOVE_DISTANCE*math.cos(direction/180*math.pi)
		y += MOVE_DISTANCE*math.sin(direction/180*math.pi)
		direction += rand.uniform(-5, 5)
		
		if x<-WIDTH or x>WIDTH:
			#direction = 180 - direction
			x *= -1
			
		if y>HEIGHT or y<-HEIGHT:
			#direction = -direction
			y *= -1
	
		person[X] = x
		person[Y] = y
		person[DIRECTION] = direction
		
		
		
		if status == SUSCEPTIBLE:
			expose(person, population)
			#waterExpose(person, population, waterSources)
			#lostExpose(person, population)
			
		elif status == INFECTED:
			r = rand.random()
			
			if r<.01 and r>.001:
				person[STATUS] = RECOVERED
				
			elif r<=.001:
				person[STATUS] = LOST

def lostExpose(person, population):
	x, y, status, *_= person
	
	for other in population:
		if other[STATUS] == LOST and other[STEPS]<500:
			otherx = other[X]
			othery = other[Y]
			
			d = math.sqrt(((x-otherx)**2)+((y-othery)**2))
			
			if d<5 and rand.random() > 0.5:
				person[STATUS] = INFECTED

'''
def waterExpose(person, population, waterSources):
	x, y, status, direction, _ = person
	
	for source in waterSources:
		waterx, watery, quality = source
		d = math.sqrt(((x-waterx)**2)+((y-watery)**2))
		
		if d <= 5 and quality == INFECTED and rand.random()>0.5:
			person[STATUS] = INFECTED
'''	
def expose(person, population):
	x, y, status, direction, _ = person
	
	for other in population:
		if other[STATUS] == INFECTED:
			i, j ,*_ = other
			d = math.hypot(x-i,y-j)
			
			#MAKE UP THIS MODEL
			if d <= INFECT_DISTANCE[0]:
				cough = rand.choice([True, False])
				#If false it's a sneeze
				
				if d <= INFECT_DISTANCE[1] and cough:
					kachoow(person)
					
				elif d >= INFECT_DISTANCE[1] and not cough:
					kachoow(person, cough)
				
				elif d >= INFECT_DISTANCE[1] and cough:
					break
					
				else:
					kachoow(person, cough) 
					
			break
			
	
def kachoow(person, cough = True):
	droplets = list()
	
	if cough:
		dropletAmount = rand.randint(100, 3000)
		for i in range(dropletAmount):
			dropletSize = rand.uniform(0.00000062, 0.0000159)
			droplets.append(dropletSize)
	else:
		dropletAmount = (rand.randint(3000, 40000))
		for i in range(dropletAmount):
			dropletSize = rand.uniform(0.00000062, 0.0000159)
			droplets.append(dropletSize)
					
	bigDroplets = list()

	for i in range(5):
		bigDroplets.append( max(droplets))
		droplets.remove(max(droplets))
	
	
	#if (bigDroplets[0] + bigDroplets[1] + bigDroplets[2]) > (INFECT_FLOOR*3):
		#person[STATUS] = INFECTED
	if max(bigDroplets) > INFECT_FLOOR:
		person[STATUS] = INFECTED

def census(population):
	s=i=r=l=0
	for _, _, status,_, _ in population:
		s += status  == SUSCEPTIBLE
		i += status  == INFECTED
		r += status  == RECOVERED
		l += status  == LOST
	return s, i, r, l

def averageInfected(infected):
	height = len(infected)
	theSum = 0
	for i in range(height):
		theSum += sum(infected[i])
	
	length = 0
	for i in range(height):
		length += len(infected[i])
		
	average = theSum/length
	
	return average
		
def simulation(n=100, InitialInfected = 1, show=True, WIDTH = 200, HEIGHT = 200):
	
	steps = 0
	#waterSources = getWaterSource(SOURCES, WIDTH, HEIGHT)
	population = getPopulation(n, WIDTH, HEIGHT)
	infectPopulation(population, n=InitialInfected)
	s,i,r,l = census(population)
	S = [s]	#[s]
	I = [i]	#[i]
	R = [r]	#[r]
	L = [l]	#[l]
	while I[-1] > 0:
		step(population, WIDTH, HEIGHT)
		steps += 1
		s, i, r, l = census(population)
		S.append(s)
		I.append(i)
		R.append(r)
		L.append(l)
		#time.sleep(1)
		if show:
			display(population, WIDTH, HEIGHT)
	#return S, I, R, L
	return steps
	
def averageSteps(steps):
	length = len(steps)
	theSum = sum(steps)
	average = theSum/length
	
	return average
def main():
	
		
	
	'''
	data = simulation(100, 1, True, WIDTH, HEIGHT)
	for i in range(len(data)):
		print(data[i])
		print("----")
	'''
	'''
	x=range(len(data[0]))
	plt.stackplot(x,data, labels=['S','I','R', 'L'],colors=[SUSCEPTIBLE,INFECTED,RECOVERED, LOST])
	plt.legend(loc='upper left')
	plt.xlabel('step count')
	plt.ylabel('population')
	plt.title(f'SIR Simulation (n={n})')
	plt.show()
	'''
	turtle.mainloop()
if __name__ == "__main__":
	main()
	
'''
DIFFERENT GRAPHS
LATEX THING WITH IT


https://www.ncbi.nlm.nih.gov/pmc/articles/PMC7462404/
https://pubmed.ncbi.nlm.nih.gov/18158720/
https://www.ncbi.nlm.nih.gov/books/NBK143281/
https://www.who.int/water_sanitation_health/emergencies/qa/emergencies_qa8/en/
'''

#CHANGES
#-Added WIDTH and HEIGHT parameters to get different world sizes for people to wiggle in
#-Added a random infection chance based on distance. (cough or sneeze? if cough or sneeze, figure out if they are infected based on amount of droplets)
#-Made it so if people go to the edge, they come out the other side. 
#-Added "water-sources", some sources are infected and if people go there - 'to drink' - they become infected. 
#-If people die, they can trasnmit the infection for 500 steps. 


#EXPERIMENTS:

#EXPERIMENT 1
'''
amount of infected with area

WIDTH=HEIGHT=10
x = []
y = []

for i in range(0, 15):
	infected = []
	print(i)
	
	for n in range(25):
		data = simulation(100, show=False, WIDTH=WIDTH, HEIGHT=HEIGHT)
		infected.append(data[1])
	
	x.append(HEIGHT*WIDTH)
	y.append(averageInfected(infected))
	HEIGHT += i
	WIDTH += i

plt.plot(x, y, linewidth = 3)
#plt.stackplot(x,y, labels=['Area','Infected'],colors=[SUSCEPTIBLE,INFECTED])

x_min = min(x)
y_min = min(y)

x_max = max(x)
y_max = max(y)

plt.xlim([x_min, x_max])
plt.ylim([y_min, y_max])


plt.xlabel('Area of the \'World\'')
plt.ylabel('Average Infected')
plt.title('The Effect of Space on Disease Transmission')
plt.show()

-------------
length of disease

WIDTH=HEIGHT=10
x = []
y = []

for i in range(0, 15):
	length = []
	print(i)
	
	for n in range(25):
		data = simulation(100, show=False, WIDTH=WIDTH, HEIGHT=HEIGHT)
		length.append(data)
	
	x.append(HEIGHT*WIDTH)
	y.append(averageSteps(length))
	HEIGHT += i
	WIDTH += i

plt.plot(x, y, linewidth = 3)
#plt.stackplot(x,y, labels=['Area','Infected'],colors=[SUSCEPTIBLE,INFECTED])

x_min = min(x)
y_min = min(y)

x_max = max(x)
y_max = max(y)

plt.xlim([x_min, x_max])
plt.ylim([y_min, y_max])


plt.xlabel('Area of the \'World\'')
plt.ylabel('Steps')
plt.title('The Effect of Space on the Length of a Disease Outbreak')
plt.show()
'''
#EXPERIMENT 2
'''
global INFECTED_SOURCES
INFECTED_SOURCES = 1

x = [] 
y = []

for i in range(20):
	lost = []
	print(i)
	for n in range(50):
		data = simulation(100, 1, False, WIDTH, HEIGHT)
		lost.append(data[3])
	
	x.append(INFECTED_SOURCES)
	y.append(averageInfected(lost))
	INFECTED_SOURCES += 1

plt.plot(x, y, linewidth = 3)

x_min = min(x)
y_min = min(y)

x_max = max(x)
y_max = max(y)

plt.xlim([x_min, x_max])
plt.ylim([y_min, y_max])


plt.xlabel('# of Infected Water Sources')
plt.ylabel('Average Deaths')
plt.title('The Impact on Death Rate by Increasing # of Infected Water Sources')
plt.show()

generate like 4 / 5 water sources 
a random number(there has to be at least one or two uninfected ones) are contaminated
if people drink from contaminated one, they get a chance of being infected by something
if they die, their corpse can spread infection for a number of steps
as more people drink they begin to stay around the noncontaminated ones.
'''
