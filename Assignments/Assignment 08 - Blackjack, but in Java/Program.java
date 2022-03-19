import java.lang.*;
import java.util.*;
import java.awt.*;

public class Program
{	
	public static void blackjack(Deck deck, ArrayList playerHand, ArrayList dealerHand)
	{
		Scanner sc = new Scanner(System.in);
		deck.shuffle();
		
		deck.hit(playerHand);
		deck.hit(playerHand);
		
		deck.showHand(playerHand);
		deck.showValue(playerHand);
		
		deck.hit(dealerHand);
		deck.showHand(dealerHand);
		
		boolean playing = true;
		boolean stay = false;
		while(playing)
		{
			if(deck.handValue(dealerHand) < 17) deck.hit(dealerHand);
			
			if(stay) break;
			
			System.out.println("Do you want to hit? Yes / No (no == staying)");
			String input = sc.next();
			if(input.equalsIgnoreCase("yes"))
			{
			 deck.hit(playerHand);
			 deck.showHand(playerHand);
			}
			
			else stay = true;
			
			if(deck.handValue(dealerHand) > 21 || deck.handValue(playerHand) > 21) break;
		}
		
	}
	
	public static void main(String[] args)
	{
		//stuff for game
		Deck deck = new Deck();
		ArrayList playerHand = new ArrayList();
		ArrayList dealerHand = new ArrayList();
		boolean game = true;
		
		//start game
		blackjack(deck, playerHand, dealerHand);
		
		//objects should interface as LITTLE as possible
	}
}

class Deck
{
	private Scanner sc = new Scanner(System.in);
	private String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private String[] deck;
	
	public Deck(int x) //if i ever need to make a custom deck size
	{
		deck = new String[x];
	}
	
	public Deck()
	{
		deck = new String[52];
	}
	
	public int size() //Checks the size of the deck for any reason I might need it(like testing)
	{
		int size = deck.length;
		return size;
	}
	
	public int randomCard()
	{
		int min = 0;
		int max = 13;
		int range = max - min;
		
		int card = (int)(Math.random() * range) + min;
		
		return card;
	}
	
	public void shuffle()
	{
		int[] newList = new int[13]; //This is used to make sure each card is only drawn 4 times. 
		
		for(int i=0;i<size();i++)
		{
			int card = randomCard();
			for(int j=0;j<newList.length;j++)
			{
				while(newList[card] == 4) card = randomCard(); //picks a new card if a card has already been drawn 4 times
			}
			
			deck[i] = cards[card];
			newList[card]++;
		}
	}
	
	public void print()
	{
		System.out.print("[ ");
		for(int i=0;i<size()-1;i++)
		{
			System.out.print(deck[i] + ", ");
		}
		System.out.print("and "+deck[size()-1] + "]");
		System.out.println(""); 
	}
	
	public void hit(ArrayList hand) //hits
	{
		String[] newList = new String[size()-1];
		
		for(int i=0;i<newList.length;i++) newList[i] = deck[i];
		
		String card = deck[size()-1];
		deck = newList;
		
		hand.add(card);
	}
	
	public int handValue(ArrayList hand)
	{
		int sum = 0;
		
		String listString = String.join(", ", hand); //I can't parse ArrayList.get(i) to an int, so I use this to make the ArrayList a string
		String[] handArray = listString.split(",");  //Then I split that string into a normal array which I can use to parse to an int!
		
		for(int i=0;i<hand.size();i++)
		{
			if(hand.get(i).equals("J") || hand.get(i).equals("Q") || hand.get(i).equals("K")) sum += 10;
			else if(hand.get(i).equals("A"))
			{
				if(sum > 11) sum += 1;
				else sum += 11;
			}
			else
			{
				handArray[i] = handArray[i].trim(); //had a problem with spaces being in the nums, e.g. " 7" 
				sum += Integer.parseInt(handArray[i]);
			}
		}
		
		return sum;
	}
	
	public void showHand(ArrayList hand)
	{
		if(hand.size() == 1) System.out.println(hand.get(0));
		else
		{
			for(int i=0;i<hand.size()-1;i++)
			{
				System.out.print("You're hand consists of: ");
				System.out.print(hand.get(i)+", ");
			}
			
			System.out.print("and "+hand.get(hand.size()-1));
		}
		System.out.println();
		
	}
	
	public void showValue(ArrayList hand)
	{
		System.out.println("Your hand is worth: "+handValue(hand));
	}
	
	public void result(ArrayList playerHand, ArrayList dealerHand)
	{
		int playerSum = handValue(playerHand);
		int dealerSum = handValue(dealerHand);
		//CHANGE HOW THIS WORKS
		if(dealerSum > 21) System.out.println("Dealer bust.");
		else if(playerSum > 21) System.out.println("Player bust.");
		else if(playerSum > dealerSum) System.out.println("Player wins.");
		else if(playerSum == dealerSum) System.out.println("Push.");
		else System.out.println("Dealer wins.");
	}
}
