package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HatchSequences {
	WPI_TalonSRX RightMotor, LeftMotor;
	// Encoder LeftEncoder, RightEncoder;
	PlaystationController m_playStationController;
	// STUFF IN THE DIO IS ANALOG INPUT
	AnalogInput LeftUltraSonic, m_RetroReflectiveSensor, LineFollower;
    boolean HasBeenStopped;
    
    public HatchSequences(PlaystationController playStationController) {
		m_playStationController = playStationController;
		HasBeenStopped = false;
		LeftMotor = new WPI_TalonSRX(0);
		RightMotor = new WPI_TalonSRX(1);
		LeftUltraSonic = new AnalogInput(2);
		LineFollower = new AnalogInput(3);
		
		
	}
	
	public void fullSequence() {
		HasBeenStopped = false;
		if (m_playStationController.ButtonR1() == true) {
			FindWhiteLine();
			hatchAlign(Direction.Right, 0.4);
			approachHatch();
		}
	}

	public void AlignButton() {
		if (m_playStationController.ButtonCircle() == true) {
			hatchAlign(Direction.Right, .4);
		}

	}

	public void ApproachButton() {
		if (m_playStationController.ButtonTriangle() == true) {
			approachHatch();
		}

	}

	public void FindWhiteLineButton() {
		if (m_playStationController.ButtonX() == true) {
			FindWhiteLine();
		}
	}

	public void getSensorData() {
		SmartDashboard.putNumber("left voltage", LeftUltraSonic.getVoltage());
		SmartDashboard.putNumber("left get", LeftUltraSonic.getValue());
		SmartDashboard.putNumber("left average voltage", LeftUltraSonic.getAverageVoltage());
		SmartDashboard.putNumber("left average get", LeftUltraSonic.getAverageValue());
		SmartDashboard.putNumber("Math Distance", getUltraSonicInches());
		SmartDashboard.putNumber("Retro Bayyybeeeeee ", m_RetroReflectiveSensor.getVoltage());

	}
	private double getUltraSonicInches() {
		double proximity = (LeftUltraSonic.getVoltage() + 0.33104) / 0.17741;
		// SmartDashboard.putNumber("Status", proximity);
		return (double) Math.round(proximity * 1000) / 1000;

	}

	public void DriveForward(double speed) {
		LeftMotor.set(-speed);
		RightMotor.set(speed);
	}

	// THIS ONE IMPORTANT
	public void FindWhiteLine() {
		SmartDashboard.putNumber("Line Follower", LineFollower.getVoltage());
		while (true && HasBeenStopped == false) {
			SmartDashboard.putNumber("Line Follower", LineFollower.getVoltage());
			DriveForward(.15);
			if ((LineFollower.getVoltage() < 2.75)) {
				break;
			}
			if ((m_playStationController.ButtonSquare() == true)) {
				HasBeenStopped = true;
				break;
			}
		}
		moveAnAmount(.05, 1);
		stop();
	}

	public void hatchAlign(Direction direction, double speed) {

		if (HasBeenStopped == false) {

			SmartDashboard.putString("Status", "startedTurning");
			double leftProximity = getUltraSonicInches();
			SmartDashboard.putNumber("Status", leftProximity);

			while (leftProximity >= 30 || leftProximity <= 2) {
				spin(direction, speed);
				SmartDashboard.putNumber("Status", leftProximity);
				leftProximity = getUltraSonicInches();
				if (m_playStationController.ButtonSquare()) {
					HasBeenStopped = true;
					break;
				}
			}
			stop();

			SmartDashboard.putString("Status", "stoppedTurning");
			double oldLeftProximity = getUltraSonicInches();
			spin(direction, speed);
			while (oldLeftProximity != getUltraSonicInches() && HasBeenStopped == false) {
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
	}

	public void spin(Direction direction, double speed) {
		if (direction == Direction.Right) {
			LeftMotor.set(-speed);
			RightMotor.set(-speed);
		} else {
			LeftMotor.set(speed);
			RightMotor.set(speed);
		}
	}

	public void stop() {
		LeftMotor.set(0);
		RightMotor.set(0);
	}

	public void approachHatch() {
		if (HasBeenStopped == false) {
			SmartDashboard.putString("Status3", "stopped Second Loop");
			double proximityOutOfRange = 30.042;
			double leftProximity = getUltraSonicInches();
			while (leftProximity == proximityOutOfRange) {
				DriveForward(0.4);
				leftProximity = getUltraSonicInches();
				if (m_playStationController.ButtonSquare()) {
					HasBeenStopped = true;
					break;
				}
			}
			while (leftProximity > 4 && leftProximity != proximityOutOfRange) {
				SmartDashboard.putString("Status3", "inside Loop");
				DriveForward(0.2);
				leftProximity = getUltraSonicInches();
				SmartDashboard.putNumber("Left Proximity", leftProximity);
				if (m_playStationController.ButtonSquare()) {
					HasBeenStopped = true;
					break;
				}
			}
			SmartDashboard.putString("Status3", "stop");
			stop();
		}
	}
	public void moveAnAmount(double speed, double time) {
		LeftMotor.set(speed);
		RightMotor.set(speed);
		Timer.delay(time);
		stop();

	}
}