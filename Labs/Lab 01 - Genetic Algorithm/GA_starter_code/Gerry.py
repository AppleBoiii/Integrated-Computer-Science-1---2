import random
import numpy
from scipy.ndimage.measurements import label

class CountyMap:
    def __init__(self, n=10):
        #instance
        x=list(range(n))*n
        random.shuffle(x)
        self.grid=numpy.reshape(x,(n,n))
        # ~ print(self.grid)
        # ~ print((self.grid==3).sum())
    
    def cross(self,other):
        x=numpy.random.randint(0,2,self.grid.shape)
        output=CountyMap()
        output.grid=self.grid*x+other.grid*(1-x)
        output._fix()
        return output
    
    def _fix(self):
        n=len(self.grid)
        counts=[(self.grid==i).sum() for i in range(n)]
        # ~ print(counts)
        while counts.count(n)<n:
            i=random.randrange(n)
            j=random.randrange(n)
            ID=self.grid[i,j]
            if counts[ID]>n:
                minID=numpy.argmin(counts)
                counts[ID]-=1
                counts[minID]+=1
                self.grid[i,j]=minID
        # ~ counts=[(self.grid==i).sum() for i in range(n)]
        # ~ print(counts)
    def mutate(self):
        g=self.grid
        n=len(g)
        i=random.randrange(n)
        j=random.randrange(n)
        x=random.randrange(n)
        y=random.randrange(n)
        g[i,j],g[x,y]=g[x,y],g[i,j]
    
    def fitness(self):
        g=self.grid
        # ~ c=(numpy.diff(g,1,0)==0).sum()
        # ~ c+=(numpy.diff(g,1,1)==0).sum()
        v=0
        for i in range(len(g)):
            island,count=label(g==i)
            m=max((island==i+1).sum() for i in range(count)) 
            v+=m/count
        return v
        
        
        
    def __str__(self):
        output=""
        for row in self.grid:
            for cell in row:
                output+=f"{cell} "
            output+="\n"
        return output


def getPopulation(n=100):
    return [CountyMap() for i in range(n)]
    
def getBest(population,n=25):
    population.sort(key=CountyMap.fitness)
    return population[-n:]

def rePopulate(pop):
    n=[]
    while len(pop)+len(n)<100:
        a=random.choice(pop)
        b=random.choice(pop)
        n.append(a.cross(b))
    return n+pop

def mutatePopulation(pop):
    for p in pop[:-10]:
        for i in range(1):
            p.mutate()

def main():
    # ~ a=CountyMap(10)
    # ~ b=CountyMap(10)
    # ~ c=a.cross(b)
    # ~ print(a)
    # ~ print(b)
    # ~ print(c)


    # ~ a=CountyMap(4)
    # ~ print(a)
    # ~ a.mutate()
    # ~ print(a)


    # ~ a=CountyMap(4)
    # ~ print(a)
    # ~ print(a.fitness())

    votes=numpy.uint8(numpy.round(numpy.random.normal(50, 10, (10,10))))
    print(votes)
    print(votes.sum())
    pop=getPopulation()
    while 1:
        pop=getBest(pop)
        print(pop[-1].fitness())
        print(pop[-1])
        pop=rePopulate(pop)
        mutatePopulation(pop)

if __name__ == "__main__":
    main()
    


