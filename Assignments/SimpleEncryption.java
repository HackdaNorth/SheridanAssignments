/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.encryption;

import javax.swing.*;

/**
 *
 * @author cgrigorsala0500
 */
public class SimpleEncryption {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String input;
        String number;
        String number1or2;

        input = JOptionPane.showInputDialog("Type in a sentence");
        number = JOptionPane.showInputDialog("Please pick a number between 1 - 25 to rotate");
        number1or2 = JOptionPane.showInputDialog("1: Encryption \n2: Decryption");

        int encrypt = 0;
        int z;
        int rotationAmount;
        String eMessage = "";
        encrypt = Integer.parseInt(number1or2);
        if (encrypt == 1) {
            while (isNumeric(number) == false) {
                number = JOptionPane.showInputDialog("Please type in a number between 1 and 25");
            }
            rotationAmount = Integer.parseInt(number);
            while (rotationAmount > 25) {
                rotationAmount -= 25;
            }
            int length = input.length();
            String input2 = input.toLowerCase();
            for (int i = 0; i < length; i++) {
                if (input2.charAt(i) != 32) {
                    z = (int) input2.charAt(i) + rotationAmount;
                    if (z > 122) {
                        z -= 26;
                    }
                    eMessage += (char) z;
                } else {
                    z = (int) input2.charAt(i);
                    eMessage += (char) z;
                }
            }
            System.out.println(input2);
            System.out.println(eMessage);
        } else if (encrypt == 2) {

            while (isNumeric(number) == false) {
                number = JOptionPane.showInputDialog("Please type in a number between 1 and 25");
            }
            rotationAmount = Integer.parseInt(number);
            while (rotationAmount > 25) {
                rotationAmount -= 25;
            }
            int length = input.length();
            String input2 = input.toLowerCase();
            for (int i = 0; i < length; i++) {
                if (input2.charAt(i) != 32) {
                    z = (int) input2.charAt(i) - rotationAmount;
                    if (z < 97) {
                        z += 26;
                    }
                    eMessage += (char) z;
                } else {
                    z = (int) input2.charAt(i);
                    eMessage += (char) z;
                }
            }
            System.out.println(input2);
            System.out.println(eMessage);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }

        //String str = "ICS 4U";
    //str.length();
    //System.out.println(str.length());
    //String str = "Sir John A. Macdonald";
    //str.length();
    //System.out.println(str.length() - 1);
    //String str1 = "This is a substring";
    //String str2 = str1.substring(10,13);
    //System.out.println(str2);
    //String trt = "Row";
    //trt += "The Boat";
    //System.out.println(trt);
    //String str1 = "Java is open-sourced";
    //String str2 = str1.substring(13);
    //System.out.println(str2);
}

}

    private static boolean isNumeric(String number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
