package holdersingleton;

/**
 * Если у вас нет необходимости в ленивой загрузки используется шаблоны из первой группы. Например. инициализация кеша или базы данных.
 * Если в приложении есть затратные ресурсы нужно использовать шаблоны с ленивой загрузкой. Здесь можно использовать только один шаблон - это Holder.
 * Другие шаблоны будут отрицательно влиять на производительность системы.
 */
public class TrackerSingle {
    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        static {
            System.out.println("Holder static block");
        }
        private static final TrackerSingle INSTANCE = new TrackerSingle();
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}