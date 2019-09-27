/**
*<h1>This is the Attribute class</h1>
* This class is used to store all the information relating to specific attributes such as its name and value.
*
*@author Callum Clarke
*@version 1.1
*@since 2017-10-16
*
*/

public class Attribute { // Card Attribute Class

		private String attributeName;
		private int attributeValue;
		
		/**
		*Constructor
		* Takes the name and value of the attribute as parameters and saves them to seperate variables in the class.
		*@param name (required) Name of the attribute.
		*@param value (required) Value of the attribute.
		*
		*/
		
		public Attribute(String name, int value) {
			attributeName = name;
			attributeValue = value;
		}
		
		/**
		* This method is used to print out the name and the value of the attribute to the console.
		*/
		
		public void printAttribute() {
			System.out.println(attributeName + ": " + Integer.toString(attributeValue));
		}
		
		/**
		* This method is used to return the name of the attribute.
		* @return String This returns the String attributeName.
		*/
		
		public String getName() {
			return attributeName;
		}
		
		/**
		* This method is used to return the value of the attribute.
		*@return Int This returns the Integer value attributeValue.
		*/
		
		public int getValue() {
			return attributeValue;
		}
}