package lazysingletons1;

/**
 * Инициализация и проверка экземпляра происходит внутри критической секции. Этот шаблон деградирует производительность.
 * Использовать этот шаблон не рекомендуется.
 */
public class TrackerSingle {
    private static TrackerSingle INSTANCE;

    private TrackerSingle() {
    }

    public static synchronized TrackerSingle getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrackerSingle();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
