package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDrive {
	private PlaystationController _controller;
	private WPI_TalonSRX _frontRight, _frontLeft, _backRight, _backLeft;

	// Note: items plugged into DIO ports are usually AnalogInput type
	AnalogInput _ultraSonic;

	public RobotDrive(PlaystationController controller) {
		_controller = controller;
		_frontRight = new WPI_TalonSRX(0);
		_frontLeft = new WPI_TalonSRX(1);
		_backRight = new WPI_TalonSRX(2);
		_backLeft = new WPI_TalonSRX(3);
		_ultraSonic = new AnalogInput(2);
	}

	public void drive() {
		double RightTrigger = _controller.RightTrigger();
		double LeftTrigger = _controller.LeftTrigger();
		double LeftStick = _controller.LeftStickXAxis();
		boolean isTriangle = _controller.ButtonTriangle();

		double Deadzone = 0.1;
		double RightPower = 1;
		double LeftPower = 1;
		double Power;
		double Limiter = 0.83245;
		double turn = 2 * LeftStick;
		Power = RightTrigger - LeftTrigger;

		String move = "";
		if (LeftStick > Deadzone) {

			LeftPower = Power;
			RightPower = Power - (turn * Power);
			move = "Left Turn ";
		} else if (LeftStick < -Deadzone) {

			LeftPower = Power + (turn * Power);
			RightPower = Power;
			move = "Right Turn ";
		} else {
			LeftPower = Power;
			RightPower = Power;
			move = "Straight ";
		}

		_frontLeft.set(-LeftPower * Limiter);
		_backLeft.set(-LeftPower * Limiter);
		_frontRight.set(RightPower * Limiter);
		_backRight.set(RightPower * Limiter);
	}

	// UltraSonic Sensor begins to become unreliable around 13 inches away, Sensor
	// also only reads if its 2 inches away
	// This returns distances with 2 decimal points of accuracy
	private double getUltraSonicInches() {
		double proximity = (_ultraSonic.getVoltage() + 0.33104) / 0.17741;
		// SmartDashboard.putNumber("Status", proximity);
		return (double) Math.round(proximity * 1000) / 1000;

	}

	public void DriveForward(double speed) {
		// TODO: implement for new motors!
		// LeftMotor.set(-speed);
		// RightMotor.set(speed);
	}

	public void FindWhiteLine() {
		/*
		
		*/

		boolean isWhiteLineFound = false;

		while (!isWhiteLineFound) {
			// Drive forward
			// Read from sensor
			// if sensor finds white tape
			// isWhiteLineFound = true;
			// stop moving forward
		}
	}

	public void hatchAlign(Direction direction, double speed) {
		SmartDashboard.putString("Status", "startedTurning");
		double leftProximity = getUltraSonicInches();
		SmartDashboard.putNumber("Status", leftProximity);
		while (leftProximity >= 30 || leftProximity <= 2) {
			spin(direction, speed);
			SmartDashboard.putNumber("Status", leftProximity);
			leftProximity = getUltraSonicInches();
		}

		stop();
		SmartDashboard.putString("Status", "stoppedTurning");

		double oldLeftProximity = getUltraSonicInches();
		spin(direction, speed);

		while (oldLeftProximity != getUltraSonicInches()) {
			SmartDashboard.putNumber("Left Proximity", getUltraSonicInches());
			SmartDashboard.putString("Status2", "secondLoop");

			spin(direction, speed);
			oldLeftProximity = leftProximity;
			leftProximity = getUltraSonicInches();
			SmartDashboard.putNumber("Old Left Proximity", oldLeftProximity);
			SmartDashboard.putNumber("Left Proximity", leftProximity);
		}

		stop();
		SmartDashboard.putNumber("Old Left Proximity", oldLeftProximity);
		SmartDashboard.putString("Status2", "stopped Second Loop");
	}

	public void spin(Direction direction, double speed) {
		// TODO: update for new motors!
		// if (direction == Direction.Right) {
		// LeftMotor.set(-speed);
		// RightMotor.set(-speed);
		// } else {
		// LeftMotor.set(speed);
		// RightMotor.set(speed);
		// }
	}

	public void stop() {
		// TODO: update for new motors!
		// LeftMotor.set(0);
		// RightMotor.set(0);
	}

	public void approachHatch() {
		SmartDashboard.putString("Status3", "stopped Second Loop");
		double proximityOutOfRange = 30.042;
		double leftProximity = getUltraSonicInches();

		while (leftProximity == proximityOutOfRange) {
			DriveForward(0.4);
			leftProximity = getUltraSonicInches();
		}

		while (leftProximity > 4 && leftProximity != proximityOutOfRange) {
			SmartDashboard.putString("Status3", "inside Loop");
			DriveForward(0.2);
			leftProximity = getUltraSonicInches();
			SmartDashboard.putNumber("Left Proximity", leftProximity);
		}

		SmartDashboard.putString("Status3", "stop");
		stop();
	}
}