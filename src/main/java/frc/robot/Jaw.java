package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.*;
//import edu.wpi.first.wpilibj.Relay;
//import edu.wpi.first.wpilibj.Relay.Value;
//import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
public class Jaw {
    //Relay B1_hatchPivotMotor, B2_ballPivotMotor, NR1_IntakeMotor;
    PlaystationController _playstation;
    WPI_TalonSRX B1_hatchPivotMotor, B2_ballPivotMotor, NR1_IntakeMotor, NR2_ballIntakeMotor;
    public Jaw(PlaystationController controller) {
        B1_hatchPivotMotor = new WPI_TalonSRX(5);
        B2_ballPivotMotor = new WPI_TalonSRX(6);
        NR1_IntakeMotor = new WPI_TalonSRX(7);
        NR2_ballIntakeMotor = new WPI_TalonSRX(8);
        _playstation = controller;
    }

    public void feedLowerJaw() {
        // && hatchLimitSwitch.get() == true
       if (_playstation.ButtonL1() == true) { 
           NR1_IntakeMotor.set(0.7);
            //System.out.println("On Feed Forward Lower Jaw");
} else if(_playstation.ButtonR1  () == true){
            NR1_IntakeMotor.set(-0.7);
            //System.out.println("On Feed Reverse Lower Jaw");
        }
        else {
            NR1_IntakeMotor.set(0);
            //System.out.println("Off Feed Lower Jaw");
        }

    }

    public void bottomJawPivot() {
        if (_playstation.ButtonCircle() == true) {
            B1_hatchPivotMotor.set(-0.5);
        } else if (_playstation.ButtonX()  == true) {
            B1_hatchPivotMotor.set(0.5);
        }
        else {
            B1_hatchPivotMotor.set(0);
        }

    }

    public void feedTopJaw() {
        if (_playstation.ButtonR1() == true) {
            NR1_IntakeMotor.set(-0.7);
        } else if(_playstation.ButtonL1() == true){
            NR1_IntakeMotor.set(0.7);
        }
        else {
            NR1_IntakeMotor.set(0);
        }

    }

    public void topJawPivot() {
        if (_playstation.ButtonTriangle()  == true) {
            B2_ballPivotMotor.set(-0.5);
            System.out.println("Top Jaw Up");
        } else if (_playstation.ButtonSquare() == true) {
            B2_ballPivotMotor.set(0.5);
             System.out.println("Top Jaw Down");
        }
        else {
            B2_ballPivotMotor.set(0);
        }

    }

}
