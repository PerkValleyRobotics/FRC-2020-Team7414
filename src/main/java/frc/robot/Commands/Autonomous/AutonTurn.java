package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonTurn extends Command {

    double turnDegrees;
    double startRightDegrees;
    double startLeftDegrees;
    long startTime;

    public AutonTurn(double degrees) {
        requires(Robot.Gavin);
        startTime = System.currentTimeMillis();
        turnDegrees = degrees;
        Robot.Gavin.resetEncoders();
        startLeftDegrees = Robot.Gavin.getLeftDegrees();
    }

    public void execute() {
        Robot.Gavin.standardDrive(Math.copySign(1.0, turnDegrees)*0.3, 0);
    }

    public boolean isFinished() {
        if (System.currentTimeMillis() > startTime + 10000) {
            return true; //failsafe
        }

        if (turnDegrees > 0) {
            return Robot.Gavin.getLeftDegrees() > startRightDegrees + turnDegrees;
        } else if (turnDegrees < 0) {
            return Robot.Gavin.getLeftDegrees() < startRightDegrees + turnDegrees;
        } else {
            return true;
        }
    }
}