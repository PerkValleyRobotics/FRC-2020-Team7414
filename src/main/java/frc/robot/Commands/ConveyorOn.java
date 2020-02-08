package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ConveyorOn extends Command {

    public ConveyorOn() {
        requires(Robot.conveyor);
        setInterruptible(true);
    }
    
    public boolean isFinished() {
        return false;
    }
    
    public void execute() {
        Robot.conveyor.ConveyorOn();
    }

    public void interrupted() {

    }

    public void end() {
        
    }
}