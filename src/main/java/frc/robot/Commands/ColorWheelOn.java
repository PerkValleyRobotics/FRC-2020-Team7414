package frc.robot.Commands;

import frc.robot.PortMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ColorWheelOn extends Command {

    public ColorWheelOn() {
        requires(Robot.colorWheel);
        setInterruptible(true);
    }
    
    public boolean isFinished() {
        return !Robot.oi.getButtonState(PortMap.JOYSTICK_colorWheelActivate);
    }
    
    public void execute() {
        Robot.colorWheel.colorWheelSpin();
    }

    public void interrupted() {

    }

    public void end() {
        
    }
}