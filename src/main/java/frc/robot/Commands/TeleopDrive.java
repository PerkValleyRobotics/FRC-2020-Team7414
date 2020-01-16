package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command{

	public TeleopDrive(){
		requires(Robot.Gavin);

	}
	
	public void execute(){
		double x = Robot.oi.getJoystickX();
		double y = Robot.oi.getJoystickY();
		Robot.Gavin.drive(x,y);

	} 

	public boolean isFinished() {
		return false;
	}
}