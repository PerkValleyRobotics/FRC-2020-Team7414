package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;

public class TeleopDrive extends Command {

	public TeleopDrive(){
		requires(Robot.Gavin);
	}
	
	public void execute(){
		double x = Robot.oi.getJoystickX();
		double y = Robot.oi.getJoystickY();
		if (Robot.oi.getButtonState(PortMap.AdjBut)) {
			Robot.Gavin.setAdjust();
		} else if (Robot.oi.getButtonState(PortMap.backwards)) {
			Robot.Gavin.setBackwards();
		} else if (Robot.oi.getButtonState(PortMap.slowMode)) {
			Robot.Gavin.slowDrive(x, y);
		} else if (Robot.oi.getButtonState(PortMap.flipDirection)) {
			Robot.Gavin.flipDirection(x, y);
		} else {
			Robot.Gavin.drive(x,y);
		}
	} 

	public boolean isFinished() {
		return false;
	}
}