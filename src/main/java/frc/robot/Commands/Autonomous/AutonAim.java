package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonAim extends Command {
    
    public AutonAim() {
        requires(Robot.Gavin);
        Robot.limelight.targetingSight();
    }

    public void execute() {
        Robot.Gavin.aimButWithPID(Robot.limelight.getTx());
    }

    public boolean isFinished() {
        return Robot.limelight.getTx() < 2 && Robot.Gavin.getSumError() < 2;
    }

    protected void interrupt() {
        Robot.limelight.driverSight();
    }

    public void end() {
        Robot.limelight.driverSight();
    }
}