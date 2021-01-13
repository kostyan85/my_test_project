package by.kostyan_85.atm.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zver on 22.03.2020.
 */
public class BanknoteBox {
    private Map<Nominals, Integer> banknotes;

    public BanknoteBox() {
        banknotes = new HashMap<>();
    }

    public void add(BanknoteStack stack) {
        for (BanknoteStack.NomCount nomCount : stack.toList()) {
            add(nomCount.getNom(), nomCount.getCount());
        }
    }

    private void add(Nominals nom, int count) {
        Integer current = banknotes.get(nom);
        banknotes.put(nom, current == null ? count : current + count);
    }

    public int get(Nominals nom) {
        Integer current = banknotes.get(nom);
        return current == null ? 0 : current;
    }
}
