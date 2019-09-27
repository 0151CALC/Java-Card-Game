import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
*<h1>This is the Card class</h1>
* This class is used to create all the objects and variables nessacery for each object of the class Card that may be created.
* For instance all the attributes for each Card are created here.
*
*@author Callum Clarke
*@version 1.1
*@since 2017-10-16
*
*/

public class Card { // Card Implementation Class
	
	private String cardName;
	private Attribute[] cardAttributes;
	private int numberOfAttributes;
	
	/**
	* Constructor.
	* Takes two parameters called name and numberOfAttributes.
	* The constructor also generates the attributes for each card that is created.
	* This is done by a for loop that executes for the value of numberOfAttributes. On each cycle a new Card object is created and is passed a name and a random value of between 0 and 9.
	* The constructor already contains the names of the attributes that will be created in a String array.
	*@param name (required) Name of the card to be created.
	*@param numberOfAttributes (required) Number of attributes that the card created will have generated for it.
	*/

	public Card(String name, int numberOfAttributes) {
		cardAttributes = new Attribute[numberOfAttributes];
		this.numberOfAttributes = numberOfAttributes;
		cardName = name;
		String[] cardAttributeNames = {"Strength", "Intelligence", "Speed", "Luck"};
		for (int i = 0; i < cardAttributes.length; i++) { // creates and adds objects of class Attribute to the cardAttributes array.
			cardAttributes[i] = new Attribute(cardAttributeNames[i], ThreadLocalRandom.current().nextInt(0, 9 + 1));
		}	
	}
	
	/**
	* This method returns the name of the attribute at the specified index.
	* @param Int index (required) The index of the attribute to return the name of.
	* @return String The name of the attribute specified by index.
	*/
	
	public String getAttributeName(int index) {
		return cardAttributes[index].getName();
	}
	
	/**
	* This method returns the value of the attribute at the specified index.
	* @param Int index (required) The index of the attribute to return the value of.
	* @return Int The value of the attribute specified by index.
	*/
	
	public int getAttributeValue(int index) {
		return cardAttributes[index].getValue();
	}
	
	/**
	* This method returns the value of every attribute held by this card in an Integer array.
	* @return Int[] An integer array of the values of all attributes held by this card.
	*/
	
	protected int[] getCardAttributeValues() {
		int[] attributeValues = new int[numberOfAttributes];
		for (int i = 0; i < numberOfAttributes; i++) {
			attributeValues[i] = getAttributeValue(i);
		}
		return attributeValues;
	}
}