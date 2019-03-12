package iopack;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

/**
 * Программа консольный чат.
 */
public class ConsoleChat {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String LOG_FILE = "log.txt";
    private static final String ANSWERS_FILE =
            "Part2InputOutput\\IIOO\\src\\main\\resources\\answers.txt";
    private static List<String> answers = new LinkedList<>();
    private static SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ");
    private static boolean pause;
    private static boolean end;

    /**
     * Загружает готовые ответы из файла.
     *
     * @throws IOException
     */
    private static void loadAnswers() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ANSWERS_FILE))) {
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
    private static void writeLog(String message, boolean bot) {
        if (!message.isEmpty()) {
            StringBuilder sb = new StringBuilder(simpleDateFormat.format(new Date()))
                    .append(bot ? "Бот: " : "Я: ")
                    .append(message).append(LINE_SEPARATOR);
            try (BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(LOG_FILE, true))) {
                stream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
                stream.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean finish(String s) {
        writeLog(s, false);
        end = true;
        return pause = true;
    }

    private static boolean stop(String s) {
        writeLog(s, false);
        return pause = true;
    }

    private static boolean keepGoing(String s) {
        pause = false;
        doIt(s);
        return pause;
    }

    private static boolean doIt(String s) {
        writeLog(s, false);
        if(!pause) {
            Random r = new Random();
            String answer = answers.get(r.nextInt(answers.size()));
            System.out.println(answer);
            writeLog(answer, true);
        }
        return pause;
    }

    public static void main(String[] args) throws IOException {
        loadAnswers();
        Map<String, Function<String, Boolean>> map = new HashMap<>();
        map.put("закончить", ConsoleChat::finish);
        map.put("стоп", ConsoleChat::stop);
        map.put("продолжить", ConsoleChat::keepGoing);

        Scanner sc = new Scanner(System.in);
        String curCmd;
        while (!end) {
            curCmd = sc.nextLine();
            map.getOrDefault(curCmd, ConsoleChat::doIt).apply(curCmd);
        }
        sc.close();
    }
}