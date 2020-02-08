package frc.robot.Commands.Autonomous;

import frc.robot.PortMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AutonAim extends Command {
    
    public AutonAim() {
        requires(Robot.Gavin);
    }

    public void execute() {
        Robot.limelight.setPipeline(PortMap.targetingPipeline);
        Robot.Gavin.autonAimbot(Robot.limelight.getTx(), Robot.limelight.getTy(), Robot.limelight.getTv(), Robot.limelight.getRange());
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        Robot.limelight.setPipeline(PortMap.defaultPipeline);
    }
}