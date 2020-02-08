package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutonDoNothing extends Command {

    public AutonDoNothing() {
        requires(Robot.Gavin);
        requires(Robot.shooter);
        requires(Robot.intake);
    }

    public void execute() {
        Robot.Gavin.standardDrive(0, 0);
        Robot.shooter.stopSpin();
        Robot.intake.intakeOff();
    }

    public boolean isFinished() {
        return false;
    }
}