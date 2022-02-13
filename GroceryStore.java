/*
Nathan Pham
Java Assignment 3
W 11/19/2021
JDK Version 16
Honor Code:
"I have neither given nor received unauthorized aid in completing this work,
nor have I presented someone else's work as my own."

For this assignment 
- Implement OOP 
- Make sure the user only implement int inputs
- While the catalog of products is hard-coded, the program should allow an Admin to enter a new product(s) 
  to be added to the catalog at the command line at run time (i.e., command shell), 
  the Admin can add a new product after inputting the password:
  MCS3603.
- Note, make all prices right justified, meaning all prices are on the right side only
GroceryStore.V.3
 */

// PART 1 of Assignment 3

import java.util.Scanner; // we need to import this in order to get the user's input
import java.io.IOException;

public class GroceryStore { // Setting up the Java file
	
    // I will simply limit the amount of additional products added to the store
    
    // creating an int value to get the product ID
    public int number, count = 0; // number is the amount of products that the user wants
    						// count is the total amount of items
    public double total = 0, salesTaxes = 0.06;
    public final double fullPaymentDiscount = 0.95;
    // salesTaxes is 6% and fullPaymentDiscount is a 5% discount, labeled final so it cannot be changed
    // create total for total price in terms of double to be precise in 2 decimals
    
    // countItem is to keep track of amount of items
    public int [] countItem = new int[15]; //
    /* an int array that contains the following list with initial variables set to zero
    This is equal to this below
    countApple = 0, countPear = 0, countBanana = 0, countOrange = 0, countGrape = 0,  
    countPizza = 0;, countWater = 0, countPotato = 0, countTuna = 0, countShark = 0, etc; 
    creating a count to keep track of how many products that the user wants*/
    
    
    public int plans; // the plans variable doesn't need to be included in the constructor
    
    public int id = 1; // setting up some conditions
    
    // These variables are used for adding a new product
    // These String values will be used to implement the new items
    public String newID, newProduct = "", newPrice, newDetail = "";
    
    public int idCount = 10;
    public boolean underLimit = true;
    public double newCost;
    
	static void welcomeStatement() // static vs public methods, static can be called by itself, meaning the method itself
	{							   // For public methods, it requires an object to be called
		// ------------ Welcoming the User -----------------
	      
	      System.out.println("\t=========================");
	      System.out.println("\t|                       |");
	      System.out.println("\t|        Welcome        |");
	      System.out.println("\t|       to Walkart      |");
	      System.out.println("\t|       Version 3.0     |");
	      System.out.println("\t|                       |");
	      System.out.println("\t=========================");
	      // display output in system terminal
	}
    
    public static String getFirstName() // Method name getFirstName to get user's first name
    {
        Scanner myObj = new Scanner(System.in); 
        // creating a scanner object where the user can input their name
        String firstName = new String(); 
        // creating a string for users to input their name
        String answer = new String();
        // used for confirming the user's name
      do // let's the user first input their name
        {
          System.out.println("\nPlease enter your first name:  ");
          firstName = myObj.nextLine();
          System.out.println("\nIs " + firstName + " your name? (y or any other input)");
          while(true)
              {
              answer = myObj.nextLine();
              if (answer.equals("y") || answer.equals("Y")) // if the user confirms their first name, they would input "y"
                  {
                  return firstName; 
                  // getUserName() method is done here, this will return the value, which is the user's first name
                  }
              else // if the user inputs any other string, they will restart the loop
                 {
                    break; //goes back to the do while loop
                 }
              }
        }while(true);
    }
    
    public static String getLastName() // Method name getLastname to get user's last name
    {
        Scanner myObj = new Scanner(System.in); 
        // creating a scanner object where the user can input their last name
        String lastName = new String(); 
        // creating a string for users to input their last name
        String answer = new String();
        // used for confirming the user's last name
      do // let's the user first input their last name
        {
          System.out.println("\nPlease enter your last name:  ");
          lastName = myObj.nextLine();
          System.out.println("\nIs " + lastName + " your name? (y or any other input)");
          while(true)
              {
              answer = myObj.nextLine();
              if (answer.equals("y") || answer.equals("Y")) // if the user confirms their last name, they would input "y"
                  {
                  return lastName; 
                  // getUserName() method is done here, this will return the value, which is the user's first name
                  }
              else // if the user inputs any other string, they will restart the loop
                 {
                    break; //goes back to the do while loop
                 }
              }
        }while(true);
    }
    
