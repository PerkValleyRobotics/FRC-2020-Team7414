package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.PortMap;

public class TeleopTurnRight extends Command {

    public TeleopTurnRight() {
        requires(Robot.Gavin);
    }

    public void execute() {
        Robot.Gavin.turnRight();
    }

    public boolean isFinished() {
        return !Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_turnRight);
    }
}