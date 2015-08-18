package nka.com.motorsimulator;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Youri on 12.06.2015.
 */
public class GameLoop extends Thread{
    static final long FPS = 25;
    private GameView theView;
    private boolean isRunning = false;
    long startTime, sleepTime;

    public GameLoop(GameView theView) {
        this.theView = theView;
    }

    public void setRunning(boolean run) {
        isRunning = run;
    }

    @Override
    public void run() {
        long TPS = 1000 / FPS;
        while (isRunning) {
            Canvas theCanvas = null;
            startTime = System.currentTimeMillis();

            try {
                theCanvas = theView.getHolder().lockCanvas();
                Log.i("the Canvas", theCanvas.toString());
                synchronized (theView.getHolder()) {
                    theView.onMSDraw(theCanvas);
                }
            } finally {
                if (theCanvas != null) {
                    theView.getHolder().unlockCanvasAndPost(theCanvas);
                }
            }
            sleepTime = TPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(20); // 10
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("GL-PRINT- " + e.getMessage());
            }
        }
    }
}
