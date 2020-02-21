package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.PortMap;

public class ClimberLiftOneSide extends Command {

    public ClimberLiftOneSide() {
        requires(Robot.climber);
    }

    public void execute() {
        if (Robot.oi.getButtonStateXbox(PortMap.XBOX_climbLeft)) {
            Robot.climber.climbLeft();
        } else {
            Robot.climber.climbLeftStop();
        }
        if (Robot.oi.getButtonStateXbox(PortMap.XBOX_climbRight)) {
            Robot.climber.climbRight();
        } else {
            Robot.climber.climbRightStop();
        }
    }

    public boolean isFinished() {
        return false;
    }
}