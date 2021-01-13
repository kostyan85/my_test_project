package by.kostyan_85.atm.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BanknoteStack {

    private static final String EMPTY = "EMPTY";
    private static final String MIDFIX = ": ";
    private static final String DELIM = ", ";

    private Map<Nominals, Integer> banknotes;

    public BanknoteStack() {
        banknotes = new HashMap<>();
    }

    public BanknoteStack add(Nominals nom, Integer count) {
        Integer current = banknotes.get(nom);
        banknotes.put(nom, current == null ? count : current + count);
        return this;
    }

    public List<NomCount> toList() {
        List<NomCount> result = new ArrayList<>();
        for (Nominals nom : Nominals.values()) {
            Integer count = banknotes.get(nom);
            if (count != null) {
                result.add(new NomCount(nom, count));
            }
        }
        return result;
    }

    public static class NomCount {
        private Nominals nom;
        private int count;

        public NomCount(Nominals nom, int count) {
            this.nom = nom;
            this.count = count;
        }

        public Nominals getNom() {
            return nom;
        }

        public int getCount() {
            return count;
        }
    }

    @Override
    public String toString() {
        if (banknotes.isEmpty()) {
            return EMPTY;
        }
        StringBuffer sb = new StringBuffer();
        for (NomCount nc : toList()) {
            sb.append(nc.getNom()).append(MIDFIX).append(nc.getCount()).append(DELIM);
        }
        return sb.substring(0,sb.length() - DELIM.length());
    }
}
