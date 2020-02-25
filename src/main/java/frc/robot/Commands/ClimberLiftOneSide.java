package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.PortMap;

public class ClimberLiftOneSide extends Command {

    public ClimberLiftOneSide() {
        requires(Robot.climber);
        setInterruptible(false);
    }

    public void execute() {
        if (Robot.oi.getButtonStateXbox(PortMap.XBOX_climbLeft)) {
            if (Robot.oi.getReverse()) {
                Robot.climber.reverseClimbLeft();
            } else {
                Robot.climber.climbLeft();
            }
        } else {
            Robot.climber.climbLeftStop();
        }
        if (Robot.oi.getButtonStateXbox(PortMap.XBOX_climbRight)) {
            if (Robot.oi.getReverse()) {
                Robot.climber.reverseClimbRight();
            } else {
                Robot.climber.climbRight();
            }
        } else {
            Robot.climber.climbRightStop();
        }
    }

    public boolean isFinished() {
        return !(Robot.oi.getButtonStateXbox(PortMap.PWM_climberLeft) || Robot.oi.getButtonStateXbox(PortMap.PWM_climberRight));
    }

    public void end() {
        Robot.climber.stopClimb();
    }
}