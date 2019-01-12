package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class SensorSuite {
    // Note: items plugged into DIO ports are usually AnalogInput type
    AnalogInput _ultraSonic;

    public SensorSuite() {
        _ultraSonic = new AnalogInput(2);
    }

    /**
     * UltraSonic Sensor begins to become unreliable around 13 inches away, Sensor
     * also only reads if its 2 inches away.
     * 
     * @return Distance to object in inches.
     */
    private double getUltraSonicInches() {
        double proximity = (_ultraSonic.getVoltage() + 0.33104) / 0.17741;

        return (double) Math.round(proximity * 1000) / 1000;
    }
}