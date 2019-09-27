import java.util.ArrayList;
import java.util.Collections;

/**
*<h1>This is the Players class.</h1>
* This class creates all the nesscery human and computer players for the game.
* 
*@author Callum Clarke
*@version 1.1
*@since 2017-10-16
*
*/

public class Players { // Player Implementation Class

	private int currentCardNumber = 0;
	private int numberOfPlayers;
	
	ArrayList<Player> players = new ArrayList<Player>();
	
	/**
	* Constructor.
	* This is the constructor for the Players class.
	* The constructor takes five parameters.
	* Using these parameters the constructor generates the appropriate amount of human and computer players.
	* The players created are all passed in the nesscery parameters from the parameters supplied to the constructor.
	*
	* @param Integer numOfhumanPlayers (required) Number of human players to be generated.
	* @param Integer numOfcomputerPlayers (required) Number of computer players to be generated.
	* @param String[] humanNames (required) A String array containing the names of the human players to be generated.
	* @param Integer cardsPerPlayer (required) Number of cards that should be generated for each player generated.
	* @param Integer numberOfAttributes (required) Number of attributes that each card generated for each player should have.
	*/
	
	public Players(int numOfhumanPlayers, int numOfcomputerPlayers, String[] humanNames, int cardsPerPlayer, int numberOfAttributes) {
		
		System.out.printf("\nEach player will start with %d cards.\n", cardsPerPlayer);
	
		System.out.println("----------HUMAN PLAYERS----------");
		for (int i = 0; i < numOfhumanPlayers; i++) {
			players.add(new Player(humanNames[i], cardsPerPlayer, currentCardNumber, numberOfAttributes, true));
			currentCardNumber = currentCardNumber + cardsPerPlayer;
			System.out.printf("%d) %s. \n", i +1, humanNames[i]);
		}
		
		System.out.println("--------COMPUTER PLAYERS--------");
		for (int i = 0; i < numOfcomputerPlayers; i++) {
			players.add(new Player("Computer " + Integer.toString(i + 1), cardsPerPlayer, currentCardNumber, numberOfAttributes, false));
			currentCardNumber = currentCardNumber + cardsPerPlayer;
			System.out.printf("%d) Computer %d. \n", i + 1, i + 1);
		}
	}
	
	/**
	* This method returns the player contained in the players ArrayList at the specified index.
	* @return Player The object of the class Player contained at the specified index in the players ArrayList/
	*/
	
	public Player getPlayer(int playerIndex) {
		return players.get(playerIndex);
	}
}