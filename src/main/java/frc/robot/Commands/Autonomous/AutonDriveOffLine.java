package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonDriveOffLine extends Command {
    
    long startTime;

    public AutonDriveOffLine() {
        requires(Robot.Gavin);
        startTime = System.currentTimeMillis();
    }
    
    public void execute() {
        Robot.Gavin.drive(0, 0.5);
    }

    public boolean isFinished() {
        return (System.currentTimeMillis() > startTime+1000); //for now
    }
}