import java.util.*;

/**
* <h1>This is the Player class.</h1>
* This class contains all the variables and methods needed for each player.
* This class also generates the Cards for each player.
*@author Callum Clarke
*@version 1.1
*@since 2017-10-16
*
*/

public class Player {

	protected String name;
	protected int cardsPerPlayer;
	protected int startFrom;
	protected int numberOfAttributes;
	protected boolean isHuman;
	protected boolean hasCards;
	LinkedList<Card> playerCards = new LinkedList<Card>();
	
	/**
	* Constructor.
	* This is the constructor for the Player class.
	* The constructor takes five parameters. 
	* Using these parameters the constructor generates a number of cards for the player. 
	* This is done by creating a new object of the class Card and then passing it the name of the card to create and the number of attributes that the card will have.
	* This is done in a for loop to create multiple cards. The cards are named "Card :" and then the total number of cards created at that time. This is then incremented.
	* So the name for the first card created is "Card: 1" and for the second it is "Card: 2" and so on and so on.
	*
	*
	*@param String name (required) The name of the player.
	*@param Int cardsPerPlayer (required) The ammount of card to be generated for the player.
	*@param Int startFrom (required) The integer value of where to start naming the cards from for the player.
	*@param Int numberOfAttributes (required) The number of attributes that will be generated for each card generated.
	*@param boolean isHuman (required) A boolean variable related to whether or not the player being created is a human player or not. e.g True = Human, False = computer.
	*/
	
	public Player(String name, int cardsPerPlayer, int startFrom, int numberOfAttributes, boolean isHuman) {
		this.name = name;
		this.cardsPerPlayer = cardsPerPlayer;
		this.startFrom = startFrom;
		this.numberOfAttributes = numberOfAttributes;
		this.isHuman = isHuman;
		hasCards = true;
		
		for (int i = 0; i < cardsPerPlayer; i++) { // Generates the appropriate number of cards for the player.
			playerCards.add(new Card("Card " + Integer.toString(startFrom + 1), numberOfAttributes));
			startFrom++;
		}
	} 
	
	/**
	* This method returns the name of the player.
	* @return String The name of the player.
	*/
	
	protected String getName() {
		return name;
	}
	
	/**
	* This method returns either True or False for if the player is a human or not.
	* @return Boolean Returns either True or False for if the player is a human or not.
	*/
	
	protected boolean isHuman() {
		return isHuman;
	}
	
	/**
	* Removes the first card in the playerCards array.
	*/
	
	protected void removeFirstCard() {
		playerCards.removeFirst();
	}
	
	/**
	* Takes a parameter of an object of class Card.
	* This card is then appended to the end of the playerCards LinkedList. 
	* This effectively puts the card at the bottom of the players deck of cards.
	*
	*@param Card cardToAdd (required) The object of class Card to add to the end of the players deck of cards.
	*/
	
	protected void addCard(Card cardToAdd) {
		playerCards.addLast(cardToAdd);
	}
	
	/**
	* Returns the Card at the start of the players deck.
	* @return Card Card object at the start of the playerCards LinkedList.
	*/
	
	protected Card returnOnTopCard() {
		Card onTopCard = playerCards.getFirst();
		return onTopCard;
	}
	
	/**
	* returns the integer value of the amount of cards that the player has left in their deck.
	* @return Int Amount of cards the player has left.
	*/
	
	protected int getAmmountofCards() {
		return playerCards.size();
	}
	
	/**
	* Sets the hasCards boolean variable to either true or false;
	* @param Boolean value (required) The boolean value to set hasCards to.
	*/
	
	protected void setHasCards(boolean value) {
		hasCards = value;
	}
	
	/**
	* returns a boolean variable for wheather or not the player has cards in their deck to play with.
	* @return Boolean returns True if the player has at least one card in their playerCards LinkedList returns false otherwise.
	*/
	
	protected boolean hasCards() {
		return hasCards;
	}
}