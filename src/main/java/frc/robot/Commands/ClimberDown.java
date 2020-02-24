package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;
import frc.robot.Robot;

public class ClimberDown extends Command {

    public ClimberDown() {
        requires(Robot.climber);
        setInterruptible(false);
    }

    public void execute() {
        Robot.climber.reverseClimb();
    }
    
    public void end() {
        Robot.climber.stopClimb();
    }

    protected boolean isFinished() {
        return !Robot.oi.getButtonStateXbox(PortMap.XBOX_climbDown);
    }
}