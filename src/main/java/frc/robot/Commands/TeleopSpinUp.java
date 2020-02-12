package frc.robot.Commands;

import frc.robot.Robot;
import frc.robot.PortMap;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopSpinUp extends Command {

    public TeleopSpinUp() {
        requires(Robot.shooter);
    }

    public void execute() {
        Robot.shooter.spin();
    }

    public void interrupted() {
        Robot.shooter.stopSpin();
    }

    public void end() {
        Robot.shooter.stopSpin();
    }
    
    public boolean isFinished() {
        return Robot.oi.getTrigger(PortMap.XBOX_rightTriggerAxis) < 0.5;
    }
}