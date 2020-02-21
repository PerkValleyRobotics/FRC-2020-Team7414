package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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

	final double k_FORWARD_DIFFERENCE = 0.015;
	final double k_BACKWARD_DIFFERENCE = 0.01;
	final double k_MINIMUM_THRESHOLD = 0.07;
	final double k_MAXIMUM_THRESHOLD_AIM = 0.5;
	final double k_ANGLE_THRESHOLD = 0.6;

	double sumErrorAim = 0;
	double prevErrorAim = 0;

	int degDist = 20;
	double turn;
	double move;

	public DriveTrain() {
		left1 = new PWMVictorSPX(PortMap.PWM_left1);
		left2 = new PWMVictorSPX(PortMap.PWM_left2);
		right1 = new PWMVictorSPX(PortMap.PWM_right1);
		right2 = new PWMVictorSPX(PortMap.PWM_right2);

		left = new SpeedControllerGroup(left1, left2);
		right = new SpeedControllerGroup(right1, right2);
		
		diffDrive = new DifferentialDrive(left, right);
	}

	public void driveStraight() {
		diffDrive.arcadeDrive(0.3, 0.0);
	}
	
	//TODO: what is the fucntional difference supposed to be between this and autonAimbot?
	public void aimbot(double tx, double ty, boolean inRange, double getRange, double y) {
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
	}

	//turn to center on limelight
	public void autonAimbot(double tx, double ty, boolean inRange, double getRange) {
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
	}
	
	public void aimButWithPID(double error) {
		double kP = 0.09;
		double kI = 0.4;
		double kD = 0.005;
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

	public void resetError() {
		sumErrorAim = 0;
		prevErrorAim = 0;
	}

	public double getSumError() {
		return sumErrorAim;
	}
	public void setAdjust() {	
		standardDrive(0.0, 0.5);
	}

	public void setBackwards() {
		standardDrive(0.0, -0.5);
	}

	public void slowDrive(double x, double y) {
		x /= 2.0;
		y /= 2.0;
		standardDrive(x, y);
	}

	public void flipDirection(double x, double y) {
		x *= -1.0;
		y *= -1.0;
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
		if (leftMotorOutput > 0) {
			leftMotorOutput += k_FORWARD_DIFFERENCE;
		} else if (leftMotorOutput < 0) {
			leftMotorOutput -= k_BACKWARD_DIFFERENCE;
		} else {

		}
		diffDrive.tankDrive(leftMotorOutput, rightMotorOutput);
	}

	public void standardTankDrive(double left, double right) {
		double leftDrive;
		if (left > 0) {
			leftDrive = left + k_FORWARD_DIFFERENCE;
		} else if (left < 0) {
			leftDrive = left - k_FORWARD_DIFFERENCE;
		} else {
			leftDrive = 0;
		}
		double rightDrive = (Math.copySign(1, right)) * (Math.abs(right) - k_BACKWARD_DIFFERENCE);
		diffDrive.tankDrive(leftDrive, rightDrive, false);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}
}