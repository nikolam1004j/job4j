package eagersingletons1;

public enum TrackerSingle {
    INSTANCE;

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.INSTANCE;
    }
}