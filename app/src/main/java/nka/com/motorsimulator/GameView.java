package nka.com.motorsimulator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Youri on 12.06.2015.
 */
public class GameView extends SurfaceView {
    private SurfaceHolder surfaceHolder;
    private int Clicks;
    private String ClickString;
    private float density;
    private GameLoop theGameLoopThread;
    private double geschwindigkeit = 0.0;
    private Eigenschaften eigenschaften;

    public GameView(Context context) {
        super(context);
        getDensity();
        theGameLoopThread = new GameLoop(this);
        eigenschaften = new Eigenschaften();
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                theGameLoopThread.setRunning(true);
                if (theGameLoopThread.getState() == theGameLoopThread
                        .getState().NEW) {
                    theGameLoopThread.start();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {//testweise Pause-Bug
                theGameLoopThread.setRunning(false);
                boolean retry = true;
                while (retry) {
                    try {
                        theGameLoopThread.interrupt();
                        theGameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        System.out.println("GV-PRINT- " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onMSDraw(Canvas canvas) { //malen nach zahlen
        canvas.drawColor(Color.BLUE);
        final int fontSize = (int) (25 * density);
        int yTextPos = (int) (30 * density);
        Typeface font = Typeface.create("Arial", Typeface.NORMAL);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTypeface(font);
        paint.setTextSize(fontSize);
        paint.setAntiAlias(true);
        ClickString = String.valueOf(Clicks);
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_UP);
        int x = 0;
        final String text = "Clicks: " + ClickString;
        canvas.drawText(text, x, yTextPos, paint);
        canvas.drawText("V: " + df.format(geschwindigkeit), x, (int) 110 * density, paint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                geschwindigkeit = eigenschaften.berechnungVmax();//Aufruf zur Berechnung der Geschwindigkeit
                Clicks++;
                return true;
            case MotionEvent.ACTION_UP:
                return false;
            default:
                return false;
        }
    }

    public float getDensity() {
        density = getResources().getDisplayMetrics().density;
        return density;
    }

    public void pauseThis() {
        //theGameLoopThread.setRunning(false);
    }

    public void resumeThisShit() {
        theGameLoopThread = new GameLoop(this);
        theGameLoopThread.setRunning(true);
        theGameLoopThread.start();
    }
}
