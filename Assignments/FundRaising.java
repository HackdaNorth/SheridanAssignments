/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundraising;
import javax.swing.*;
/**
 *
 * @author cgrigorsala0500
 */
public class FundRaising {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        double totalDon = 0;
        double donationAmount = 0;
        int select = 0;
        String dinput = "";
        int donation = 0;
        String inpop = "";
        int pop = 0;
        double fund[][] = new double[8][4];
        double total[] = new double[4];
        String amount[] = {"$0.25", "$0.50", "$1.00", "$2.00"};
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 4; col++) {
                fund[row][col] = 0;
            }
        }
        while (select != 9) {
            String school = JOptionPane.showInputDialog("Which school is doing a fundraising? \n 0: Catholic Central \n "
                    + "1: Holy Cross \n 2: John Paul II \n 3: Mother Teresa \n 4: Regina Mundi \n 5: St Joseph "
                    + "\n 6: St Mary \n 7: St. Thomas Aquinas \n 9: END PROGRAM");
            select = Integer.parseInt(school);
            if (select != 9) {
                dinput = JOptionPane.showInputDialog("How much did the school donate per students? \n 0 - 25 cents\n 1 - 50 cents"
                        + "\n 2 - 1 dollar\n 3 - 2 dollars");
                donation = Integer.parseInt(dinput);
                inpop = JOptionPane.showInputDialog("How many students attend this school?");
                pop = Integer.parseInt(inpop);
                if (donation == 0) {
                    donationAmount = 0.25;
                } else if (donation == 1) {
                    donationAmount = 0.5;
                } else if (donation == 2) {
                    donationAmount = 1;
                } else if (donation == 3) {
                    donationAmount = 2;
                }
                fund[select][donation] = pop * donationAmount;
            }
        }
        System.out.println(" CathCen, HolyC, JP II, MotherT, ReginaM, St.Joe, St.Mary, St.Thom TOTAL");
        for (int col = 0; col < 4; col++) {
            System.out.println();
            System.out.print(amount[col] + "   |");
            for (int row = 0; row < 8; row++) {
                System.out.print(fund[row][col] + "   |");
                total[col] += fund[row][col];
            }
            System.out.print(total[col]);
        }
        System.out.println("");
        for (int i = 0; i < 4; i++) {
            totalDon += total[i];
        }
        System.out.println("TOTAL DONATIONS = " + totalDon);

    }

}
