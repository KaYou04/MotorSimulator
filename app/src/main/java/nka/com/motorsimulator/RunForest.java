package nka.com.motorsimulator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Youri on 12.06.2015.
 */
public class RunForest extends Activity {
    private GameView theGameView;
    private boolean firstStart = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theGameView = new GameView(this);
        setContentView(theGameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        firstStart = false;
        theGameView.pauseThis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        theGameView.pauseThis();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!firstStart) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
