#OOP: Marries data and behavior
#Structs: Group of data

#instance variables: suit , value
#constructor: stores stuff into the object
#methods
from enum import Enum
import random

class Suit(Enum):
	SPADES = '\u2660'
	HEARTS = '\u2665'
	DIAMONDS = '\u2666'
	CLUBS = '\u2663'

class Card:
	def __init__(self, suit, value):
		self.suit = suit
		self.v = value
	
	def value(self):
		if self.v == "A":
			return 1
		elif self.v in "TJQK":
			return 10
		else:
			return int(self.v)
	
	def __str__(self):
		return self.v+self.suit.value
	def __repr__(self):
		return self.v+self.suit.value
		
class Deck:
	def __init__(self):
		self.cards=[]
		for suit in Suit:
			for value in "A23456789TJQK":
				if value == "T":
					value = "10"
				self.cards.append(Card(suit, value))
		self.shuffle()
	def shuffle(self):
		random.shuffle(self.cards)
	
	def draw(self):
		return self.cards.pop()
	
	def __str__(self):
		return str(self.cards)

class Hand:
	def __init__(self):
		self.cards=[]
	
	def add(self, card):
		return self.cards.append(card)
	
	def value(self):
		t=0
		hasAce = False
		for c in self.cards:
			t+=c.value()
			if c.value() == 1:
				hasAce = True
		
		if hasAce and t<=11:
			t+=10
		
		return t
	
	def showMasked(self):
		out="?? "
		for c in self.cards[1:]:
			out += str(c)+" "
		
		return out
	
	def __str__(self):
		return str(self.cards)			

class Game:
	def __init__(self):
		self.dealerHand = Hand()
		self.playerHand = Hand()
		self.deck = Deck()
		
		for i in range(2):
			self.dealerHand.add(self.deck.draw())
			self.playerHand.add(self.deck.draw())
		
		self.playerTurn()
		self.dealerTurn()
		self.results()
		
	def playerTurn(self):
		print(self)
		prompt = input("[hit] or [stay]? ")
		while prompt not in ["hit", "stay"]:
			prompt = input("[hit] or [stay]? ")
			
		if prompt == "hit":
			self.playerHand.add(self.deck.draw())
			if self.playerHand.value()>21:
				print(self)
				print("bust")
			else:
				self.playerTurn()
		
	def dealerTurn(self):
		while self.dealerHand.value()<=17:
			self.dealerHand.add(self.deck.draw())

	def results(self):
		print(self)
		if 21>=self.playerHand.value() > self.dealerHand.value():
			print("Player wins!")
		elif 21>=self.playerHand.value()==self.dealerHand.value():
			print("Push!")
		else:
			print("House wins!")
	def __str__(self):
		out = "DEALER: "+str(self.dealerHand.showMasked())
		out +="\nPLAYER: "+str(self.playerHand)
		
		return out

d = Deck()
h = Hand()
d.shuffle()
print(d)
print("")



h.add(d.draw())
h.add(d.draw())

'''
print(d)
print("")
print(h)
print(h.value())

print(h.showMasked())
'''

g = Game()

		 
