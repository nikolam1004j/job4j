package iopack;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class ChekingByteArrayTest {

    @Test
    public void testStream() throws IOException {
        ChekingByteArray chekingByteArray = new ChekingByteArray();
        try(InputStream inputStream = new FileInputStream("src\\main\\resources\\src.txt")) {
            Assert.assertTrue(chekingByteArray.isNumber(inputStream));
        }
    }

    @Test
    public void testBadWords() throws IOException {
        ChekingByteArray chekingByteArray = new ChekingByteArray();
        String input = "src\\main\\resources\\input.txt";
        String output = "src\\main\\resources\\output.txt";
        String[] badWords = {"Lorem", "Ipsum", "with", "Vestibulum"};
        try(InputStream inputStream = new FileInputStream(input)) {
            try(OutputStream outputStream = new FileOutputStream(output)) {
                chekingByteArray.dropAbuses(inputStream,
                        outputStream,
                        badWords);
            }
        }

        //После выполнения процедуры dropAbuses заново загружаем файлы
        String sep = System.getProperty("line.separator");
        StringBuilder sbFileInput = new StringBuilder();
        try(FileReader fr = new FileReader(input)) {
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()) {
                sbFileInput.append(sc.nextLine()).append(sep);
            }
        }

        StringBuilder sbFileOutput = new StringBuilder();
        try(FileReader fr = new FileReader(output)) {
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()) {
                sbFileOutput.append(sc.nextLine()).append(sep);
            }
        }

        //Циклом заменяем все плохие слова и сравниваем с выходным файлом,
        //который записала процедура dropAbuses.
        String afterReplaceString = sbFileInput.toString();
        for (String badWord : badWords) {
            afterReplaceString = afterReplaceString.replace(badWord, "");
        }

        Assert.assertEquals(afterReplaceString, sbFileOutput.toString());
    }
}