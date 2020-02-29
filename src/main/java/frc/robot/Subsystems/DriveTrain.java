package frc.robot.Subsystems;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.PortMap;
import frc.robot.Commands.TeleopDrive;

public class DriveTrain extends Subsystem {

	PWMVictorSPX left1;
	PWMVictorSPX left2;
	PWMVictorSPX right1;
	PWMVictorSPX right2;

	SpeedControllerGroup left;
	SpeedControllerGroup right;

	DifferentialDrive diffDrive;

	boolean squaring = false;

	final double k_FORWARD_DIFFERENCE = 0.023;
	final double k_BACKWARD_DIFFERENCE = 0.03;
	final double k_MINIMUM_THRESHOLD = 0.1;
	final double k_MAXIMUM_THRESHOLD_AIM = 0.3;
	final double k_ANGLE_THRESHOLD = 0.45;

	double sumErrorAim = 0;
	double prevErrorAim = 0;

	int degDist = 20;
	double turn;
	double move;

	Encoder leftDriveEncoder;
	Encoder rightDriveEncoder;

	public DriveTrain() {
		left1 = new PWMVictorSPX(PortMap.PWM_left1);
		left2 = new PWMVictorSPX(PortMap.PWM_left2);
		right1 = new PWMVictorSPX(PortMap.PWM_right1);
		right2 = new PWMVictorSPX(PortMap.PWM_right2);

		left = new SpeedControllerGroup(left1, left2);
		right = new SpeedControllerGroup(right1, right2);
		
		diffDrive = new DifferentialDrive(left, right);

		leftDriveEncoder = new Encoder(PortMap.DIO_leftDriveEncoder1, PortMap.DIO_leftDriveEncoder2);
		leftDriveEncoder.setReverseDirection(true);
		leftDriveEncoder.reset();
		rightDriveEncoder = new Encoder(PortMap.DIO_rightDriveEncoder1, PortMap.DIO_rightDriveEncoder2);
		rightDriveEncoder.reset();
	}

