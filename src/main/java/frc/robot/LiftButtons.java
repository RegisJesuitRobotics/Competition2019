package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftButtons extends Command{
double ballHeight;
boolean isFinished = false;



    public LiftButtons(double ballHeight){
        super(Robot._lift);
        this.ballHeight = ballHeight;

    }

    @Override
    protected void execute(){
        Robot._lift.autoLift(ballHeight);
        isFinished = true;
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }




}