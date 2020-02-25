package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ClimbLockToggle extends Command {

    boolean flag;

    public ClimbLockToggle() {
        requires(Robot.climber);
        flag = false;
    }

    public void execute() {
        Robot.climber.actuateLocks();
        flag = true;
    }

    public boolean isFinished() {
        return flag;
    }
}