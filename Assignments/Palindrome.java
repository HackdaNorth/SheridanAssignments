/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindrome;
import javax.swing.*;
/**
 *
 * @author cgrigorsala0500
 */
public class Palindrome {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String input;
        input = JOptionPane.showInputDialog("Type a word");
        String output = new StringBuilder(input).reverse().toString();
        if (input.equals(new StringBuilder(input).reverse().toString() )) {
            System.out.println(input + " is a Palindrome");
        }
        else if (input != new StringBuilder(input).reverse().toString()) {
            System.out.println(input + " is not a Palindrome");
        }
        else {
            System.out.println("Error " + input +" is contains illegal characters");
        }

    }
    
}
