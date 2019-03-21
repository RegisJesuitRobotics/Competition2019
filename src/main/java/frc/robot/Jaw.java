package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.DigitalInput;

public class Jaw {
    DigitalInput hatchLimitSwitch;
    Relay B1_hatchPivotMotor, B2_ballPivotMotor, NR1_IntakeMotor;
    PlaystationController _playstation;

    public Jaw(PlaystationController controller) {
        hatchLimitSwitch = new DigitalInput(2);
        B1_hatchPivotMotor = new Relay(1);
        B2_ballPivotMotor = new Relay(3);
        NR1_IntakeMotor = new Relay(2);
       // NR2_ballIntakeMotor = new Relay(1);
        _playstation = controller;
    }

    public void feedLowerJaw() {
        // && hatchLimitSwitch.get() == true
        if (_playstation.ButtonL1() == true) {
            NR1_IntakeMotor.set(Value.kForward);
            //System.out.println("On Feed Forward Lower Jaw");
        } else if(_playstation.ButtonR1  () == true){
            NR1_IntakeMotor.set(Value.kReverse);
            //System.out.println("On Feed Reverse Lower Jaw");
        }
        else {
            NR1_IntakeMotor.set(Value.kOff);
            //System.out.println("Off Feed Lower Jaw");
        }

    }

    public void bottomJawPivot() {
        if (_playstation.ButtonCircle() == true) {
            B1_hatchPivotMotor.set(Value.kReverse);
        } else if (_playstation.ButtonX()  == true) {
            B1_hatchPivotMotor.set(Value.kForward);
        }
        else {
            B1_hatchPivotMotor.set(Value.kOff);
        }

    }

    public void feedTopJaw() {
        if (_playstation.ButtonR1() == true) {
            NR1_IntakeMotor.set(Value.kReverse);
        } else if(_playstation.ButtonL1() == true){
            NR1_IntakeMotor.set(Value.kForward);
        }
        else {
            NR1_IntakeMotor.set(Value.kOff);
        }

    }

    public void topJawPivot() {
        if (_playstation.ButtonTriangle()  == true) {
            B2_ballPivotMotor.set(Value.kReverse);
            System.out.println("Top Jaw Up");
        } else if (_playstation.ButtonSquare() == true) {
            B2_ballPivotMotor.set(Value.kForward);
             System.out.println("Top Jaw Down");
        }
        else {
            B2_ballPivotMotor.set(Value.kOff);
        }

    }

}
