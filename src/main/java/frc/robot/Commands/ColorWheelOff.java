package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ColorWheelOff extends Command {

    public ColorWheelOff() {
        requires(Robot.colorWheel);
        setInterruptible(true);
    }
    
    public boolean isFinished() {
        return false;
    }

    public void execute() {
        Robot.colorWheel.colorWheelStop();
    }

    public void interrupted() {

    }

    public void end() {
        
    }
}