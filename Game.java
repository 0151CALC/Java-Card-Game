import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
*<h1>This is the Game class.</h1>
*This class is where almost all the operations relating to the game are first called and where the main game loop runs.
*
*@author Callum Clarke
*@version 1.0
*@since 2017-10-16
*
*/

public class Game { // Game Simulation Class
	
	private static final int CARDSPERPLAYER = 100;
	private static final int NUMBEROFATTRIBUTES = 4;
	
	/**
	*startGame Method.
	*
	*This is where the main game variables, objects and ArrayLists are initialized.
	*This is also where the main game loop runs and where all the logic for the game is.
	*
	*@param names (required) An array of type String that contains the names of all the human players that are going to be playing in the game.
	*@param type (required) An integer value between 1 and 3 which determines the type of computer player that will be used thoughtout the game.
	*@param numberOfComputerPlayers (required) An integer value that represents the number of computer players that will be simulated in this game.
	*/
	
	public void startGame(String[] names, int type, int numberOfComputerPlayers) {
		
		String[] humanNames = names; // Initialises a String array called humanNames and sets it equal to the array names that was passed in as a parameter for this method.
		int computerType = type; // Initialises an Integer called computerType and sets it equal to the Integer type that was passed in as a parameter for this method.
		int numberOfHumanPlayers = humanNames.length; // Initialises an Integer called numberOfHumanPlayers and sets it equal to the length of the humanNames array. This stores the number of human players that we be playing in this game.
		int totalNumberOfPlayers = numberOfHumanPlayers + numberOfComputerPlayers; // Initialises an Integer value called totalNumberOfPlayers and sets it equal to numberOfHumanPlayers + numberOfComputerPlayers.
		boolean gameFinished = false; // Initialises a boolean variable called gameFinished that will be set to the false.
		int currentPlayer = 0; // Initialises an integer value called currentPlayer and sets it equal to 0. This is used to keep track of whos turn it is for each round.
		int roundNumber = 1; // Initialises an integer value called roundNumber and sets it equal to 1. This is used to keep track of what round we are currently on.
		int numberOfPlayersWithCards = totalNumberOfPlayers; // Initialises an integer value called numberOfPlayersWithCards
		
		Players players = new Players (numberOfHumanPlayers, numberOfComputerPlayers, humanNames, CARDSPERPLAYER, NUMBEROFATTRIBUTES); // Initialises an object of the Players class called players and passes in all the required parameters.
		
		String[] playerNames = new String[totalNumberOfPlayers]; // Initialises a String array called playerNames which will be used to hold the names of all the players in the game including computer players. The length of this array is set to the number of total players in the array.
		
		for (int i = 0; i < totalNumberOfPlayers; i++) { // Populating the playerNames array.
			playerNames[i] = players.getPlayer(i).getName();
		}
		
		System.out.println("Starting Game...");
		
		while (!gameFinished) { // Main game while loop.
			
			ArrayList<Card> playerCards = new ArrayList<Card>(); // An arraylist to hold the all the players on top cards for this round.
			ArrayList<Integer> specificPlayerAttributes = new ArrayList<Integer>(); // An arraylist to hold the value of each players attribute for the attribute chosen to play with by the current player.
			ArrayList<String> roundWinnerNames = new ArrayList<String>(); // An arraylist to hold the names of the winners of the round (If there is a tie that is otherwise this will only contain one name).
			ArrayList<Integer> roundWinnerIndexValues = new ArrayList<Integer>(); // An arraylist to hold the index values of the winning players of the round. (If there is a tie that is otherwise this will only contain one value).
			Scanner userInput = new Scanner(System.in); // Scanner to get user input to choose the attribute to play with.
			
			if (players.getPlayer(currentPlayer).hasCards()) {
				
				int chosenAttribute = ThreadLocalRandom.current().nextInt(0, NUMBEROFATTRIBUTES); // Initialises an Integer value called chosenAttribute which will at first be set to a random integer between 0 and the number of attributes.
				int roundWinner = -1; // Initialises an Integer value used to hold the winner of the round.
				int highestValue = -1; // Initialises an Integer value used to hold the highest value of the attribute selected from every player.
				String playingPlayersName = players.getPlayer(currentPlayer).getName(); // Gets the playing players name for this round.
				String chosenAttributeName; // Initialises a String that will be used to the hold the name of the attribute the player has chosen to play with.
				
				for(int i = 0; i < totalNumberOfPlayers; i++) { // runs once for each player in the game.
					if (players.getPlayer(i).hasCards()) { // if the player has cards to play with.
						playerCards.add(players.getPlayer(i).returnOnTopCard()); // get their on top card and add it to the playerCards array.
					} else { // if the player does not have cards to play with and is out of the game.
						playerCards.add(new Card("placeHolderCard", NUMBEROFATTRIBUTES)); // create a temp Card called placeHolderCard that is only generated so that the index's of players and players cards in the playerCards array don't get mixed up.
					}
				}	
				
				int[] playerCardAttributeValues = playerCards.get(currentPlayer).getCardAttributeValues(); // Initialises an Int array that is set to the current players on top card attribute values.
				
				System.out.printf("---------------------------- ROUND %d ----------------------------\n\n", roundNumber);
				
				for (int i = 0; i < totalNumberOfPlayers; i++) { // prints the amount of cards that each player had left to the console.
					System.out.printf("%s has %d cards left\n", players.getPlayer(i).getName(), players.getPlayer(i).getAmmountofCards());
				}
				
				System.out.printf("\nIt is %s's turn to play.", playingPlayersName);
				
				System.out.printf("\n%s's card attributes for this round are:\n", playingPlayersName);
				
				for (int i = 0; i < playerCardAttributeValues.length; i++) { // prints the current players on top card to the console. (e.g the name of the all attributes and their values).
					System.out.printf("\n%s : %d\n", playerCards.get(currentPlayer).getAttributeName(i), playerCardAttributeValues[i]);
				}
				
				if (players.getPlayer(currentPlayer).isHuman()) { // If the player is a human.
					System.out.println("What attribute would you like to play with? (from 1 onwards starting with the first attribute.)"); // ask the user what attribute they would like to play with.
					chosenAttribute = userInput.nextInt(); // set chosenAttribute to the next int the user types to the console.
					while (chosenAttribute < 1 || chosenAttribute > NUMBEROFATTRIBUTES) { // if the integer entered by the user is less than one or greater then the number of attributes for the game.
						System.out.println("Please enter a valid integer value of between 1 and 4."); // ask for a new integer.
						chosenAttribute = userInput.nextInt(); // set chosenAttribute equal to the next int the user types to the console.
					}
					chosenAttribute = chosenAttribute - 1; // decrement chosenAttribute by 1 so that we can use it as an index pointer.
				} else { // else if the player is a computer.
					if (computerType == 2) { // if the user had chosen to play with the smart type of computers.
						int highestAttributeValue = 0; // Initialise an integer called highestAttributeValue that will be used to hold the value of the highest attribute value held by the player on their card.
						int highestAttributeIndex = -1; // Initialise an integer called highestAttributeIndex that will be used to hold the index of the attribute of the highest value held by the player on their card.
						for (int i = 0; i < playerCardAttributeValues.length; i++) { // run once for each attribute.
							if (playerCardAttributeValues[i] > highestAttributeValue) { // if the value of the attribute is greater than highestAttributeValue. then set highestAttributeValue equal to it.
								highestAttributeValue = playerCardAttributeValues[i];
								highestAttributeIndex = i; // set the index too.
							}
						}
						chosenAttribute = highestAttributeIndex;
					} else if (computerType == 3) { 
						chosenAttribute = 3; // computer always chooses the attribute "luck".
					}
					System.out.printf("%s has chosen to player with the attribute %s\n", playingPlayersName, playerCards.get(currentPlayer).getAttributeName(chosenAttribute));
				}
				
				chosenAttributeName = playerCards.get(currentPlayer).getAttributeName(chosenAttribute); // get the name of the attribute chosen.
				
				for (int i = 0; i < totalNumberOfPlayers; i++) { // gets the values of each players attribute from their card for the chosen attribute.
					specificPlayerAttributes.add(playerCards.get(i).getAttributeValue(chosenAttribute));
				}
				
				for (int i = 0; i < totalNumberOfPlayers; i++) { // runs once for each player.
					if (players.getPlayer(i).hasCards()) {
						System.out.printf("%s had the value of %d for %s.\n", playerNames[i], specificPlayerAttributes.get(i), chosenAttributeName); // prints the value that each player had for that attribute on their card.
					}
				}
				
				for (int i = 0; i < totalNumberOfPlayers; i++) { //sets highestValue equal to the highest value of the chosen attribute of all players.
					if (players.getPlayer(i).hasCards()) {
						if (specificPlayerAttributes.get(i) > highestValue) { 
							highestValue = specificPlayerAttributes.get(i);
						}	
					}
				}
				
				for (int i = 0; i < totalNumberOfPlayers; i++) { // runs once for each player.
					if (playerCards.get(i).getAttributeValue(chosenAttribute) == highestValue && players.getPlayer(i).hasCards()) { //if the value of the chosen attribute for that player is equal to highestValue and the player hadCards to play with (to make sure placeholder cards don't count).						roundWinnerNames.add(playerNames[i]);
						roundWinnerIndexValues.add(i); // add the index value of the player to the roundWinnerIndexValues array.
						roundWinner = i; // set round will equal to i (for if only one player wins).
					}
				}
				
				if (roundWinnerNames.size() == 1) { // if the round isn't tied print out the name of the winning player.
					System.out.printf("\n%s has won the round with the value of %d for %s. \n", roundWinnerNames.get(0), highestValue, chosenAttributeName);
				} else if (roundWinnerNames.size() > 1) { // if the round is tied print out the names of each tied player in a formatted string.
					System.out.print("\nThis round is tied since ");
					if (roundWinnerNames.size() > 2) {
						for (int i = 2; i < roundWinnerNames.size(); i++) {
							System.out.printf("%s, ", roundWinnerNames.get(i));
						}
					}
					System.out.printf("%s and %s are tied with the value of %d for %s.\n", roundWinnerNames.get(1), roundWinnerNames.get(0), highestValue, chosenAttributeName);
					int tempWinner = ThreadLocalRandom.current().nextInt(0, roundWinnerIndexValues.size()); // select a random winner.
					System.out.printf("%s had been chosen as the random winner for this round.\n", playerNames[roundWinnerIndexValues.get(tempWinner)]);
					roundWinner = roundWinnerIndexValues.get(tempWinner); //set round winner equal to the index value of the selected random winner.
				}
				
				for (int i = 0; i < totalNumberOfPlayers; i++) { // runs once for each player.
					if (players.getPlayer(i).hasCards()) { // if the player has cards.
						players.getPlayer(i).removeFirstCard(); // remove their first card.
					}
				}
				
				for (int i = 0; i < totalNumberOfPlayers; i++) { // runs once for each player.
					if (players.getPlayer(i).hasCards()) { // if the player has cards.
						players.getPlayer(roundWinner).addCard(playerCards.get(i)); // add all the cards to the back of the winnning players deck.
					}
				}
				
				for (int i = 0; i < totalNumberOfPlayers; i++) { // runs once for each player.
					if (players.getPlayer(i).playerCards.isEmpty() && players.getPlayer(i).hasCards()) { // if the player had no more cards to play with and the value of hasCards for that player is equal to true.
						System.out.printf("\n%s has ran out of cards to play with and will now be removed from the game. Good luck next time %s!.\n", players.getPlayer(i).getName(), players.getPlayer(i).getName()); // print goodbye to the player.
						players.getPlayer(i).setHasCards(false); // set the players hadCards boolean to false.
						numberOfPlayersWithCards--; // decrement numberOfPlayersWithCards.
					}
				}
				
				playerCards.clear(); // clear all the arraylists.
				roundWinnerNames.clear();
				specificPlayerAttributes.clear();
				roundWinnerIndexValues.clear();
				
				currentPlayer++; // increment currentPlayer.
				roundNumber++; // increment roundNumber
				
			} else { // else the player hasn't got cards so skip the player.
				System.out.printf("\n%s hasn't got any cards to play with so we will skip them and go to the next player\n", playerNames[currentPlayer]);
				currentPlayer++; // increment currentPlayer.
				
			}
			
			if (currentPlayer == totalNumberOfPlayers) { // if we done a cycle of all the players then skip back to the start and carry on.
				currentPlayer = 0;
			}
			
			if (numberOfPlayersWithCards == 1) { // if there is only one player left.
				for (int i = 0; i < totalNumberOfPlayers; i++) { // find winning player.
					if (players.getPlayer(i).hasCards()) {
						System.out.printf("\nCongratulations %s you have won the game!", playerNames[i]); // print congradulations message.
						gameFinished = true; // game finished.
						break; // break out of loop no need to check other players.
					}
				}
			}	
		}	
	}
}