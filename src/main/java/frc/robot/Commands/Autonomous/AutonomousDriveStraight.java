package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonomousDriveStraight extends Command {
    
    double leftEncoderStart;
    double rightEncoderStart;
    double length;

    public AutonomousDriveStraight(double degreesForward) {
        requires(Robot.Gavin);
        leftEncoderStart = Robot.oi.getLeftDegrees();
        rightEncoderStart = Robot.oi.getRightDegrees();
        length = degreesForward;
    }

    public void execute() {
        Robot.Gavin.standardDrive(0.0, 0.4);
    }
    
    //TODO: figure out a better way to track this than forcing both sides of the encoder to reach a certain point
    public boolean isFinished() {
        return Robot.oi.getLeftDegrees()>leftEncoderStart+length && Robot.oi.getRightDegrees()>rightEncoderStart+length;
    }
}