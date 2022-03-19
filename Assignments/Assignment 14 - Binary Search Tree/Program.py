import random
import time
import csv

random.seed(420)
maxStuff = 1000000000000
f = open("timeAdds2.txt", "w")

# trees -> binary trees -> binary search trees
#Assignment
#Implement a tree
#Add 2^i things to tree (same random things each time)
#Make sure random numbers have big enough range
#Benchmark the time and height 
#do a little write up 

#things a tree does
#adds, deletes, and contains
#left nodes are less than the Mother Node, right nodes are greater
#here is a article I used to try and understand delete
#https://www.geeksforgeeks.org/binary-search-tree-data-structure/

class BST:
    def __init__(self, value = None, left = None, right = None):
        self.value = value 
        # if left and right:
        #     left = BST(left)
        #     right = BST(right)

        #     if left.value > right.value:
        #         left, right = right, left
        

        self.left = left
        self.right = right
        self.size = 1
        self.prev = None
    
    def __str__(self):
        x = ""
        if self.left != None:
            x += f"[{self.left}]" #this calls the __str__ func for self.left
        
        x += str(self.value)

        if self.right != None:
            x += f"({self.right})"

        return x

    def __add__(self, value):
        #if the value is greater than the root, go right. if less, go left. keep doing this
        #until there isn't a child node. then the value to be added becomes that child node. 
        #self.right + value or self.left + value are recursive, they call this function for the self.right node
        if self.value is None:
            self.value = value
        if value < self.value:
            self.size += 1
            if self.left:
                self.left + value
            else:
                self.left = BST(value)
        
        elif value > self.value:
            self.size += 1 
            if self.right:
                self.right + value
            else:
                self.right = BST(value)
        
        else:
            pass
            
        return self  
    def __sub__(self, value):
        #uses inorder traversal (had to google it)
        #uses recursion to either go left or right to get to node
        if self is None:
            return None
        if self.left is None and value < self.value:
            return None
        if self.right is None and value > self.value:
            return None

        if value > self.value:
            self.size -= 1
            self.right.prev = self
            self.right - value
        elif value < self.value:
            self.size -= 1
            self.left.prev = self
            self.left - value
        
        else:
            if not self.left and not self.right:    #CASE 1 - node to delete is a leaf node
                if self.prev.left.value == self.value:
                    self.prev.left = None
                else:
                    self.prev.right = None

            elif self.left and not self.right:         #CASE 2 - node to delete has one child
                self.prev.left = self.left
                self.left = None
            elif self.right and not self.left:
                self.prev.right = self.right 
                self.right = None
            
            else:   #CASE 3 - node has two children
                # print("here.")
                pred = self.left
                if self.left.right:
                    pred = self.left.right
                    self.left.right = None
                    self.value = pred.value
                else:
                    # print("Boink.")
                    self.left = None
                    self.value = pred.value
        
        return self

    def __len__(self):
        return self.size
    
    def height(self): #just go left and right, and the height is whichever one is longest
        if self is None:
            return 0
        
        left, right = 0, 0

        if self.left:
            left = self.left.height()
        if self.right:
            right = self.right.height()

        if left > right:
            return left + 1
        else:
            return right + 1
            
    def contains(self, value):
        if self.value == value:
            return True
        
        elif value > self.value:
            return self.right != None and self.right.contains(value)
        
        elif value < self.value:
            return self.left != None and self.left.contains(value)

    def min(self):
        min = self
        if self is None:
            return None

        else:
            if self.left:
                min = self.left.min()
            elif self.right:
                min = self.right.min

        return min

    def clear(self):
        while self.size != 0:
            mini = self.min()
            self - mini.value
        return self

def benchmark(tree, f):
    n = 0
    while True:
        start = time.time()
        for i in range(2**n):
            tree + random.randrange(0, maxStuff)
        end = time.time()
        deltaTime = end - start
        f.write(f"{2**n}, {deltaTime}, {tree.height()}\n")
        print(deltaTime)

        if deltaTime > 200:
            print("Breaking..")
            break
        n += 1
    print("Finishing up..")
    f.close()
    return 0
        
# tree = BST()
# benchmark(tree, f)

tree = BST(50) + 30 + 70 + 20 + 40 + 60 + 80

tree.clear()
print(tree)
print("Finished.")


