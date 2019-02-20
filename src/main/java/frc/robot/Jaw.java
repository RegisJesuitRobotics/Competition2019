package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalInput;

public class Jaw {
    DigitalInput hatchLimitSwitch;
    Relay B1_hatchPivotMotor, B2_ballPivotMotor, NR1_hatchIntakeMotor, NR2_ballIntakeMotor;
    PlaystationController _playstation;

    public Jaw(PlaystationController controller) {
        hatchLimitSwitch = new DigitalInput(2);
        B1_hatchPivotMotor = new Relay(3);
        B2_ballPivotMotor = new Relay(0);
        NR1_hatchIntakeMotor = new Relay(2);
        NR2_ballIntakeMotor = new Relay(1);
        _playstation = controller;
    }

    public void feedLowerJaw() {
        // && hatchLimitSwitch.get() == true
        if (_playstation.ButtonL1() == true) {
            NR1_hatchIntakeMotor.set(Value.kForward);
        } else if(_playstation.ButtonR1  () == true){
            NR1_hatchIntakeMotor.set(Value.kReverse);
        }
        else {
            NR1_hatchIntakeMotor.set(Value.kOff);
        }

    }

    public void bottomJawRotate() {
        if (_playstation.ButtonCircle() == true) {
            B1_hatchPivotMotor.set(Value.kReverse);
        } else if (_playstation.ButtonX()  == true) {
            B1_hatchPivotMotor.set(Value.kForward);
        }else{
            B1_hatchPivotMotor.set(Value.kOff);
        }

    }

    public void feedTopJaw() {
        if (_playstation.ButtonR1() == true) {
            NR2_ballIntakeMotor.set(Value.kReverse);
        } else if(_playstation.ButtonL1() == true){
            NR2_ballIntakeMotor.set(Value.kForward);
        }
        else if (_playstation.ButtonSquareRelease() == true) {
            NR2_ballIntakeMotor.set(Value.kOff);
        }

    }

    public void ballRotate() {
        if (_playstation.ButtonTriangle()  == true) {
            B2_ballPivotMotor.set(Value.kForward);

        } else if (_playstation.ButtonSquare() == true) {
            B2_ballPivotMotor.set(Value.kReverse);
        }else{
            B2_ballPivotMotor.set(Value.kOff);
        }

    }

}
