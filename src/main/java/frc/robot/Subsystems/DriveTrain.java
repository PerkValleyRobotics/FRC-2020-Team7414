package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.PortMap;
import frc.robot.Commands.TeleopDrive;



public class DriveTrain extends Subsystem{

	PWMVictorSPX left1;
	PWMVictorSPX left2;
	PWMVictorSPX right1;
	PWMVictorSPX right2;

	SpeedControllerGroup left;
	SpeedControllerGroup right;

	public DifferentialDrive diffDrive;

	boolean squaring = false;

	double forwardsDifference = 0.015;
	double backwardsDifference = 0.01;

	int degDist = 20;
	double turn;
	double move;

	public DriveTrain(){
		left1 = new PWMVictorSPX(PortMap.left1);
		left2 = new PWMVictorSPX(PortMap.left2);
		right1 = new PWMVictorSPX(PortMap.right1);
		right2 = new PWMVictorSPX(PortMap.right2);

		left = new SpeedControllerGroup(left1, left2);
		right = new SpeedControllerGroup(right1, right2);
		
		diffDrive = new DifferentialDrive(left, right);
	}

	public void driveStraight() {
		diffDrive.arcadeDrive(0.3, 0.0);
	}
	
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
///////////////////////////////////////////////////////////////////////////////////////
	public void autonAimbot(double tx, double ty, boolean inRange, double getRange){
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
		}
	}
	

	public void setAdjust() {	
		drive(0.0, 0.5);
	}

	public void setBackwards() {
		drive(0.0, -0.5);
	}

	public void slowDrive(double x, double y) {
		x /= 2.0;
		y /= 2.0;
		drive(x, y);
	}

	public void flipDirection(double x, double y){
		x *= -1.0;
		y *= -1.0;
	}

	public void drive(double x, double y) {
		// if (y <= 0.05 && y >= -0.05) {
		// 	y = 0;
		// } else {
		// 	x += difference;
		// }
		// //x /= 2;
		// if (y < 0) {
		// 	x *= -1;
		// }
		// diffDrive.arcadeDrive(y, x, squaring);
		
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
			leftMotorOutput += forwardsDifference;
		} else if (leftMotorOutput < 0) {
			leftMotorOutput -= backwardsDifference;
		}
		diffDrive.tankDrive(leftMotorOutput, rightMotorOutput);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}
}