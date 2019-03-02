package iopack;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ChekingByteArrayTest {

    @Test
    public void testStream() throws IOException {
        ChekingByteArray chekingByteArray = new ChekingByteArray();
        try(InputStream inputStream = new FileInputStream("src\\main\\resources\\src.txt")) {
            Assert.assertTrue(chekingByteArray.isNumber(inputStream));
        }
    }

}