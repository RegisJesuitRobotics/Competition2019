package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class RobotDrive {
	private PlaystationController _Controller;
	private WPI_TalonSRX UpperRight, UpperLeft, BackRight, BackLeft;

	public RobotDrive(PlaystationController controller) {
		_Controller = controller;
		UpperRight = new WPI_TalonSRX(0);
		UpperLeft = new WPI_TalonSRX(1);
		BackRight = new WPI_TalonSRX(2);
		BackLeft = new WPI_TalonSRX(3);

	}

	public void drive() {
		double RightTrigger = _Controller.RightTrigger();
		double LeftTrigger = _Controller.LeftTrigger();
		double LeftStick = _Controller.LeftStickXAxis();
		boolean isTriangle = _Controller.ButtonTriangle();

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

		UpperLeft.set(-LeftPower * Limiter);
		BackLeft.set(-LeftPower * Limiter);
		UpperRight.set(RightPower * Limiter);
		BackRight.set(RightPower * Limiter);
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

	public void hatchAlign() {

			







		/*
		 * // read values from proximity sensors: leftProximity and rightProximity //
		 * if(leftProximity < rightProximity) { while(leftProximity != rightProximity)
		 * // figure a way to be good enough and not perfect { spin left } }
		 * 
		 * else if(rightProximity < leftProximity) { while(leftProximity !=
		 * rightProximity) // figure a way to be good enough and not perfect { spin
		 * right } }
		 * 
		 * 
		 */
	}

	public void approachHatch() {
		/*
		
		*/
	}
}