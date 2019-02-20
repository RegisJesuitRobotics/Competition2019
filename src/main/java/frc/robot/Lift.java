package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;

public class Lift {
    CANSparkMax liftMotor;
  //  CANEncoder liftMotorEncoder;
    PlaystationController _playstation;
    boolean presetLiftIsRunning = false;
    double lastEncoderValue, currentEncoderValue, encoderDifference;
    double HighBall, MidBall, LowBall, HighHatch, MidHatch, LowHatch;

    public Lift(PlaystationController controller) {
          liftMotor = new CANSparkMax(13, MotorType.kBrushed);
      //  liftMotorEncoder = liftMotor.getEncoder();
        _playstation = controller;
        encoderDifference = 0;
        presetLiftIsRunning = false;
      //  lastEncoderValue = liftMotorEncoder.getPosition();

        LowBall = 50;
        MidBall = 87.64;
        HighBall = 120.244;
        LowHatch = 41.64;
        MidHatch = 79.76;
        HighHatch = 113;

    }

    // public void getButtons() {

    //     if (SmartDashboard.getBoolean("LowBall", false) == true && presetLiftIsRunning == false) {
    //         autoLift(LowBall);
    //     } else if (SmartDashboard.getBoolean("Midball", false) == true && presetLiftIsRunning == false) {
    //         autoLift(MidBall);
    //     } else if (SmartDashboard.getBoolean("Highball", false) == true && presetLiftIsRunning == false) {
    //         autoLift(HighBall);
    //     } else if (SmartDashboard.getBoolean("LowHatch", false) == true && presetLiftIsRunning == false) {
    //         autoLift(LowHatch);
    //     } else if (SmartDashboard.getBoolean("MidHatch", false) == true && presetLiftIsRunning == false) {
    //         autoLift(MidHatch);
    //     } else if (SmartDashboard.getBoolean("HighHatch", false) == true && presetLiftIsRunning == false) {
    //         autoLift(HighHatch);
    //     }

    //     SmartDashboard.putBoolean("LowBall", false);
    //     SmartDashboard.putBoolean("MidBall", false);
    //     SmartDashboard.putBoolean("HighBall", false);
    //     SmartDashboard.putBoolean("LowHatch", false);
    //     SmartDashboard.putBoolean("MidHatch", false);
    //     SmartDashboard.putBoolean("HighHatch", false);
    // }

    // public void autoLift(double height) {

    //     // Thread t = new Thread(() -> {
    //     //     boolean flag = true;
    //     // while (flag == true) {
        
    //         presetLiftIsRunning = true;
    //         {
    //             while (presetLiftIsRunning == true && encoderDifference < height) {
    //                 currentEncoderValue = liftMotorEncoder.getPosition();
    //                 encoderDifference = currentEncoderValue - lastEncoderValue;
    //                 liftMotor.set(0.7);
    
    //                 if (_playstation.ButtonSquareRelease() == true) {
    //                     presetLiftIsRunning = false;
    //                 }
    
    //             }
    
    //             liftMotor.set(0);
    //             presetLiftIsRunning = false;
    //         }




    //     //     flag = false;
    //     // }
    //     // });
    //     //t.start();
    // }

    public void LiftHold() {

        if (_playstation.ButtonTriangle() == true) {
            liftMotor.set(-.8); 
        } else if (_playstation.ButtonX() == true) {
            liftMotor.set(0.5);
        } else if (presetLiftIsRunning == false) {
            liftMotor.set(0);
        }
    }

    // public void compareEncoder() {
    //     currentEncoderValue = liftMotorEncoder.getPosition();
    //     encoderDifference = currentEncoderValue - lastEncoderValue;

    //     SmartDashboard.putNumber("Encoder value Difference", encoderDifference);
    //     SmartDashboard.putNumber("Current Encoder Value", currentEncoderValue);

    // }
}
