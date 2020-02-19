package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.PortMap;

public class ConveyorBackwards extends Command {

    public ConveyorBackwards() {
        requires(Robot.conveyor);
    }

    public void execute() {
        Robot.conveyor.conveyorBackwards();
    }

    protected void interrupted() {
        //Robot.conveyor.conveyorOff();
    }

    protected void end() {
        Robot.conveyor.conveyorOff();
    }

    public boolean isFinished() {
        return !Robot.oi.getButtonStateXbox(PortMap.XBOX_conveyorBackwards);
    }
}