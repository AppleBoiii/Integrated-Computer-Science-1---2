import random 

# [6, 1, 4, 3, 7, 2, 0, 5]
#  A  aB aC bD b  c  c  d
#  children = i ->  2*i+1, 2*i+2
#  (i-1) / 2 = parent
#heapify -> siftdown

# [3, 5, 7, 6, 1, 2, 4, 0]

#                 3
#         5               7
#     6       1       2       4
# 0

#                 7
#         6               4
#     5       1       2       3
# 0

def siftDown(m, i, noGoZone): #start at i, stop short of noGo
    leftIndex = 2*i+1
    rightIndex = 2*i+2
    swapIndex = i

    if leftIndex < noGoZone and leftIndex < len(m) and m[leftIndex] > m[swapIndex]:
        swapIndex = leftIndex

    if rightIndex < noGoZone and rightIndex < len(m) and m[rightIndex] > m[swapIndex]:
        swapIndex = rightIndex
    
    if swapIndex != i:
        m[swapIndex], m[i] = m[i], m[swapIndex]
        siftDown(m, swapIndex, noGoZone)

def heapify(m):
    middle = len(m)// 2 +1 
    while middle >= 0:
        siftDown(m, middle, len(m))
        middle -= 1

m = [i for i in range(13)]
random.shuffle(m)
print(m)
m = [3, 5, 7, 6, 1, 2, 4, 0]
heapify(m)
print(m)

