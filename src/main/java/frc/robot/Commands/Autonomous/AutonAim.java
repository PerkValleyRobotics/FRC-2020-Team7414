package frc.robot.Commands.Autonomous;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AutonAim extends Command {
    
    public AutonAim() {
        requires(Robot.Gavin);
    }

    public void execute() {
        Robot.Gavin.autonAimbot(Robot.limelight.getTx(), Robot.limelight.getTy(), Robot.limelight.getTv(), Robot.limelight.getRange());
    }

    public boolean isFinished() {
        return false;
    }
}