package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ClimberHookDeploy extends Command {

    public ClimberHookDeploy() {
        requires(Robot.climber);
    }

    public void execute() {
        Robot.climber.deployHook();
    }

    public void end() {
        Robot.climber.stopClimb();
    }

    public boolean isFinished() {
        return false;
    }
}