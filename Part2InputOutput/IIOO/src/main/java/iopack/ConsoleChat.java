package iopack;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Программа консольный чат.
 */
public class ConsoleChat {

    private static List<String> answers = new LinkedList<>();
    private static final String answersFile = "Part2InputOutput\\IIOO\\src\\main\\resources\\answers.txt";
    private static final String logFile = "log.txt";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ");
    private static final String lineSeparator = System.getProperty("line.separator");

    /**
     * Загружает готовые ответы из файла.
     *
     * @throws IOException
     */
    private static void loadAnswers() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(answersFile))) {
            String answer;
            while ((answer = reader.readLine()) != null) {
                answers.add(answer);
            }
        }
    }

    /**
     * Записывает сообщения в лог.
     *
     * @param message Сообщение.
     * @param bot     true - если сообщение от бота, false - от пользователя.
     * @throws IOException
     */
    private static void writeLog(String message, boolean bot) throws IOException {
        if (!message.isEmpty()) {
            StringBuilder sb = new StringBuilder(simpleDateFormat.format(new Date()))
                    .append(bot ? "Бот: " : "Я: ")
                    .append(message).append(lineSeparator);
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(logFile, true))) {
                stream.write(sb.toString().getBytes("utf-8"));
            }
        }
    }

    /**
     * Точка входа в программу.
     *
     * @param args Аргументы.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        loadAnswers();
        Random r = new Random();
        boolean pause = false;

        Scanner sc = new Scanner(System.in);
        String curCmd, answer = null;
        while (!(curCmd = sc.nextLine()).equalsIgnoreCase("закончить")) {
            writeLog(curCmd, false);
            if (pause) {
                if (curCmd.equalsIgnoreCase("продолжить")) {
                    pause = false;
                }
            }
            if (!pause) {
                if (curCmd.equalsIgnoreCase("стоп")) {
                    pause = true;
                } else {
                    answer = answers.get(r.nextInt(answers.size()));
                    System.out.println(answer);
                    writeLog(answer, true);
                }
            }

        }
        sc.close();
    }
}