 package frc.robot;

 import edu.wpi.first.wpilibj.shuffleboard.*;
 import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.DigitalInput;

 public class Jaw {
    DigitalInput hatchLimitSwitch;
    Relay jawLiftMotor, hatchFeedMotor;
    PlaystationController _controller;

    public Jaw (PlaystationController controller){
        hatchLimitSwitch = new DigitalInput(1);
        jawLiftMotor = new Relay(3);
        hatchFeedMotor = new Relay(4);
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
            
        }
    }
 }