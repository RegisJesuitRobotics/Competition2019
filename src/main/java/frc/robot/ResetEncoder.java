package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncoder extends Command{
boolean isFinished = false;
Lift _lift;


    public ResetEncoder( Lift lift){
        _lift = lift;

    }

    @Override
    protected void execute(){
        _lift.setEncoderToZero();
        isFinished = true;
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }




}