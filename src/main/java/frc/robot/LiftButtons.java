package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftButtons extends Command{
double ballHeight;
boolean isFinished = false;
Lift _lift;


    public LiftButtons(double ballHeight, Lift lift){
        _lift = lift;
        this.ballHeight = ballHeight;

    }

    @Override
    protected void execute(){
        _lift.autoLift(ballHeight);
        isFinished = true;
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }




}