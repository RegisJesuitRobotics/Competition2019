package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDrive {
	WPI_VictorSPX _frontRight, _frontLeft, _backRight, _backLeft;
	WPI_TalonSRX hatchMotor;
	Encoder frontEncoderRight, frontEncoderLeft;
	PlaystationController m_playStationController;
	// STUFF IN THE DIO IS ANALOG INPUT
    AnalogInput LeftUltraSonic, Laser, m_RetroReflectiveSensor;
    Ultrasonic proximity;
	boolean HasBeenStopped;
	// Limit Switch for the hatch
    DigitalInput limitSwitch;
    
    public void AutoDrive(){  
		_frontRight = new WPI_VictorSPX(2);
		_frontLeft = new WPI_VictorSPX(0);
		_backRight = new WPI_VictorSPX(3);
		_backLeft = new WPI_VictorSPX(1);
        frontEncoderLeft = new Encoder(0, 1, true,Encoder.EncodingType.k2X);
        frontEncoderRight = new Encoder(2, 3, false,Encoder.EncodingType.k2X);
        LeftUltraSonic = new AnalogInput(2);
    }

    public void DriveForward(double speed) {
		_frontLeft.set(-speed);
		_frontLeft.set(speed);
		_backLeft.set(-speed);
		_backRight.set(speed);
	}

    private double getUltraSonicInches() {
		double proximity = (LeftUltraSonic.getVoltage() + 0.33104) / 0.17741;
		// SmartDashboard.putNumber("Status", proximity);
		return (double) Math.round(proximity * 1000) / 1000;

	}

    public void AutoGoForward(double speed, double distance){
        frontEncoderRight.reset();
		frontEncoderLeft.reset();

        _frontLeft.set(-0.5);
		_frontRight.set(0.5);
		_backLeft.set(-0.5);
        _backRight.set(0.5);

        while(frontEncoderLeft.get() < distance && frontEncoderRight.get() < distance){

        }
        AutoStop();
     
    }

    public void AutoTurn(Direction direction, double speed, double distance){

        if(direction == Direction.Left){
            _frontLeft.set(speed);
			_frontRight.set(speed);
			_backLeft.set(speed);
            _backRight.set(speed);
            while(frontEncoderRight.get() < distance){}
            AutoStop();
        }
        if(direction == Direction.Right){
            _frontLeft.set(-speed);
			_frontRight.set(-speed);
			_backLeft.set(-speed);
            _backRight.set(-speed);
            while(frontEncoderLeft.get() < distance){}
            AutoStop();
        }
    }

    public void AutoGoBackward(double speed, double distance){
        frontEncoderRight.reset();
        frontEncoderLeft.reset();
       
        _frontLeft.set(speed);
		_frontRight.set(-speed);
		_backLeft.set(speed);
        _backRight.set(-speed);

        while(frontEncoderLeft.get() < distance && frontEncoderRight.get() < distance){

        }
        AutoStop();
    }

    public void AutoStop(){
        _frontLeft.set(0);
		_frontRight.set(0);
		_backLeft.set(0);
        _backRight.set(0);
    }

    public void hatchAlign(Direction direction, double speed) {

		if (HasBeenStopped == false) {

			SmartDashboard.putString("Status", "startedTurning");
			double leftProximity = getUltraSonicInches();
			SmartDashboard.putNumber("Status", leftProximity);

			while (leftProximity >= 30 || leftProximity <= 2) {
				AutoTurn(direction, speed, 5);
				SmartDashboard.putNumber("Status", leftProximity);
				leftProximity = getUltraSonicInches();
				if (m_playStationController.ButtonSquare()) {
					HasBeenStopped = true;
					break;
				}
			}
			AutoStop();

			SmartDashboard.putString("Status", "stoppedTurning");
			double oldLeftProximity = getUltraSonicInches();
			AutoTurn(direction, speed, 5);
			while (oldLeftProximity != getUltraSonicInches() && HasBeenStopped == false) {
				SmartDashboard.putNumber("Left Proximity", getUltraSonicInches());
				SmartDashboard.putString("Status2", "secondLoop");

				AutoTurn(direction, speed, 5);
				oldLeftProximity = leftProximity;
				leftProximity = getUltraSonicInches();
				SmartDashboard.putNumber("Old Left Proximity", oldLeftProximity);
				SmartDashboard.putNumber("Left Proximity", leftProximity);
			}
			AutoStop();
			SmartDashboard.putNumber("Old Left Proximity", oldLeftProximity);
			SmartDashboard.putString("Status2", "stopped Second Loop");
		}
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
			AutoStop();
		}
    }
    
    public void AlignSequence(){
		hatchAlign(Direction.Right, 0.4);
		approachHatch();
    }
}