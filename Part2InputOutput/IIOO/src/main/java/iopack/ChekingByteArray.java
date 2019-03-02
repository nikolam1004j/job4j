package iopack;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        byte[] lineSeparator = System.getProperty("line.separator").getBytes();
        try(Scanner sc = new Scanner(in)) {
            while (sc.hasNext()) {
                String curLine = sc.nextLine();
                for (String badWord : abuse) {
                    curLine = curLine.replace(badWord, "");
                }
                out.write(curLine.getBytes());
                out.write(lineSeparator);
            }
            out.flush();
        }
    }
}
