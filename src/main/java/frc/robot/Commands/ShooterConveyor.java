package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ShooterConveyor extends Command {

    long startTime;

    public ShooterConveyor() {
        requires(Robot.shooter);
        requires(Robot.conveyor);
        startTime = System.currentTimeMillis();
    }

    public void execute() {
        Robot.shooter.spinRPM(1);
        if (System.currentTimeMillis() > startTime + 3250) {
            Robot.conveyor.conveyorForwards();
        }
    }

    public boolean isFinished() {
        return System.currentTimeMillis() > startTime + 4000;
    }

    public void end() {
        Robot.conveyor.conveyorOff();
        Robot.shooter.stopSpin();
    }
}