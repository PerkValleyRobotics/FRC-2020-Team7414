package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class IntakePistonToggle extends Command {

    boolean flag = false;

    public IntakePistonToggle() {
        requires(Robot.intake);
        setInterruptible(false);
    }

    public void execute() {
        Robot.intake.actuateClawPiston();
        flag = true;
    }

    public boolean isFinished() {
        return flag;
    }
}