package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ConveyorOff extends Command {

    public ConveyorOff() {
        requires(Robot.conveyor);
        setInterruptible(true);
    }
    
    public boolean isFinished() {
        return false;
    }
    
    public void execute() {
        Robot.conveyor.ConveyorOff();
    }

    public void interrupted() {

    }

    public void end() {
        
    }
}