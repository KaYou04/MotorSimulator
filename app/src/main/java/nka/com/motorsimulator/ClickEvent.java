package nka.com.motorsimulator;

import android.util.Log;

/**
 * Created by Youri on 12.06.2015.
 */
public class ClickEvent {
    private double ClickFrequenzy;//clicks relativ zur zeit
    private long lastClick;//letzter click
    private long currClick;//aktueller click

    public double getClickFrequenzy() {
        return ClickFrequenzy;
    }

    public void berechnungClickFrequenzy() {
        currClick = System.currentTimeMillis();
        double diff = currClick - lastClick;//differenz der clicks
        ClickFrequenzy = 1000.0 / diff;//normalisierung auf sek
        Log.i("ClickFrequenzy", String.valueOf(ClickFrequenzy));
        Log.i("diff", String.valueOf(diff));
        lastClick = currClick;
    }
}
