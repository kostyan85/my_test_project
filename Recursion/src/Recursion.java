import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zver on 16.12.2019.
 */
public class Recursion {
    public static void main(String[] args) {
        splitDigits(12);
        System.out.println("Массив mass = " + Arrays.toString(mass));
    }
    public static byte[] mass;
    public static List<Byte> arr = new ArrayList<Byte>();

    public static byte[] splitDigits(int number) {

        int x = 0;
        if (number != 0) {
            x = number / 10;
            int y = number % 10;
             splitDigits(x);
            arr.add((byte) y);

        }
        mass = new byte[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            mass[i] = arr.get(i);
        }
        return mass;
    }

    }


