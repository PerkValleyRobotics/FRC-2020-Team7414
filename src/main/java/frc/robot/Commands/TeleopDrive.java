package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;

public class TeleopDrive extends Command {

	public TeleopDrive() {
		requires(Robot.Gavin);
	}
	
	public void execute() {
		double x = Robot.oi.getJoystickX();
		double y = Robot.oi.getJoystickY();
		if (Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_AdjBut)) {
			Robot.Gavin.setAdjust();
		} else if (Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_backwards)) {
			Robot.Gavin.setBackwards();
		} else if (Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_slowMode)) {
			Robot.Gavin.slowDrive(x, y);
		} else if (Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_flipDirection)) {
			Robot.Gavin.flipDirection(x, y);
		} else {
			Robot.Gavin.standardDrive(x,y);
		}
	} 

	public boolean isFinished() {
		return false;
	}
}