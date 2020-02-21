package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.PortMap;

public class TeleopStraightDrive extends Command {

    public TeleopStraightDrive() {
        requires(Robot.Gavin);
    }

    public void execute() {
        Robot.Gavin.driveStraight();
    }

    protected boolean isFinished() {
        return Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_straightForwards);
    }
}