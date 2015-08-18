package nka.com.motorsimulator;

import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Youri on 12.06.2015.
 */
public class VmaxBerechnung {
    private double V; //Gechwindigkeit deines Autos

    public void berechneV(double endMotor, int zusatzPower, double clickFrequenzy) {
        Log.i("endMotorLvl ", String.valueOf(endMotor));
        //endMotor = Stärke des Motors
        //zusatzPower kommt noch
        //berechnete ClickFrequenzy
        V = endMotor * clickFrequenzy;
        Log.i("berchnete V ", String.valueOf(V));
    }

    public double getV() {
        return V;
    }
}
