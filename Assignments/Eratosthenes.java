/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eratosthenes;

/**
 *
 * @author cgrigorsala0500
 */
public class Eratosthenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       //my codes
       int i =0;
       int number =0;
       //Empty String
       String  primeNumbers = "";

       for (i = 1; i <= 1000; i++)         
       { 		  	  
          int counter=0; 	  
          for(number =i; number>=1; number--)
	  { if(i%number == 0)
	     {
 		counter = counter + 1;
	     }
	  }
	  if (counter == 2)
	  {
	     //Prime number to the String
	     primeNumbers = primeNumbers + i + " ";
	  }	
       }	
       System.out.println("Prime numbers from 1 to 1000 are :");
       System.out.println(primeNumbers);

       //Teacher's codes
        boolean numbers[] = new boolean[1000];
        for (int x = 0; x <= 999; x++) {
            numbers[x] = true;
        }
        for (int j = 2; j < 500; j++) {
            for (int a = 2; (a * j) < 1000; a++) {
                numbers[a * j] = false;
            }
        }
        System.out.println("Answer Code: ");
        for (int q = 2; q < 1000; q++) {
            if (numbers[q] == true) {
                System.out.print(q+" ");
            }
        }
        System.out.println("");
    }

}
