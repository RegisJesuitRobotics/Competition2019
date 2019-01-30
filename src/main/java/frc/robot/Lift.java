package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Controller;;

public class Lift {
    CANSparkMax liftMotor;
    CANEncoder liftMotorEncoder;
    PlaystationController _playstation;

    public Lift(PlaystationController controller) {

        liftMotor = new CANSparkMax(13, MotorType.kBrushless);
        liftMotorEncoder = liftMotor.getEncoder();
        _playstation = controller;
    }

    public void LiftTheLift() {
        SmartDashboard.putNumber("Lift Encoder", liftMotorEncoder.getPosition());
        SmartDashboard.putNumber("Lift Encoder 2", liftMotorEncoder.getPosition());
        if (_playstation.ButtonTriangle() == true) {
            //if(liftMotorEncoder.getPosition() < 72){
            liftMotor.set(0.1);
            SmartDashboard.putNumber("Lift Encoder", liftMotorEncoder.getPosition());
            SmartDashboard.putBoolean("Going Up", _playstation.ButtonTriangle());
            //}
        } else if (_playstation.ButtonX() == true) {
            //if(liftMotorEncoder.getPosition() > 0){
            liftMotor.set(-0.1);
            SmartDashboard.putNumber("Lift Encoder 2", liftMotorEncoder.getPosition());
            SmartDashboard.putBoolean("Going Down", _playstation.ButtonX());
            //}
        } else {
            liftMotor.set(0);
            SmartDashboard.putNumber("Lift Encoder 3", liftMotorEncoder.getPosition());
        }

    }
}