package frc.robot.Commands;

import frc.robot.PortMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ColorWheelOn extends Command {

    public ColorWheelOn() {
        requires(Robot.colorWheel);
        setInterruptible(true);
    }
    
    public void execute() {
        Robot.colorWheel.colorWheelSpin();
    }
    
    public boolean isFinished() {
        return !Robot.oi.getButtonStateXbox(PortMap.XBOX_colorWheelSpin);
    }

    public void interrupted() {
        Robot.colorWheel.colorWheelStop();
    }

    public void end() {
        Robot.colorWheel.colorWheelStop();
    }
}