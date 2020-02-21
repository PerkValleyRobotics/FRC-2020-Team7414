package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.StateTrackers.IntakePositionState;

public class IntakePistonToggle extends Command {

    boolean flag = false;

    public IntakePistonToggle() {
        requires(Robot.intake);
    }

    public void execute() {
        if (Robot.intake.positionState == IntakePositionState.UP) {
            Robot.intake.deployClaw();
        } else {
            Robot.intake.retractClaw();
        }
        flag = true;
    }

    public boolean isFinished() {
        return flag;
    }
}