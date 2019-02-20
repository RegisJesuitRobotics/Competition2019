  package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Elevator {
    
    Relay elevatorLiftMotor;
    PlaystationController _controller;
    WPI_TalonSRX elevatorMotorLeft, elevatorMotorRight;


    public Elevator(PlaystationController controller){
        _controller = controller;
        elevatorLiftMotor = new Relay (6);
    }

    // public void elevatorUp(){
    //     if (_controller.isDPadUp() == true){
    //         elevatorLiftMotor.set(Value.kOn);
    //     }
    //     if(_controller.isDPadDown() == true){
    //         elevatorLiftMotor.set(Value.kReverse);
    //     }
    //     else{
    //         elevatorLiftMotor.set(Value.kOff);
    //     }
    // }

    // public void elevatorMove(){
    //     if(_controller.isDPadRight() == true){
    //         elevatorMotorLeft.set(-.5);
    //         elevatorMotorRight.set(.5);
    //     }
    //     if(_controller.isDPadLeft() == true){
    //         elevatorMotorLeft.set(.5);
    //         elevatorMotorRight.set(-.5);
    //     }
    //     else{
    //         elevatorMotorLeft.set(0);
    //         elevatorMotorRight.set(0);
    //     }
    // }
}