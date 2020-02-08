package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonDriveOffLine extends Command {

    double leftStart;
    double rightStart;
    double length;

    public AutonDriveOffLine() {
        requires(Robot.Gavin);
        leftStart = Robot.oi.getLeftDegrees();
        rightStart = Robot.oi.getRightDegrees();
        length = 1000; //TODO: figure out the actual length
    }
    
    public void execute() {
        Robot.Gavin.standardDrive(0, 0.5);
    }

    //TODO: figure out a better way to track distance than relying on both sides to hit a certian distance
    public boolean isFinished() {
        return (Robot.oi.getLeftDegrees()>leftStart+length && Robot.oi.getRightDegrees()>rightStart+length);
    }
}