package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ColorWheelLiftToggle extends Command {

    boolean flag = false;

    public ColorWheelLiftToggle() {
        requires(Robot.colorWheel);
    }

    public void execute() {
        if (Robot.colorWheel.isRetracted()) {
            Robot.colorWheel.deploy();
        } else {
            Robot.colorWheel.retract();
        }
        flag = true;
    }

    public boolean isFinished() {
        return flag;
    }
}