	public void resetEncoders() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}

	public double getRightDegrees() {
		double position = rightDriveEncoder.get(); //in counts
		position = position / 4.0;  //1 pulse per 4 counts
		position = position * rightDriveEncoder.getDistancePerPulse(); //revolutions per pulse
		position = position * 360; //360 degrees per revolution
		return position;
	}

	public double getLeftDegrees() {
		double position = leftDriveEncoder.get();
		position = position / 4.0;
		position  = position * leftDriveEncoder.getDistancePerPulse();
		position = position * 360;
		return position;
	}

	public void driveStraight() {
		diffDrive.arcadeDrive(0.3, 0.0);
	}
	
	public void aimButWithPID(double error) {
		double kP = 0.017;
		double kI = 0.038;
		double kD = 0.000;
		double diffErrorAim = error - prevErrorAim;
		sumErrorAim = error*.02;

		double speed = error*kP + sumErrorAim*kI + diffErrorAim*kD;
		if (Math.abs(speed) < k_MINIMUM_THRESHOLD) {
			speed = Math.copySign(1, speed) * k_MINIMUM_THRESHOLD;
		} else if (Math.abs(speed) > k_MAXIMUM_THRESHOLD_AIM) {
			speed = Math.copySign(1, speed) * k_MAXIMUM_THRESHOLD_AIM;
		} 
		if (Math.abs(error) < k_ANGLE_THRESHOLD) {
			speed = 0;
		}
		prevErrorAim = error;
		standardTankDrive(speed, -speed);
	}

	public void intakeAim(double error, double x, double y) {
		double kP = 0.017;
		double kI = 0.038;
		double kD = 0.000;
		double diffErrorAim = error - prevErrorAim;
		sumErrorAim = error * 0.02;

		double speed = error*kP + sumErrorAim*kI + diffErrorAim*kD;
		if (Math.abs(speed) < k_MINIMUM_THRESHOLD) {
			speed = Math.copySign(1, speed);
		} else if (Math.abs(speed) > k_MAXIMUM_THRESHOLD_AIM) {
			speed = Math.copySign(1, speed) * k_MAXIMUM_THRESHOLD_AIM;
		}
		if (Math.abs(error) < k_ANGLE_THRESHOLD) {
			speed = 0;
		}
		prevErrorAim = error;
		standardDrive(speed, y);
	}

	public void resetError() {
		sumErrorAim = 0;
		prevErrorAim = 0;
	}

	public double getSumError() {
		return sumErrorAim;
	}

	public void straightDrive() {	
		standardDrive(0.0, 0.5);
	}

	public void straightBackwards() {
		standardDrive(0.0, -0.5);
	}

	public void turnLeft() {
		standardTankDrive(-0.3, 0.3);
	}

	public void turnRight() {
		standardTankDrive(0.3, -0.3);
	}

	public void stop() {
		diffDrive.tankDrive(0, 0);
	}

	public void slowDrive(double x, double y) {
		x /= 2.0;
		y /= 2.0;
		standardDrive(x, y);
	}

	public void flipDirection(double x, double y) {
		x *= -1.0;
		y *= -1.0;
		standardDrive(x, y);
	}

	public void standardDrive(double x, double y) {
		
		//deadzone for y
		if (y <= 0.05 && y >= -0.05) {
			y = 0;
		}
		if (y > 0) {
			//deadzone for x forwards
			if (x <= 0.05 && x >= -0.05) {
				x = 0;
			}
			//bigger deadzone for x going backwards
		} else if (y < 0) {
			if (x <= 0.08 && x >= -0.08) {
				x = 0;
			}
		}
		//no deadzone for x when not going forwards or backwards to be better at turning
		
		double maxInput = Math.copySign(Math.max(Math.abs(y), Math.abs(x)), y);
		double leftMotorOutput = 0.0;
		double rightMotorOutput = 0.0;
		if (y >= 0.0) {
			// First quadrant, else second quadrant
			if (x >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = y - x;
			} else {
				leftMotorOutput = y + x;
				rightMotorOutput = maxInput;
			}
		} else {
			// Third quadrant, else fourth quadrant
			if (x >= 0.0) {
				leftMotorOutput = y + x;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = y - x;
			}
		}
		if (rightMotorOutput > 0) {
			rightMotorOutput += k_FORWARD_DIFFERENCE;
		} else if (rightMotorOutput < 0) {
			rightMotorOutput -= k_BACKWARD_DIFFERENCE;
		} else {

		}
		diffDrive.tankDrive(leftMotorOutput, rightMotorOutput);
	}

	public void standardTankDrive(double left, double right) {
		double rightDrive;
		if (right > 0) {
			rightDrive = right + k_FORWARD_DIFFERENCE;
		} else if (right < 0) {
			rightDrive = right - k_FORWARD_DIFFERENCE;
		} else {
			rightDrive = 0;
		}
		double leftDrive = (Math.copySign(1, left)) * (Math.abs(left) - k_BACKWARD_DIFFERENCE);
		diffDrive.tankDrive(leftDrive, rightDrive, false);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}

	//TODO: what is the functional difference supposed to be between this and autonAimbot?
	/*public void aimbot(double tx, double ty, boolean inRange, double getRange, double y) {
		if (inRange == true){
			if (getRange < 18.0) {
			turn = tx / 45;
			move = (degDist - ty) / 5;
			if (move > .5) {
				move = .5 * move / Math.abs(move);
			}
			if (move < .3) {
				move = .3 * move / Math.abs(move);
			}
			if (Math.abs(turn) < .3) {
				turn = .3 * turn / Math.abs(turn);
			}
			if (Math.abs(tx) < .5) {
				turn = 0;
			}
			if (Math.abs(degDist - ty) < .5) {
				move = 0;
			}
			if (Math.abs(tx) > .5 || Math.abs(degDist - ty) > .5) {
				diffDrive.arcadeDrive(move, turn);
			} else {
				diffDrive.arcadeDrive(0,0);
				}
			} else {
				turn = tx / 45;
				if (Math.abs(turn) < .3) {
					turn = .3 * turn / Math.abs(turn);
				}
				if (Math.abs(tx) < .5) {
					turn = 0;
				}
				diffDrive.arcadeDrive(0, turn);
			}
		} else {
			diffDrive.arcadeDrive(0.0, y);
		}
	}*/

	//turn to center on limelight
	/*public void autonAimbot(double tx, double ty, boolean inRange, double getRange) {
		if (inRange == true){
			if (getRange < 18.0) {
			turn = tx / 45;
			move = (degDist - ty) / 5;
			if (move > .5) {
				move = .5 * move / Math.abs(move);
			}
			if (move < .3) {
				move = .3 * move / Math.abs(move);
			}
			if (Math.abs(turn) < .3) {
				turn = .3 * turn / Math.abs(turn);
			}
			if (Math.abs(tx) < .5) {
				turn = 0;
			}
			if (Math.abs(degDist - ty) < .5) {
				move = 0;
			}
			move = 0; //temporary, might change if needed
			if (Math.abs(tx) > .5 || Math.abs(degDist - ty) > .5) {
				standardDrive(turn, move);
			} else {
				standardDrive(0,0);
				}
			} else {
				turn = tx / 45;
				if (Math.abs(turn) < .3) {
					turn = .3 * turn / Math.abs(turn);
				}
				if (Math.abs(tx) < .5) {
					turn = 0;
				}
				standardDrive(turn, 0);
			}
		}
	}*/
}