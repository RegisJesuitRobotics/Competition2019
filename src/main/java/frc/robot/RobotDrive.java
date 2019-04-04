package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDrive {
	private PlaystationController _controller;
	private WPI_VictorSPX _frontRight, _frontLeft, _backRight, _backLeft;
	private WPI_TalonSRX _iWantToDie;
	public RobotDrive(PlaystationController controller) {
		_controller = controller;
		_frontRight = new WPI_VictorSPX(4);
		_frontLeft = new WPI_VictorSPX(2);
		_backRight = new WPI_VictorSPX(1);
		_backLeft = new WPI_VictorSPX(3);
		_iWantToDie = new WPI_TalonSRX(5);
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

		_frontLeft.set(LeftPower * Limiter);
		_backLeft.set(LeftPower * Limiter);
		_frontRight.set(-RightPower * Limiter);
		_backRight.set(-RightPower * Limiter);
	}
	
}
