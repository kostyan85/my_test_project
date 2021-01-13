package by.kostyan_85.atm;

import by.kostyan_85.atm.core.ATM;
import by.kostyan_85.atm.core.ATMException;
import by.kostyan_85.atm.core.BanknoteStack;
import by.kostyan_85.atm.core.Nominals;

/**
 * симлятор работы банкомата
 * */
public class Launcher {

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.replenish(new BanknoteStack().add(Nominals.ONE_HUNDRED, 10).add(Nominals.FIFTY, 40).add(Nominals.TWENTY, 30050));
        int[] testAmounts = new int[] {10, 20, 30, 50, 80, 100, 110, 130, 170, 190};
        try {
            atm.withdraw(80);
            for (int amount : testAmounts) {
                try {
                    System.out.println("amount = " + amount + "; " + atm.withdraw(amount));
                } catch (ATMException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (ATMException e) {
            e.printStackTrace();
        }
    }

}
