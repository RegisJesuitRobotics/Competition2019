 package frc.robot;

 import edu.wpi.first.wpilibj.shuffleboard.*;
 import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.DigitalInput;

 public class Jaw {
    DigitalInput hatchLimitSwitch, jawRotationLimitSwitch, ballLimitSwitch;
    Relay jawLiftMotor, hatchFeedMotor, ballFeedMotor;
    PlaystationController _controller;

    public Jaw (PlaystationController controller){
        hatchLimitSwitch = new DigitalInput(1);
        jawRotationLimitSwitch = new DigitalInput(2);
        ballLimitSwitch = new DigitalInput(3);
        jawLiftMotor = new Relay(4);
        hatchFeedMotor = new Relay(5);
        ballFeedMotor = new Relay(6);
        _controller = controller;
    }

    public void feedHatch(){
        if(_controller.ButtonCircleRelease() == true){
            hatchFeedMotor.set(Value.kOn);
            if(hatchLimitSwitch.get() == true || _controller.ButtonSquareRelease() == true) {
                hatchFeedMotor.set(Value.kOff);
            }
        }
    }

    public void hatchRotate(){
        if(_controller.ButtonR1() == true){
            jawLiftMotor.set(Value.kOn);
            if(jawRotationLimitSwitch.get() == true || _controller.ButtonSquareRelease() == true){
                jawLiftMotor.set(Value.kOff);
            }
        }
    }

    public void feedBal(){
        if(_controller.ButtonL1() == true){
            ballFeedMotor.set(Value.kOn);
            if(ballLimitSwitch.get() == true || _controller.ButtonSquareRelease() == true){
                ballFeedMotor.set(Value.kOff);
            }
        }
    }
 }