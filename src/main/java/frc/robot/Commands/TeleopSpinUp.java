package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TeleopSpinUp extends Command {

    public TeleopSpinUp() {
        requires(Robot.shooter);
        setInterruptible(false);
    }

    public void execute() {
        Robot.shooter.spin();
    }

    public void interrupted() {
        //Robot.shooter.stopSpin();
    }

    public void end() {
        //Robot.shooter.stopSpin();
    }
    
    public boolean isFinished() {
        return Robot.oi.getTrigger(3) < 0.5;
        //return !Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_shoot);
    }
}