package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.StateTrackers.ClimbPistonState;

public class ClimbLockToggle extends Command {

    boolean flag;

    public ClimbLockToggle() {
        requires(Robot.climber);
        flag = false;
    }

    public void execute() {
        if (Robot.climber.pistonState == ClimbPistonState.LOCKED) {
            Robot.climber.ReleasePiston();
        } else if (Robot.climber.pistonState == ClimbPistonState.UNLOCKED) {
            Robot.climber.LockPiston();
        }
        flag = true;
    }

    public boolean isFinished() {
        return flag;
    }
}