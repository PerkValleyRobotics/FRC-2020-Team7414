package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ClimberLift extends Command {

    public ClimberLift() {
        requires(Robot.climber);
    }

    public void execute() {
        Robot.climber.climb();
    }
    
    public void end() {
        Robot.climber.stopClimb();
    }

    protected boolean isFinished() {
        return false;
    }
}