package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;

public class Lift extends Subsystem{
    CANSparkMax liftMotor;
    CANEncoder liftMotorEncoder;
    PlaystationController _playstation;
    boolean presetLiftIsRunning = false;
    double lastEncoderValue, currentEncoderValue, encoderDifference;
    double HighBall, MidBall, LowBall, HighHatch, MidHatch, LowHatch;
    double LiftDeadzone;

    public Lift(PlaystationController controller) {
          liftMotor = new CANSparkMax(13, MotorType.kBrushless);
      liftMotorEncoder = liftMotor.getEncoder();
        _playstation = controller;
        encoderDifference = 0;
        presetLiftIsRunning = false;
        LiftDeadzone = 0.1;
      //  lastEncoderValue = liftMotorEncoder.getPosition();

        LowBall = 50;
        MidBall = 87.64;
        HighBall = 120.244;
        LowHatch = 41.64;
        MidHatch = 79.76;
        HighHatch = 113;

        
        SmartDashboard.putData("LowBall", new LiftButtons(LowBall));
        SmartDashboard.putData("MidBall", new LiftButtons(MidBall));
        SmartDashboard.putData("HighBall", new LiftButtons(HighBall));
        SmartDashboard.putData("LowHatch", new LiftButtons(LowHatch));
        SmartDashboard.putData("MidHatch", new LiftButtons(MidHatch));
        SmartDashboard.putData("HighHatch", new LiftButtons(HighHatch));

    }

    public void getButtons() {

    }

    public void autoLift(double height) {
        
            presetLiftIsRunning = true;
            {
                while (presetLiftIsRunning == true && encoderDifference < height) {
                    currentEncoderValue = liftMotorEncoder.getPosition();
                    encoderDifference = currentEncoderValue - lastEncoderValue;
                    liftMotor.set(0.7);
    
                    if (_playstation.ButtonSquareRelease() == true) {
                        presetLiftIsRunning = false;
                    }
    
                }
    
                liftMotor.set(0);
                presetLiftIsRunning = false;
            }
    }

    public void LiftHold() {
        if (_playstation.RightStickYAxis() > LiftDeadzone) {
            liftMotor.set(-.8); 
        } else if (_playstation.RightStickYAxis() < -LiftDeadzone) {
            liftMotor.set(0.5);
        } else if (presetLiftIsRunning == false) {
            liftMotor.set(0);
        }
    }

    @Override
    protected void initDefaultCommand() {

    }

    // public void compareEncoder() {
    //     currentEncoderValue = liftMotorEncoder.getPosition();
    //     encoderDifference = currentEncoderValue - lastEncoderValue;

    //     SmartDashboard.putNumber("Encoder value Difference", encoderDifference);
    //     SmartDashboard.putNumber("Current Encoder Value", currentEncoderValue);

    // }
}
