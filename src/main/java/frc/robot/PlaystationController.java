package frc.robot;
import edu.wpi.first.wpilibj.Joystick;

public class PlaystationController extends Joystick {
	public PlaystationController(int port) {
		super(port);
	}

	public boolean ButtonSquare() {
		return super.getRawButton(1);
	}
	public boolean ButtonSquareRelease(){
		return super.getRawButtonReleased(1);
	}
	public boolean ButtonX() {
		return super.getRawButton(2);
	}
	public boolean ButtonXRelease(){
		return super.getRawButtonReleased(2);
	}
	public boolean ButtonCircle() {
		return super.getRawButton(3);
	}
	
	public boolean ButtonCircleRelease(){
		return super.getRawButtonReleased(3);
	}

	public boolean ButtonTriangle() {
		return super.getRawButton(4);
	}
	public boolean ButtonTriangleRelease(){
		return super.getRawButtonReleased(4);
	}
	public boolean ButtonL1() {
		return super.getRawButton(5);
	}

	public boolean ButtonR1() {
		return super.getRawButton(6);
	}

	public boolean ButtonL2() {
		return super.getRawButton(7);
	}

	public boolean ButtonR2() {
		return super.getRawButton(8);
	}

	public boolean ButtonShare() {
		return super.getRawButton(9);
	}

	public boolean ButtonOptions() {
		return super.getRawButton(10);
	}

	public boolean ButtonL3() {
		return super.getRawButton(11);
	}

	public boolean ButtonR3() {
		return super.getRawButton(12);
	}

	public boolean ButtonPS() {
		return super.getRawButton(13);
	}

	public boolean ButtonTouchscreen() {
		return super.getRawButton(14);
	}
	public boolean ButtonTouchscreenReleased() {
		return super.getRawButtonReleased(14);
	}
	public double LeftStickXAxis() {
		return super.getRawAxis(0);
	}

	public double LeftStickYAxis() {
		return super.getRawAxis(1);
	}

	public double RightStickXAxis() {
		return super.getRawAxis(2);
	}

	public double RightStickYAxis() {
		return super.getRawAxis(5);
	}

	public double LeftTrigger() {
		if (super.getRawAxis(3) < 0) {
			return 0;
		} else {
			return super.getRawAxis(3);
		}

	}

	public double RightTrigger() {
		if (super.getRawAxis(4) < 0) {
			return 0;
		} else {
			return super.getRawAxis(4);
		}
	}

	public boolean isDPadUp() {
		return super.getPOV() == 0;
	}
	
	public boolean isDPadRight() {
		return super.getPOV() == 90;
	}

	public boolean isDPadDown() {
		return super.getPOV() == 180;
	}

	public boolean isDPadLeft() {
		return super.getPOV() == 270;
	}

	public boolean isDPadUpRight() {
		return super.getPOV() == 45;
	}

	public boolean isDPadDownRight() {
		return super.getPOV() == 135;
	}

	public boolean isDPadDownLeft() {
		return super.getPOV() == 225;
	}

	public boolean isDPadUpLeft() {
		return super.getPOV() == 315;
	}
}