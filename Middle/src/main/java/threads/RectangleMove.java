package threads;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private final int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public void run() {
        int koefX = 1;
        int koefY = 1;
        while (!Thread.currentThread().isInterrupted()) {
            if ((rect.getX() == limitX - rect.getWidth() && koefX > 0) ||
                    (rect.getX() == 0 && koefX < 0)) {
                koefX = -koefX;
            }
            if ((rect.getY() == limitY - rect.getHeight() && koefY > 0) ||
                    (rect.getY() == 0 && koefY < 0)) {
                koefY = -koefY;
            }
            this.rect.setX(this.rect.getX() + koefX);
            this.rect.setY(this.rect.getY() + koefY);
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}