package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.PortMap;
import frc.robot.Commands.TeleopDrive;

public class DriveTrain extends Subsystem{

	PWMVictorSPX left1;
	PWMVictorSPX left2;
	PWMVictorSPX right1;
	PWMVictorSPX right2;

	SpeedControllerGroup left;
	SpeedControllerGroup right;

	DifferentialDrive diffDrive;


	public DriveTrain(){
		left1 = new PWMVictorSPX(PortMap.left1);
		left2 = new PWMVictorSPX(PortMap.left2);
		right1 = new PWMVictorSPX(PortMap.right1);
		right2 = new PWMVictorSPX(PortMap.right2);

		left = new SpeedControllerGroup(left1, left2);
		right = new SpeedControllerGroup(right1, right2);
		
		diffDrive = new DifferentialDrive(left, right);
	}

	public void drive(double x, double y){
		diffDrive.arcadeDrive(y, x);
	}

	public void initDefaultCommand(){
		setDefaultCommand(new TeleopDrive());
	}
}