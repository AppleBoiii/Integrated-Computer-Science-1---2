import numpy as np
import cv2
import random


def show(img):
    cv2.imshow("img",img)
    cv2.waitKey(1)
    #cv2.destroyAllWindows()

def coverage(towers):
    img=getCoverageMap(towers)
    covered=(img==255).sum()
    h,w=img.shape
    total=h*w
    return covered/h/w

def getCoverageMap(towers):
    img=np.zeros((512,512),dtype=np.uint8)
    for x,y,r in towers:
        cv2.circle(img, (int(x),int(y)), int(r),color=255, thickness=-1)
    return img
def getPrettyMap(towers):
    img=np.zeros((512,512),dtype=np.uint8)
    for x,y,r in towers:
        temp=np.zeros((512,512),dtype=np.uint8)
        cv2.circle(temp, (int(x),int(y)), int(r),color=255, thickness=-1)
        img[temp>0]+=63
    return img

def getOverlappingPercent(towers):
    img=np.zeros((512,512),dtype=np.uint8)
    for x,y,r in towers:
        temp=np.zeros((512,512),dtype=np.uint8)
        cv2.circle(temp, (int(x),int(y)), int(r),color=255, thickness=-1)
        img[temp>0]+=1
    return (img>2).sum()/512/512
    

def getPopulation(n=100):
    return [Solution() for i in range(n)]
    
def getBest(population,n=25):
    def getFitness(x):
        return x.fitness()
    population.sort(key=getFitness)
    return population[-n:]

def rePopulate(pop,n=100):
    n=[]
    while len(pop+n)<100:
        a=random.choice(pop)
        b=random.choice(pop)
        n.append(a.crossover(b))
    # ~ mutatePopulation(n)
    return n+pop

def mutatePopulation(pop):
    for p in pop:
        p.mutate()

class Solution:
    def __init__(self,n=10):
        self.towers=[[random.uniform(0,512),random.uniform(0,512),random.uniform(50,150)] for i in range(n)]
    def fitness(self):
        c=coverage(self.towers)
        cost=sum(r*r  for x,y,r in self.towers  )
        overlaps=getOverlappingPercent(self.towers)
        return c-100*overlaps
    def crossover(self, other):
        s=Solution(n=0)
        for m in zip(self.towers,other.towers):
            s.towers.append(random.choice(m)[:])
        return s
    def mutate(self):
        while random.random()<.5:
            i=random.randrange(10)
            j=random.randrange(3)
            self.towers[i][j]+=20*random.random()-10
        self.fix()
    def fix(self):
        for i in range(len(self.towers)):
            x,y,r=self.towers[i]
            if r<50:
                r=50
            if r>150:
                r=150
                
    def show(self):
        show(getCoverageMap(self.towers))
                
    def showPretty(self):
        show(getPrettyMap(self.towers))
            
    
pop=getPopulation()
while 1:
    pop=getBest(pop)
    print(pop[-1].fitness())
    pop[-1].showPretty()
    
    pop=rePopulate(pop)
    mutatePopulation(pop)

