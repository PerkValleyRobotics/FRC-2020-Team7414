package frc.robot.Commands;

import frc.robot.PortMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ConveyorOn extends Command {

    public ConveyorOn() {
        requires(Robot.conveyor);
        setInterruptible(false);
    }
    
    public void execute() {
        if (Robot.oi.getReverse()) {
            Robot.conveyor.conveyorBackwards();
        } else {
            Robot.conveyor.conveyorForwards();
        }
    }

    public boolean isFinished() {
        return !Robot.oi.getButtonStateXbox(PortMap.XBOX_conveyorForwards);
    }

    public void end() {
        Robot.conveyor.conveyorOff();
    }
}