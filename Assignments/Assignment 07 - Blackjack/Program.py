import numpy as np
import random as rand
import webbrowser as browser
#deck = "2, 3, 4, 5, 6, 7, 8, 9, 10, A, J, K , Q"
#deck = deck.split(",")*4
#hand = deck.pop()
#get deck boom

#REDO HITS WITH DECKS
def getDeck():
	
	deck = "2, 3, 4, 5, 6, 7, 8, 9, 10, A, J, K , Q"
	deck = deck.split(",")*4
	rand.shuffle(deck)
	
	
	return deck

def hit(deck, someoneHand):
	
	card = deck.pop()
	someoneHand.append(card)
	
	return someoneHand

def showHand(playerHand):

	hand = ""
	
	for i in range(len(playerHand)-1):
		hand += f"{playerHand[i]}, "
	
	#strPrint = "Your cards are, "
	
	if len(playerHand) > 1:
		strPrint = "Cards are "
		hand += f"and {playerHand[len(playerHand)-1]}"
	else:
		strPrint = "Card is "
		hand += f"{playerHand[0]}"
		
	return hand
	
def handValue(hand):
	handVal = 0
	aceCount = 0
	for i in range(len(hand)):
		
		try:
			hand[i] = int(hand[i])
			handVal += hand[i]
		except:
			if hand[i] == "A":
				aceCount += 1
			else:
				handVal += 10

	for i in range(aceCount):
		if handVal + 11 > 21:
			handVal += 1
		else:
			handVal += 11
	return handVal

def flipCards(playerHand, dealerHand):
	
	playerValue = handValue(playerHand)
	p = showHand(playerHand)
	
	dealerValue = handValue(dealerHand)
	d = showHand(dealerHand)
	
	if handValue(playerHand) == 21:
		print("BLACKJACK!!!!")

	print(f"\nYour cards add to {playerValue}. Your deck was "+p)
	print(f"\nThe dealer's cards added to {dealerValue}. Their deck was "+d)
	
	if playerValue > 21:
		print("BUST. Whoops, You just lost all your money.")
	
	elif dealerValue > playerValue:
		print("The dealer had a higher score. You lose.")
	
	elif dealerValue > 21 or playerValue > dealerValue:
		print("Congrats. You won.")
	else:
		print("It must have been a tie!")

def play(play):
	#deck, usedCards = getDeck()
	deck = getDeck()
	
	playerHand = []
	dealerHand = []
	stay = False
	
	blackJack = True
	
	dealerHand = hit(deck, dealerHand)
	dealerVal = handValue(dealerHand)
	a = "a "
	
	if showHand(dealerHand) == "A" or showHand(dealerHand) == "8":
		a = "an "
	
	print("The dealer has "+a+showHand(dealerHand))
	
	for i in range(2):
		playerHand = hit(deck,playerHand)
	
	print("\nYour hand is: "+showHand(playerHand))
	playerVal = handValue(playerHand)
	print(f"Your hand adds up to {playerVal}")
	
	while blackJack:
		if handValue(dealerHand) < 17:
			dealerHand = hit(deck, dealerHand)
			dealerVal = handValue(dealerHand)
			
		if stay:
			break
			
		choice = input("Type hit or stay: ")
		choice = choice.lower()
		
		if choice == "hit":
			playerHand = hit(deck, playerHand)
			playerVal = handValue(playerHand)
			print("\nYour hand is: "+showHand(playerHand))
			print(f"Your hand adds up to {handValue(playerHand)}\n")
			playerVal = handValue(playerHand)
		
		if choice == "stay":
			stay = True
		
		if dealerVal > 21 or playerVal > 21:
			break
	
	flipCards(playerHand, dealerHand)
	
	stop = input("Do you want to keep playing? Type yes, something not yes will quit: ")
	stop.lower()
		
	return stop == "yes"
def main():
	
	start = True
	while start:
		start = play(start)
	
if __name__ == "__main__":
	main()
