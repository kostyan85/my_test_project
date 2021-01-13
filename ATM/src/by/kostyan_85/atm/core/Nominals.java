package by.kostyan_85.atm.core;

/**
 * Created by Zver on 09.03.2020.
 */
public enum Nominals {
    ONE_HUNDRED(100),
    FIFTY(50),
    TWENTY(20);

    private int value;

    public int getVal() {
        return value;
    }

    Nominals(int value) {
        this.value = value;

    }

    public static Nominals getMax() {
        return Nominals.ONE_HUNDRED;
    }


    public boolean hasLower() {
        return this != TWENTY;
    }

    public Nominals nextLower() {
        if (!hasLower()) {
            return null;
        }
        return Nominals.values()[ordinal() + 1];
    }


}

