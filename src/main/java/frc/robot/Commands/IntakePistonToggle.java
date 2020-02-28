package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class IntakePistonToggle extends Command {

    boolean flag = false;

    public IntakePistonToggle() {
        requires(Robot.intake);
        setInterruptible(true);
    }

    public void execute() {
        Robot.intake.actuateClawPiston();
        flag = true;
    }

    public void interrupted() {
        end();
    }

    public boolean isFinished() {
        return flag;
    }

    protected void end() {

    }
}