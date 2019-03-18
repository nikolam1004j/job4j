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

    private final String LINE_SEPARATOR = System.getProperty("line.separator");
    private final String LOG_FILE = "log.txt";
    private final String ANSWERS_FILE =
            "Part2InputOutput\\IIOO\\src\\main\\resources\\answers.txt";
    private List<String> answers = new LinkedList<>();
    private SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss] ");
    private boolean pause;
    private boolean end;

    /**
     * Загружает готовые ответы из файла.
     *
     * @throws IOException
     */
    private void loadAnswers() throws IOException {
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
    private void writeLog(String message, boolean bot) {
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

    private boolean finish(String s) {
        writeLog(s, false);
        end = true;
        return pause = true;
    }

    private boolean stop(String s) {
        writeLog(s, false);
        return pause = true;
    }

    private boolean keepGoing(String s) {
        pause = false;
        doIt(s);
        return pause;
    }

    private boolean doIt(String s) {
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
        ConsoleChat chat = new ConsoleChat();
        chat.loadAnswers();
        Map<String, Function<String, Boolean>> map = new HashMap<>();
        map.put("закончить", chat::finish);
        map.put("стоп", chat::stop);
        map.put("продолжить", chat::keepGoing);

        Scanner sc = new Scanner(System.in);
        String curCmd;
        while (!chat.end) {
            curCmd = sc.nextLine();
            map.getOrDefault(curCmd, chat::doIt).apply(curCmd);
        }
        sc.close();
    }
}