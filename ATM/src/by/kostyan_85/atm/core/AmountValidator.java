package by.kostyan_85.atm.core;

public class AmountValidator {

    private AmountValidator() {
    }

    public static BanknoteStack validate(int amount) throws ATMAmountException {
        BanknoteStack stack = new BanknoteStack();
        int cutOff = amount;
        for (Nominals nom = Nominals.getMax(); nom != null; nom = nom.nextLower()) {
            try {
                cutOff = cutOff(stack, cutOff, nom);
            } catch (ATMAmountException e) {
                throw new ATMAmountException("Cannot withdraw amount. Amount = " + amount + "\n" + e.getMessage());
            }
        }
        if (cutOff == 0) {
            return stack;
        } else {
            throw new ATMAmountException("Cannot withdraw amount. Amount = " + amount);
        }
    }

    private static int cutOff(BanknoteStack stack, int amount, Nominals nom) throws ATMAmountException {
        if (amount < nom.getVal()) {
            return amount;
        }
        int rest = amount % nom.getVal();
        int count = amount / nom.getVal();
        if (saveBanknote(rest, nom)) {
            count--;
            rest += nom.getVal();
        }
        if (count > 0) {
            stack.add(nom, count);
        }
        return rest;
    }

    private static boolean saveBanknote(int rest, Nominals nom) throws ATMAmountException {
        if (rest == 0) {
            return false;
        }
        if (rest > 0 && !nom.hasLower()) {
            throw new ATMAmountException("Cannot withdraw amount. Rest = " + rest);
        }
        Nominals next = nom.nextLower();

        return next.hasLower() ? rest < next.getVal() && !isMult(rest, next.nextLower()) : !isMult(rest, next);
    }

    private static boolean isMult(int rest, Nominals nom) {
        return rest % nom.getVal() == 0;
    }

}
