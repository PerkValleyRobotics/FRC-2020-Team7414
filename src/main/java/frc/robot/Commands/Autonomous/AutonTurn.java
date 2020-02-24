package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonTurn extends Command {

    double turnDegrees;
    double startRightDegrees;
    double startLeftDegrees;

    public AutonTurn(double degrees) {
        /*requires(Robot.Gavin);
        turnDegrees = degrees;
        startRightDegrees = Robot.oi.getRightDegrees();*/
    }

    public void execute() {
        //Robot.Gavin.standardDrive(Math.copySign(1.0, turnDegrees)*0.3, 0);
    }

    //TODO: do this with the NavX, rather than encoder values
    public boolean isFinished() {
        /*if (turnDegrees > 0) {
            return Robot.oi.getRightDegrees() > startRightDegrees + turnDegrees;
        } else if (turnDegrees < 0) {
            return Robot.oi.getRightDegrees() < startRightDegrees + turnDegrees;
        } else {
            return true;
        }*/
        return false;
    }
}