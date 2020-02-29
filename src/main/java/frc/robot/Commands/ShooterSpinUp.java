package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;
import frc.robot.Robot;

public class ShooterSpinUp extends Command {

    public ShooterSpinUp() {
        requires(Robot.shooter);
        setInterruptible(false);
    }

    public void execute() {
        if (Robot.oi.getReverse()) {
            Robot.shooter.spinBackwards();
        } else {
            Robot.shooter.spin();
        }
    }

    public void interrupted() {
        //Robot.shooter.stopSpin();
    }

    public void end() {
        //Robot.shooter.stopSpin();
    }
    
    public boolean isFinished() {
        return Robot.oi.getTrigger(PortMap.XBOX_rightTriggerAxis) < 0.5;
        //return !Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_shoot);
    }
}