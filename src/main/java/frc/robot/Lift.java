package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Lift extends Subsystem {
    PlaystationController _playstation;
    boolean presetLiftIsRunning = false;
    double lastEncoderValue, currentEncoderValue, encoderDifference;
    double HighBall, MidBall, LowBall, HighHatch, MidHatch, LowHatch, Grab, GrabberHigh;
    double LiftDeadzone;
    WPI_TalonSRX liftMotorTalon;

    public Lift(PlaystationController controller) {
        liftMotorTalon = new WPI_TalonSRX(5);
        liftMotorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        liftMotorTalon.setSelectedSensorPosition(0, 0, 0);
        _playstation = controller;
        encoderDifference = 0;

        presetLiftIsRunning = false;
        LiftDeadzone = 0.1;
     

        LowBall = 23000;
        MidBall = 55000;
        HighBall = 78000;
        MidHatch = 39000;
        HighHatch = 67000;
        Grab = 17000;
        GrabberHigh = 80000;

        SmartDashboard.putData("LowBall", new LiftButtons(LowBall, this));
        SmartDashboard.putData("MidBall", new LiftButtons(MidBall, this));
        SmartDashboard.putData("HighBall", new LiftButtons(HighBall, this));
        SmartDashboard.putData("MidHatch", new LiftButtons(MidHatch, this));
        SmartDashboard.putData("HighHatch", new LiftButtons(HighHatch, this));
        SmartDashboard.putData("Grab", new LiftButtons(Grab, this));
        SmartDashboard.putData("GrabberHigh", new LiftButtons(GrabberHigh, this));
        SmartDashboard.putData("ResetEncoder", new ResetEncoder(this));
    

    }
    
    public void setEncoderToZero(){
    liftMotorTalon.setSelectedSensorPosition(0, 0, 0);
    }

    public void autoLift(double height) {
        currentEncoderValue = liftMotorTalon.getSelectedSensorPosition(0);
        presetLiftIsRunning = true;

        while (presetLiftIsRunning == true && currentEncoderValue < height) {
            currentEncoderValue = liftMotorTalon.getSelectedSensorPosition(0);
            liftMotorTalon.set(-0.98);
           
            if (_playstation.ButtonTouchscreenReleased() == true) {
                presetLiftIsRunning = false;
            }
        }

        liftMotorTalon.set(0);
        presetLiftIsRunning = false;

    }

    public void LiftHold() {
        SmartDashboard.putNumber("Encoder", liftMotorTalon.getSelectedSensorPosition(0));
        if (_playstation.isDPadDown() == true) {
            liftMotorTalon.set(0.7);
        } else if (_playstation.isDPadUp() == true) {
            liftMotorTalon.set(-0.7);
        } else if (presetLiftIsRunning == false) {
            liftMotorTalon.set(0);
        }
    }

    @Override
    protected void initDefaultCommand() {

    }

    // public void compareEncoder() {
    // currentEncoderValue = liftMotorEncoder.getPosition();
    // encoderDifference = currentEncoderValue - lastEncoderValue;

    // SmartDashboard.putNumber("Encoder value Difference", encoderDifference);
    // SmartDashboard.putNumber("Current Encoder Value", currentEncoderValue);

    // }
}
