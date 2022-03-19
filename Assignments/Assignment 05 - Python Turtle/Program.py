import turtle
import math
import random

def drawTriangle():
	triangle = turtle.Turtle()
	triangle.speed(0)
	
	for i in range(25):
		x, y = getRan()
		i, j = posOrNeg()
		triangle.penup()
		triangle.setpos(x*i, y*j)
		triangle.pendown()
	
		triangle.forward(40)
		triangle.left(110)
		triangle.forward(60)
		triangle.left(140)
		triangle.forward(60)

def circleParams(radius, n):
	sumOfAngles = (n-2)*180
	angle = sumOfAngles/n
	
	sideLength = ((math.pi*radius)/(n/2))
	
	return angle, sideLength
	
def drawCircle(circle, radius, n):
	x, y = getRan(1,20)
	i, j = posOrNeg()
	circle.setpos(x*i, y*j)
	angle, sideLength = circleParams(radius, n)
	
	circle.speed(0)
	for i in range(100, 105):
		x = circle.xcor()+x+5
		y = circle.ycor()+x+5
		
		
		circle.penup()
		circle.forward(radius)
		circle.left(90)
		
		
		circle.pendown()
		
		for i in range (n):
			circle.forward(sideLength)
			circle.left(180-angle)
			
	circle.penup()
	circle.setposition(0, 0)
	circle.left(270)
def drawParallelogram(bob1, bob2, a, b, angle):
	
	bob1.penup()
	
	x, y = getRan(10, 50)
	i, j = posOrNeg()
	bob1.setpos(x*i, y*j)
	
	bob2.penup()
	#bob2.left(bob2.towards(bob1.xcor(), bob1.ycor()))
	bob2.setposition(bob1.xcor(), bob1.ycor())
	bob2.left(180)
	
	bob1.pendown() 
	bob2.pendown()
	
	halfA = a/2
	bob1.forward(halfA)
	bob2.forward(halfA)
	
	bob1.right(180-angle)
	bob2.left(angle)
	bob1.forward(b)
	bob2.forward(b)
	
	bob1.right(angle)
	bob2.left(180-angle)
	bob1.forward(halfA)
	bob2.forward(halfA)
	
	bob1.penup()
	bob2.penup()
	
def getParallelogramArea(a, b, angle):
	angle = 180 - angle
	radians = (angle*math.pi)/180

	height = b*math.sin(radians)
	return a*height

def posOrNeg():
	x = random.randint(1,2)
	if x == 1:
		x = -1
	
	y = random.randint(1,2)
	if y == 1:
		y = -1
		
	return x,y

def getRan(j = 1, k = 100):
	ran1 = random.randint(j, k)
	ran2 = random.randint(j, k)
	
	return ran1, ran2

def main():
	
	s = turtle.getscreen()
	bob = turtle.Turtle()
	bob.penup()
	rgb = ["blue" , "green" , "pink" , "orange", "yellow", "brown"]
	color = random.randint(0, len(rgb)-1)
	bob.color(rgb[color])
	
	bob2 = turtle.Turtle()
	rgb2 = ["blue" , "green" , "pink" , "orange", "yellow", "brown"]
	color2 = random.randint(0, len(rgb)-1)
	bob2.color(rgb2[color2])
	bob2.penup()
	
	drawTriangle()

	
	r, n = getRan(10, 80)
	drawCircle(bob, r, n)
	
	a, b, = getRan(10, 50)
	null, angle = getRan(1, 90)
	drawParallelogram(bob, bob2, 100, 45, 135)
	
	area = getParallelogramArea(40, 10, 135)
	drawCircle(bob, area, 20)
	

	while True:
		bob.left(0)
		
if __name__ == '__main__':
	main()
