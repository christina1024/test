import java.util.Random;
import java.util.Scanner;
public class Check{
    public static void main (String[]args){
	Scanner keyboard=new Scanner(System.in);
	Random rand=new Random();
	System.out.println("I'm thinking a number between 0 and 9.");
	System.out.print("What is your guess: ");
	
	//Prompt user for a number between 0 and 9.
	String  num1=keyboard.nextLine();
	//Convert string into a character.
	char num3=num1.charAt(0);
	
	//Check the length of the user's input. And if the length is less or greater than 1, an invalid guess statement will be printed.	
	if (num1.length()!= 1){
	    System.out.println("invalid guess");}
	//Check if the user's input is a digit or not. And if the input is not a digit 1, an invalid guess statement will be printed.
	
	else if (Character.isDigit(num3)!=true){
	    System.out.println("invalid guess");}
	    
	else{	
	    int num2=rand.nextInt(10);
	    int num=Integer.parseInt(num1);
	    //compare the user's input and the random number imported by the computer.
	    if(num==num2){
		System.out.println("yout guess is correct.");}
	    else if(num>num2){
		System.out.println("your guess is too high.");}
	    else if(num2>num){
		System.out.println("your guess is too low.");}
	    System.out.println("my guess is "+num2);
	}
    }
}
