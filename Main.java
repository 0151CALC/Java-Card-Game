import java.util.*;

/**
*<h1>This is the Main class</h1>
*This class essentually just takes the basic input from the user in order to setup the game enviroment.
*For example this class asks for the number of human players, their names and how many computer players and the type of computer player. The class then creates a new instance of the Game class and passes in those variables entered by the user.
*@author Callum Clarke
*@version 1.0
*@since 2017-10-16
*/

public class Main { // Main Running Class

	/**
	* This is the main method of the program.
	*
	* @param args unused.
	* @return Nothing.
	*
	*/

	public static void main(String[] args) {
		
		int numOfHumanPlayers; // Initializing an integer variable to store the number of human players in the game.
		int numOfComputerPlayers; // Initializing an integer variable to store the number of computer players in the game.
		
		Scanner userInput = new Scanner(System.in); // Creating a Scanner object called userInput in order to get input from the user.
		
		System.out.print("How many human players do you want to play with?: "); // asking the user how many human players are playing.
		numOfHumanPlayers = userInput.nextInt(); // setting numOfHumanPlayers equal to the next integer value the user enters.
		while(numOfHumanPlayers < 1 || numOfHumanPlayers > 99) { // while the integer entered by the user is either less than 1 or greater then 4 exec the following.
			System.out.println("Please enter a number between 1 and 4: "); // ask the user to enter a valid integer.
			numOfHumanPlayers = userInput.nextInt(); // set numOfHumanPlayers equal to the next integer the user enters.
		}
		
		String[] playerNames = new String[numOfHumanPlayers]; //Creating a String array called playerNames and setting its length equal to numOfHumanPlayers. This will be used to store the name of each player.
		System.out.println(""); // print new line for formatting.
		for (int i = 0; i < numOfHumanPlayers; i++) { // run once for each player.
			System.out.printf("Please enter player %d's name: ", i + 1); // ask for each players name.
			playerNames[i] = userInput.next(); // add user input to the playerNames array in the correct position for that player.
		} 
		
		System.out.print("How many computer players do you want to play with?: "); // Same thing we did for the number of human players instead we are doing it for the number of computer players.
		numOfComputerPlayers = userInput.nextInt();
		while(numOfComputerPlayers < 1 || numOfComputerPlayers > 99) {
			System.out.println("Please enter a number between 1 and 4: ");
			numOfComputerPlayers = userInput.nextInt();
		}

		System.out.println("What type of computer player would you like to play against?\n1: Random, 2: Smart, 3: Predictable."); //Asking the user what type of computer they would like to play against.
		String computerType; // creating an empty string called computerType.
		int decicdedType = userInput.nextInt(); // creating an integer variable and setting it to the next integer the user enters.
		while ((decicdedType < 1) || (decicdedType > 3)) { // making sure the integer value entered is either 1 or 2.
			System.out.println("Please enter either '1', '2' or '3'."); // if its not ask for new integer.
			decicdedType = userInput.nextInt(); // set decicdedType equal the new integer the user enters.
		}
		
		if (decicdedType == 1) { // if the user entered 1.
			computerType = "predictable"; //set the empty string to the appropriate type.
		} else { //else the user must have entered 2.
			computerType = "random"; //set the empty string to the appropriate type.
		}
		
		System.out.println("You have chosen the " + computerType + " type of computer player. "); // confirm type chosen with user.
		Game mainGame = new Game(); // create a new instance of the Game class called mainGame;
		mainGame.startGame(playerNames, decicdedType, numOfComputerPlayers); // call the startGame method of class game.	
	}
}