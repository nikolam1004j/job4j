package eagersingletons2;

public class TrackerSingle {
    private static final TrackerSingle INSTANCE = new TrackerSingle();

    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        TrackerSingle instance = TrackerSingle.getInstance();
    }
}
