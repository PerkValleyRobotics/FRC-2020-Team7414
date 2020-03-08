package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.AutonConstants;
import frc.robot.Robot;

public class AutonDriveOffLine extends Command {

    double leftStart;
    double rightStart;
    double length;

    public AutonDriveOffLine() {
        requires(Robot.Gavin);
        Robot.Gavin.resetEncoders();
        leftStart = Robot.Gavin.getLeftDegrees();
        rightStart = Robot.Gavin.getRightDegrees();
        length = AutonConstants.offLineLength;
    }
    
    public void execute() {
        Robot.Gavin.standardDrive(0, 0.5);
    }

    public boolean isFinished() {
        return (Robot.Gavin.getLeftDegrees()>leftStart+length && Robot.Gavin.getRightDegrees()>rightStart+length);
        //return false;
    }

    public void end() {
        Robot.Gavin.stop();
    }
}