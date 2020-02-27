package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;
import frc.robot.Robot;

public class ClimberLift extends Command {

    public ClimberLift() {
        requires(Robot.climber);
        setInterruptible(true);
    }

    public void execute() {
        if (Robot.oi.getReverse()) {
            Robot.climber.reverseClimb();
        } else {
            Robot.climber.climb();
        }
    }
    
    public void end() {
        Robot.climber.stopClimb();
    }

    protected boolean isFinished() {
        return !Robot.oi.getButtonStateXbox(PortMap.XBOX_climbBoth);
    }
}