    public static boolean checkIfNegative(int count) // this method checks if the count (amount) is negative because you can't have -1 apples
    {
    	if (count < 0)
    	{
    		return true; // boolean method returns true
    	}
    	else
    	{
    		return false; // boolean method returns false
    	}
    }
    
    public static void printMenuDetails() // simple code to system output
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\nFood list (please enter the number code to order the listed item, ex: 45): \n");
        System.out.println("SALE PRICE! A 10% DISCOUNT IS APPLIED TO ANY 10 SAME ITEM!");
        System.out.printf("\n%s\t%s\t\t%s\t\t%s", "ID", "Product name", "Price", "Description"); 
    }
    
    
    public static void main(String [] args) //starting up the program
    { 
    	
      welcomeStatement(); // I can call this method without creating an object because it is a static type of method
      
      // creating object called store to access variables from GroceryStore
      GroceryStore store = new GroceryStore();
      
      Scanner myObj = new Scanner(System.in); // scanning for next user input
      
      int j = 0; // this variable is for implementing new products into the system
      
      // Initalizing the array of objects, this will then go to class itemList where it arranges
      // its variables
      itemList [] productDetails = new itemList[15];
      itemList [] productPrice = new itemList[15];
      
      // ============================ HARD CODED ITEMS =====================================
      productDetails = new itemList[19];
      productDetails[0] = new itemList("1", "Apple", "$0.99", "A red pomaceous fruit, fully ripen fruit that weighs around 0.5lbs");
      productDetails[1] = new itemList("2", "Pear", "$0.50", "A green fruit that is sweeter than an apple, very ripe and weighs 0.4lbs");
      productDetails[2] = new itemList("3", "Banana", "$0.57", "A bundle of 7, a long yellow fruit, weighing 1.6lbs");
      productDetails[3] = new itemList("4", "Orange", "$1.20", "An orange fruit that is rich with vitamin C, weighing 0.6lbs");
      productDetails[4] = new itemList("5", "Grape", "$2.99", "A bag of purple grapes, it's sweet and fresh, weighing 2lbs");
      productDetails[5] = new itemList("6", "Pizza", "$10.99", "A large cheese pizza, no additional toppings included, weighs around 3lbs");
      productDetails[6] = new itemList("7", "Bottle Water", "$0.99", "A bottle of water, comes from the very top of Mt. Fuji, weighs 1lb");
      productDetails[7] = new itemList("8", "Potato Chip", "$0.99", "A bag of yellow cripsy chips, a little salty but tasty, weighs 0.5lbs");
      productDetails[8] = new itemList("9", "Raw Tuna", "$21.99", "A large raw fish that is kept nicely fresh from Japan and weighs 4lbs");
      productDetails[9] = new itemList("10", "Shark Fin", "$799.99", "A pair of fresh shark fins that was shipped from the Pacifics, weighs 3lb");
      
      productPrice[0] = new itemList(0.99); 
      productPrice[1] = new itemList(0.50); 
      productPrice[2] = new itemList(0.57); 
      productPrice[3] = new itemList(1.20);
      productPrice[4] = new itemList(2.99);
      productPrice[5] = new itemList(10.99);
      productPrice[6] = new itemList(0.99);
      productPrice[7] = new itemList(0.99);
      productPrice[8] = new itemList(21.99);
      productPrice[9] = new itemList(799.99);  
      
      
      //========================== ADD NEW PRODUCT ======================================
      
      for (int i = 0; i < 5; i++) // should 5 times because additional items are set to a limit of 5
      {
    	  store.newProduct = args[0+j];
    	  store.newPrice = args[1+j];
    	  store.newDetail = args[2+j];
    	  
    	  if(store.newProduct == null) // check if the string is empty
    	  {
    		  break; // if so stop
    	  }
    	  
    	  // idCount starts at 10, 10 in array is the 11th position
    	  store.newID = Integer.toString(store.idCount); // convert integer to String
		  
    	  store.newCost = Double.parseDouble(store.newPrice); // converting string to double
		  productPrice[store.idCount] = new itemList(store.newCost);
    	  
		  // implementing new product into system
		  
		  productDetails[store.idCount] = new itemList(store.newID, store.newProduct, "$" + store.newPrice, store.newDetail);
		  // Also, I am appending $ with price
		  
		  
		  
		  store.idCount++; // increase count, because count acts as an ID number, since the number of products are increasing
		  j += 3; // add 4 because after 4 positions in the array, it will represent new items
      }
      
      // ================================================================
      
      String input; // this variable will be used for confirming whether the input is int or not
      
      // Creating a scanner object where the user can input their name, which is a string in this form
      
      String firstName = getFirstName(); 
      // This will run the getFirstname() method and will return with a string once the method is finished
      // name can be any symbols including numbers, because user ID exist, etc
      
      String lastName = getLastName(); 

      
      // ---------------- Choosing The List of Items ------------------
      
      
      while(store.id != 0) // initially set 0 to id, 0 not equal to 11 which is true and will keep running the loop until
	    { // the user types in 11;
    	  
    	printMenuDetails();
    	  
    	for (int i = 0; i < productDetails.length; i++) // runs loop 15 times because I set the limit to 15 items
    	{
    		if (productDetails[i] == null) 
		  	{
		  		break; // will stop here if there is a null, meaning nothing in the list
		  	}
    		if(i >= 10)
    		{
    			productDetails[i].plusOneID(); // fixes printing of the id
    		}
    		
		  	productDetails[i].printList(); // prints the entire listed items for the users to read
		  	// I want my user's to see the list so that they do not need to scroll up in the output system
		  	
		  	if(i >= 10)
		  	{
		  		productDetails[i].minusOneID(); // fixes printing of the id, reverts to original
		  	}
		  	
    	}
    	System.out.print("\n0 to Pay\n"); 
    	
    	
	      input = myObj.next(); // read next String input, then the next line will convert it into an integer
			  try { // utilizing try and catch to check if the input is correct instead of using if and else
				  store.id = Integer.parseInt(input); // converts string to an int
	      }  								  		 // basically checks if the String is an Integer
			  catch (NumberFormatException ne) { // skips the exception error in the system output
	         System.out.println("Error, input is not a number, please try again!");
	         continue; // run the while loop again
	         }
			  
	        switch(store.id-1)
	        {
	        	  case -1:
		        	  System.out.println("\nLoading payout list. . .");
		          	  break;
	            case 0: // Apple $0.99
	          	  do 
	          	  { // run the code then check the conditions at the very end
	          		  System.out.println("How many Apples do you want? ");
	          		  // ask the user the amount of product that they want
	          		  	  store.number = myObj.nextInt();
		            	  store.countItem[0] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Apples"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[0] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Apples");
		                	  store.countItem[0] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[0]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break; // break to get out of the switch statements so it doesn't play the entire statements
	            case 1: // Pear $0.50
	          	  do 
	          	  {
	          		  System.out.println("How many Pears do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[1] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Pears"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[1] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Pears");
		                	  store.countItem[1] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[1]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 2: // Banana $0.57
	          	  do 
	          	  {
	          		  System.out.println("How many Bananas do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[2] += store.number; // you can add or remove, just simply put a minus sign to remove
		              System.out.println("Added " + store.number + " Bananas"); 
		              // tells the user that the system added the product to their list
		                  if(store.countItem[2] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Bananas");
		                	  store.countItem[2] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[2]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 3: // Orange $1.20
	          	  do 
	          	  {
	          		  System.out.println("How many Oranges do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[3] += store.number; // you can add or remove, just simply put a minus sign to remove
		              System.out.println("Added " + store.number + " Oranges"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[3] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Oranges");
		                	  store.countItem[3] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[3]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 4: // Grape $2.99
	          	  do 
	          	  {
	          		  System.out.println("How many Grapes do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[4] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Grapes"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[4] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Grapes");
		                	  store.countItem[4] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[4]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 5: // Pizza $10.99
	          	  do 
	          	  {
	          		  System.out.println("How many Pizzas do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[5] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Pizzas"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[5] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Pizzas");
		                	  store.countItem[5] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[5]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 6: // Water $0.99
	          	  do 
	          	  {
	          		  System.out.println("How many Bottle of Waters do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[6] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Bottle of Waters"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[6] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Bottle of Waters");
		                	  store.countItem[6] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[6]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 7: // Potato Chip $0.99
	          	  do 
	          	  {
	          		  System.out.println("How many Potato Chips do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[7] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Potato Chips"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[7] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Potato Chips");
		                	  store.countItem[7] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[7]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 8: // Tuna $21.99
	          	  do 
	          	  {
	          		  System.out.println("How many Raw Tunas do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
	          		  store.countItem[8] += store.number; // you can add or remove, just simply put a minus sign to remove
		                  System.out.println("Added " + store.number + " Raw Tunas"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[8] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Raw Japanaese Tunas");
		                	  store.countItem[8] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[8]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 9: // A pair of Shark fins $799.99
	          	  do 
	          	  {
	          		  
	          		  System.out.println("How many Pairs of Shark Fins do you want? ");
	          		  // ask the user the amount of product that they want
	          		  store.number = myObj.nextInt();
		            	  
	          		  store.countItem[9] += store.number; // you can add or remove, just simply put a minus sign to remove
		            	  
		                  System.out.println("Added " + store.number + " Pairs of Shark Fins"); 
		                  // tells the user that the system added the product to their list
		                  if(store.countItem[9] < 0)
	          		  {
		                	  System.out.println("\nYou cannot have a negative amount of Raw Japanaese Tunas");
		                	  store.countItem[9] = 0;
	          			  // reset amount to 0 if the user had negative amount of said product
	          		  }
	          	  }while(checkIfNegative(store.countItem[9]));
	          	  // check to make sure the user never has an amount below zero (0)
	                break;
	            case 10: // ======================== NEW ITEMS ============================
	            	if(productDetails[10] == null) // checking if said item exits...
	            	{
	            		System.out.println("Please try inputting a correct ID number...");
	            		continue; // goes back to grocery list
	            	}
	            	do 
		          	  {
	            		
		          		  productDetails[10].printQuestionAmount();
		          		  // ask the user the amount of product that they want
		          		  store.number = myObj.nextInt();
			            	  
		          		  store.countItem[10] += store.number; // you can add or remove, just simply put a minus sign to remove
			            	  
			                  productDetails[10].printAmountAdded(store.number);
			                  // tells the user that the system added the product to their list
			                  if(store.countItem[10] < 0)
		          		  {
			                	  productDetails[10].printIncorrectAmount(); 
			                	  store.countItem[10] = 0;
		          			  // reset amount to 0 if the user had negative amount of said product
		          		  }
		          	  }while(checkIfNegative(store.countItem[10]));
	            	break;
	            case 11:
	            	if(productDetails[11] == null) // checking if said item exits...
	            	{
	            		System.out.println("Please try inputting a correct ID number...");
	            		continue; // goes back to grocery list
	            	}
	            	do 
		          	  {
	            		
		          		  productDetails[11].printQuestionAmount();
		          		  // ask the user the amount of product that they want
		          		  store.number = myObj.nextInt();
			            	  
		          		  store.countItem[11] += store.number; // you can add or remove, just simply put a minus sign to remove
			            	  
			                  productDetails[11].printAmountAdded(store.number);
			                  // tells the user that the system added the product to their list
			                  if(store.countItem[11] < 0)
		          		  {
			                	  productDetails[11].printIncorrectAmount(); 
			                	  store.countItem[11] = 0;
		          			  // reset amount to 0 if the user had negative amount of said product
		          		  }
		          	  }while(checkIfNegative(store.countItem[11]));
	            	break;
	            case 12:
	            	if(productDetails[12] == null) // checking if said item exits...
	            	{
	            		System.out.println("Please try inputting a correct ID number...");
	            		continue; // goes back to grocery list
	            	}
	            	do 
		          	  {
	            		
		          		  productDetails[12].printQuestionAmount();
		          		  // ask the user the amount of product that they want
		          		  store.number = myObj.nextInt();
			            	  
		          		  store.countItem[12] += store.number; // you can add or remove, just simply put a minus sign to remove
			            	  
			                  productDetails[12].printAmountAdded(store.number);
			                  // tells the user that the system added the product to their list
			                  if(store.countItem[12] < 0)
		          		  {
			                	  productDetails[12].printIncorrectAmount(); 
			                	  store.countItem[12] = 0;
		          			  // reset amount to 0 if the user had negative amount of said product
		          		  }
		          	  }while(checkIfNegative(store.countItem[12]));
	            	break;
	            case 13:
	            	if(productDetails[13] == null) // checking if said item exits...
	            	{
	            		System.out.println("Please try inputting a correct ID number...");
	            		continue; // goes back to grocery list
	            	}
	            	do 
		          	  {
	            		
		          		  productDetails[13].printQuestionAmount();
		          		  // ask the user the amount of product that they want
		          		  store.number = myObj.nextInt();
			            	  
		          		  store.countItem[13] += store.number; // you can add or remove, just simply put a minus sign to remove
			            	  
			                  productDetails[13].printAmountAdded(store.number);
			                  // tells the user that the system added the product to their list
			                  if(store.countItem[13] < 0)
		          		  {
			                	  productDetails[13].printIncorrectAmount(); 
			                	  store.countItem[13] = 0;
		          			  // reset amount to 0 if the user had negative amount of said product
		          		  }
		          	  }while(checkIfNegative(store.countItem[13]));
	            	break;
	            case 14:
	            	if(productDetails[14] == null) // checking if said item exits...
	            	{
	            		System.out.println("Please try inputting a correct ID number...");
	            		continue; // goes back to grocery list
	            	}
	            	do 
		          	  {
	            		
		          		  productDetails[14].printQuestionAmount();
		          		  // ask the user the amount of product that they want
		          		  store.number = myObj.nextInt();
			            	  
		          		  store.countItem[14] += store.number; // you can add or remove, just simply put a minus sign to remove
			            	  
			                  productDetails[14].printAmountAdded(store.number);
			                  // tells the user that the system added the product to their list
			                  if(store.countItem[14] < 0)
		          		  {
			                	  productDetails[14].printIncorrectAmount(); 
			                	  store.countItem[14] = 0;
		          			  // reset amount to 0 if the user had negative amount of said product
		          		  }
		          	  }while(checkIfNegative(store.countItem[14]));
	            	break;
	            default: // If the users enters anything in terms of int
	          	  System.out.println("Please try inputting a correct ID number...");
	          	  break;
	        }
	    }
	
      
      for (int i = 0; i < store.countItem.length; i++) // length should be 10
      {
    	  // Remember, countItem is an int array, so most empty positions carries a value of 0
    	  store.count += store.countItem[i]; // adds all count 
      }
      // Calculating the total amount of items (how many items there are)
      
      //------------------ PRINTING INVOICE -----------------
      System.out.println("\nPrinting your receipt . . . . . .");
      
      System.out.println("\n\n#########################################################################");
      System.out.println("\tWalkart");
      System.out.println("Customer: " + firstName + " " + lastName); // Stating the customer's name
      System.out.println("\nIn Cart: " + store.count); // count total items in cart
      System.out.printf("\n%s\t%s\t%s\t\t%s\t\t", "Quantity", "ID", "Product name", "Price");  // structure
      
      
      if(store.countItem[0] > 0) // 1 Apple
      { // if the amount of said product is at least above 0, print in invoice
    	  productDetails[0].printItem(store.countItem[0]);
    	  store.total += productPrice[0].getDiscount(store.countItem[0]); // adding $0.99 * the amount of apples
    	  // the layout that it should display:
    	  // ID Product Name Price Description
    	  // Quantity: #
      }
      
      if(store.countItem[1] > 0) // 2 Pear
      {
    	  productDetails[1].printItem(store.countItem[1]);
    	  store.total += productPrice[1].getDiscount(store.countItem[1]); // adding $0.50 * amount
      } 
      
      if(store.countItem[2] > 0) // 3 Banana
      {
    	  productDetails[2].printItem(store.countItem[2]);
    	  store.total += productPrice[2].getDiscount(store.countItem[2]); // adding $0.57 * amount
      }
      
      if(store.countItem[3] > 0) // 4 Orange
      {
    	  productDetails[3].printItem(store.countItem[3]);
    	  store.total += productPrice[3].getDiscount(store.countItem[3]); // adding $1.20 * amount
      }
      
      if(store.countItem[4] > 0) // 5 Grape
      {
    	  productDetails[4].printItem(store.countItem[4]);
    	  store.total += productPrice[4].getDiscount(store.countItem[4]); // adding $2.99
      }
      
      if(store.countItem[5] > 0) // 6 Pizza
      {
    	  productDetails[5].printItem(store.countItem[5]);
    	  store.total += productPrice[5].getDiscount(store.countItem[5]); // adding $10.99
      }
      
      if(store.countItem[6] > 0) // 7 Water
      {
    	  productDetails[6].printItem(store.countItem[6]);
    	  store.total += productPrice[6].getDiscount(store.countItem[6]); // adding $0.99
      }
      
      if(store.countItem[7] > 0) // 8 Potato Chip
      {
    	  productDetails[7].printItem(store.countItem[7]);
    	  store.total += productPrice[7].getDiscount(store.countItem[7]); // adding $0.99
      }
      
      if(store.countItem[8] > 0) // 9 Raw Tuna
      {
    	  productDetails[8].printItem(store.countItem[8]);
    	  store.total += productPrice[8].getDiscount(store.countItem[8]); // adding $21.99
      }
      
      if(store.countItem[9] > 0) // 10 Shark Fin
      {
    	  productDetails[9].printItem(store.countItem[9]);
    	  store.total += productPrice[9].getDiscount(store.countItem[9]); // adding $799.99
      }
      
      // Below are New Items, similar format from above
      
      if(store.countItem[10] > 0) 
      {
    	  productDetails[10].plusOneID(); // quick fix to the program
    	  productDetails[10].printItem(store.countItem[10]);
    	  productDetails[10].minusOneID();
    	  store.total += productPrice[10].getDiscount(store.countItem[10]); 
      }
      
      if(store.countItem[11] > 0) 
      {
    	  productDetails[11].plusOneID();
    	  productDetails[11].printItem(store.countItem[11]);
    	  productDetails[11].minusOneID();
    	  store.total += productPrice[11].getDiscount(store.countItem[11]); 
      }
      
      if(store.countItem[12] > 0) 
      {
    	  productDetails[12].plusOneID();
    	  productDetails[12].printItem(store.countItem[12]);
    	  productDetails[12].minusOneID();
    	  store.total += productPrice[12].getDiscount(store.countItem[12]); 
      }
      
      if(store.countItem[13] > 0) 
      {
    	  productDetails[13].plusOneID();
    	  productDetails[13].printItem(store.countItem[13]);
    	  productDetails[13].minusOneID();
    	  store.total += productPrice[13].getDiscount(store.countItem[13]);
      }
      
      if(store.countItem[14] > 0) 
      {
    	  productDetails[14].plusOneID();
    	  productDetails[14].printItem(store.countItem[14]);
    	  productDetails[14].minusOneID();
    	  store.total += productPrice[14].getDiscount(store.countItem[14]);
      }

      // End of last item, because this is the set limit for the store
      
      /* NOTE, how discount and sales tax work is that, discount is applied and then sales tax is applied after
      Example: Total price: $10.00
      Total price after 10% discount: $9.00
      Applied tax on total price: $9.54 
      */
      

      if (store.total != 0.0) // will not display the price IF the customer did not buy anything
      {
    	//Showing subtotal before applying sales tax
          System.out.printf("\n\t\t\t\t      SUBTOTAL: $%.2f", store.total); // display subtotal of all items (excluding sales tax)
          
          // User is prompt to choose FUll payment or with installments
          // reorganized to fit the receipt
          System.out.println(""
          		+ "\n\nWould you like to choose Full payment or installment plan? "
          		+ "\n(1 for full payment and 0 for installment plan)"
          		+ "\nFull payment offers a 5% discount when paying full Price."
          		+ "\nInstallment plan offers a monthly fee of 10% of the price with"
          		+ "\nsales tax, this fee will last for 1 Year.\n");
         
    	  while(true)
          {
    		  input = myObj.next();
    		  try { // utilizing try and catch to check if the input is correct instead of using if and else
    			  store.plans = Integer.parseInt(input); // converts string to an int
              }  								  		 // basically checks if the String is an Integer
    		  catch (NumberFormatException ne) { // skips the exception error in the system output
                 System.out.println("Error, input is not a number, please try again!");
                 continue; // run the while loop again
                 }
    		  
        	  if(store.plans == 1)// Fullpayment plan
        	  {                                        
        		  store.total *= store.fullPaymentDiscount;         
        		  System.out.printf("\n\t\t\tApplied Discount Total: $%.2f", store.total);
        		  break;                                                      
        	  }
        	  else if(store.plans == 0) // Installment plan, 10% of the price
        	  {						
        		  store.total *= 0.1;
        		  break;
        	  }
        	  else
        	  {
        	  System.out.println("Error... Please enter 1 or 0\n"); // Error message
        	  }
          }
    	  
    	  store.salesTaxes = store.salesTaxes * store.total; // calculating salesTaxes = 0.06 x total
          System.out.printf("\n\t\t\t\t\t   TAX: $%.2f", store.salesTaxes); //display taxes now
          // %.2f, similar in C++, it will print in that location base on the given parameter value
          store.total += store.salesTaxes;// adding taxes to total
          
          if(store.plans == 0)
          {
        	  System.out.println("\nHere's your first payment for this month");
          }
          
          System.out.printf("\n\t\t\t\t     ****TOTAL: $%.2f\n",store.total); // tab is 8 spaces
          
          if (store.plans == 0) // show installment plan full price at the end
          {
        	  System.out.printf("\n\t  ****Installment plan in 1 year total: $%.2f\n",store.total*12); // adding taxes to total
          }
      }


      System.out.println("\n\nTHANK YOU FOR SHOPPING WITH WALKART, HAVE A NICE DAY!"); 
      // the end of the program and bids farewell to the users
    }
}

class itemList {
	// these are attributes
	public String id;
	public String itemName;
	public String itemPrice;
	public String details;
	public int ids = 1;
	public double price;
	
	// itemList class constructor
	itemList(String id, String itemName, String itemPrice, String details)
	{   // eliminates confusion between items
		// because these will be assign into an array that contains the item
		this.id = id;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.details = details;
	}
	
	//Overloading, but this takes in only double
	itemList(double price) 
	{
		this.price = price;
	}
	
	public void plusOneID() // fixes id
	{
		int temp; // temporary holds value
		temp = Integer.parseInt(id); // converts string to integer
		temp += 1;
		id = Integer.toString(temp); // converts integer to string
	}
	
	public void minusOneID() // fixes id
	{ // similar format as plusOnID but does subtraction
		int temp; 
		temp = Integer.parseInt(id);
		temp -= 1;
		id = Integer.toString(temp);
	}
	
	public void printList() // prints out the the list of items
    {						// this will base on what array/item
        // Product ID, Product Name, Product Price, Product Description
        System.out.printf("\n%-8s%-13s%16s\t\t%s", id, itemName, itemPrice, details);
        // remember... row is the first array and column is the second
        // printList() is finished here
    }
	
	public void printQuestionAmount() // method prints name of item base on the saved attribute of productDetails[i]
	{
		System.out.printf("\n` %s do you want? ", itemName);
		  // ask the user the amount of product that they want
	}
	
	public void printAmountAdded(int amount) // prints amount been added base on item
	{
		System.out.printf("\nAdded " + amount + " %s", itemName); 
	}
	
	public void printIncorrectAmount() // prints a statement that the user cannot go under 0 amount of items
	{
		System.out.printf("\n\nYou cannot have a negative amount of %s", itemName);
	}
	
	public void printItem(int count)
	{ // this method will print the item's descritption
		System.out.printf("\n%-8d%10s%18s\t\t%s", count, id, itemName, itemPrice);
	}
	
	public double getDiscount(int count)
    {
    	if (count >= 10) // if the said product has 10 or more
    	{ // apply discount 10% discount
    		price = (price * count) * 0.90; // take 10% out, we are left with 90% of the full price
    		System.out.printf("\nApplied 10%% discount\tTotal item price:\t$%.2f\n", price);
    	} // %% gives the output "%" itself
    	else
    	{
    		System.out.printf("\n\t\t\t      Total item price: $%.2f\n",price);
    	}
    	
    	// display below the item the number of items and total price of said item
    	// example: 5 Apples, Quantity: 5	Price: $4.95
    	return price; // return double value back
    }
}

