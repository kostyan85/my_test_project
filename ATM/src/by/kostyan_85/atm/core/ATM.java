package by.kostyan_85.atm.core;

public class ATM {

    private BanknoteBox box;

    public ATM() {
        box = new BanknoteBox();
    }

    public void replenish(BanknoteStack stack) {
        box.add(stack);
    }

    public BanknoteStack withdraw(int amount) throws ATMException {
        BanknoteStack valid = AmountValidator.validate(amount);
        // TODO check againt ATM load (BanknoteBox)
        return AmountValidator.validate(amount);
    }
}
