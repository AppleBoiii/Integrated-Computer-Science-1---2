#import random
#random.seed(1337)
#print([random.randrange(10) for i in range(10)]) 
#
# trees -> binary trees -> binary search trees
#Assignment
#Implement a tree
#Add 2^i things to tree (same random things each time)
#Make sure random numbers have big enough range
#Benchmark the time and height 
#do a little write up 


class TreeNode:
    def __init__(self, value, left = None, right = None):
        self.value = value
        self.left = left
        self.right = right
        self.size = 1
    
    def contains(self, value): #has big O(n)
        # return self.value == value or \
        #     (self.left != None and self.left.contains(value)) or \
        #         (self.right != None and self.right.contains(value))

        #this is O(log n)
        if self.value == value:
            return True
        elif value > self.value:
            #go right
            return self.right != None and self.right.contains(value)
        else:
            return self.left != None and self.left.contains(value)

    def __add__(self, value): #this lets you do TreeNode + 3, __add__ overrides the + sign
        #big O(log(n))
        if value < self.value:
            self.size += 1
            #go left
            if self.left:
                self.left + value #equivalent to self.add(self.left, value)
            
            else:
                self.left = TreeNode(value)

        elif value > self.value:
            self.size += 1
            #go right
            if self.right:
                self.right + value 
            
            else:
                self.right = TreeNode(value)

        else:
            pass
        
        return self

    def __len__(self): #O(1)
        return self.size
    
    def __str__(self):
        output = ""
        if self.left:
            output += "[%s]"%self.left
        
        output += str(self.value)
        
        if self.right:
            output += "(%s)"%self.right
        
        return output


a = TreeNode(5)
a + 4 + 8 + 2 + 1 + 4
print(a)
print(a.contains(100))
print(len(a))
#ArrayList Add (no upsize): O(1)
#Upsize: O(n)
#ArrayList Add Amortized: O(log(n))
#Tree Add: O(log(n))
#LinkedList Add with Last: O(1)

#ArrayList Delete: O(n)
#LinkedList Delete: O(n)
#Tree Delete: O(log(n))

