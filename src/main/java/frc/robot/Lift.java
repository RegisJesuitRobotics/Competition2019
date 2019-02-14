package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.command.Command;;

public class Lift {
    CANSparkMax liftMotor;
    CANEncoder liftMotorEncoder;
    PlaystationController _playstation;
    boolean presetLiftIsRunning = false;
    double lastEncoderValue, currentEncoderValue, encoderDifference;
    double HighBall, MidBall, LowBall, HighHatch, MidHatch, LowHatch;

class CallLifter extends Command{
    double lifterHeight;
    public CallLifter(double lifterHeight) {
        this.lifterHeight = lifterHeight;
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void execute() {
        autoLift(lifterHeight);
    }

}

    public Lift(PlaystationController controller) {
        liftMotor = new CANSparkMax(13, MotorType.kBrushless);
        liftMotorEncoder = liftMotor.getEncoder();
        _playstation = controller;
        encoderDifference = 0;
        presetLiftIsRunning = false;
        lastEncoderValue = liftMotorEncoder.getPosition();

        LowBall = 50;
        MidBall = 87.64;
        HighBall = 120.244;
        LowHatch = 41.64;
        MidHatch = 79.76;
        HighHatch = 113;
        
        SmartDashboard.putData("Lifter Low Ball", new CallLifter(LowBall));
        SmartDashboard.putData("Lifter Mid Ball", new CallLifter(MidBall));
        SmartDashboard.putData("Lifter High Ball", new CallLifter(HighBall));
        SmartDashboard.putData("Lifter Low Hatch", new CallLifter(LowHatch));
        SmartDashboard.putData("Lifter Mid Hatch", new CallLifter(MidHatch));
        SmartDashboard.putData("Lifter High Hatch", new CallLifter(HighHatch));


    }

    public void autoLift(double height) {
        boolean LiftIsRunning = true;
         {
            
            while(LiftIsRunning == true && encoderDifference < height){
                currentEncoderValue = liftMotorEncoder.getPosition();
                encoderDifference = currentEncoderValue - lastEncoderValue;
                liftMotor.set(0.5);

                if(_playstation.ButtonRelasequare() == true){
                    LiftIsRunning = false;
                }

            }

            liftMotor.set(0);

        } 
        }

        public void test(){
            if (_playstation.ButtonRelaseircle() == true){
                LifttoLowBall();
                
        }
    }

        public void LifttoLowBall(){
            //SmartDashboard.putData("Lifter Low Ball", new LifttoLowBall(encoderDifference));
            if (encoderDifference < LowBall){
                autoLift(LowBall);
                

            }
        }

        public void LifttoMidBall(){
            if (encoderDifference < MidBall){
                autoLift(MidBall);


            }
        }
        public void LifttoHighBall(){
            if (encoderDifference < HighBall){
                autoLift(HighBall);


            }
        }
        public void LifttoLowHatch(){
            if (encoderDifference < LowHatch){
                autoLift(LowHatch);


            }
        }
        public void LifttoMidHatch(){
            if (encoderDifference < MidHatch){
                autoLift(MidHatch);


            }
        }
        public void LifttoHighHatch(){
            if (encoderDifference < HighHatch){
                autoLift(HighHatch);


            }
        }


    

    public void LiftHold() {
        
        if (_playstation.ButtonTriangle() == true) {
            liftMotor.set(0.5);
        } else if (_playstation.ButtonX() == true) {
            liftMotor.set(-0.25);
        } else if (presetLiftIsRunning == false) {
            liftMotor.set(0);
        }
    }

    public void compareEncoder(){
        currentEncoderValue = liftMotorEncoder.getPosition();
        encoderDifference = currentEncoderValue - lastEncoderValue;
    
        SmartDashboard.putNumber("Encoder Value Difference", encoderDifference);
        SmartDashboard.putNumber("Current Encoder Value", currentEncoderValue);



    }
}