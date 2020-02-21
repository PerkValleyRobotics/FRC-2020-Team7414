package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.PortMap;

public class TeleopTurnLeft extends Command {

    public TeleopTurnLeft() {
        requires(Robot.Gavin);
    }

    public void execute() {
        Robot.Gavin.turnLeft();
    }

    public boolean isFinished() {
        return !Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_turnLeft);
    }
}