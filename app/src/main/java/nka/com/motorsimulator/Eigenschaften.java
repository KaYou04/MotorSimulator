package nka.com.motorsimulator;

import android.util.Log;

/**
 * Created by Youri on 18.08.2015.
 */
public class Eigenschaften {
    private double endMotorBonus; //MotorLvl umgerechnet auf deinen Speed(das was drauf gerechnet wird)
    private int motorLvl;
    private int zusatzPower;
    private VmaxBerechnung vmaxBerechnung;
    private ClickEvent clE;
    private double clickFrequenzy;

    public Eigenschaften() {
        clE = new ClickEvent();
        vmaxBerechnung = new VmaxBerechnung();
        initializeEigenschaften();
    }

    private void initializeEigenschaften() {
        zusatzPower = 0;  //zusatzPower für evtl spätere Updates
        motorLvl = 0; //aktuelles MotorLvl
        motorBonusBerechnung();
    }

    private void motorBonusBerechnung() {
        endMotorBonus = 0; //Faktor mit dem die clickFrequency multipliziert wird um die Geschwindigkeit zu finden
        for (int i = 0; i <= motorLvl; i++) {
            double motorBonus = 3 * 10 / (10 + i);
            endMotorBonus += motorBonus;
        }
    }

    public double getEndMotorBonus() {
        return endMotorBonus;
    }

    public double berechnungVmax() {
        clE.berechnungClickFrequenzy();//aufruf zur berechnung der freq und get freq
        clickFrequenzy = clE.getClickFrequenzy();
        vmaxBerechnung.berechneV(endMotorBonus, zusatzPower, clickFrequenzy);
        return vmaxBerechnung.getV();
    }
}
