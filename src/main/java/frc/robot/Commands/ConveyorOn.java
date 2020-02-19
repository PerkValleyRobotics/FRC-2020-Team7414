package frc.robot.Commands;

import frc.robot.PortMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ConveyorOn extends Command {

    public ConveyorOn() {
        requires(Robot.conveyor);
        setInterruptible(false);
    }
    
    public boolean isFinished() {
        return !Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_conveyorForwards);
    }
    
    public void execute() {
        Robot.conveyor.conveyorForwards();
    }

    public void interrupted() {
        //Robot.conveyor.conveyorOff();
    }

    public void end() {
        //Robot.conveyor.conveyorOff();
    }
}