package iopack;

import java.io.InputStream;
import java.util.Scanner;

public class ChekingByteArray {
    boolean isNumber(InputStream in) {
        boolean result = false;
        try (Scanner sc = new Scanner(in)) {
            if (sc.hasNextInt()) {
                result = (sc.nextInt() & 1) == 0;
            }
        }
        return result;
    }
}
