package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.*;

import frc.robot.Robot;
import frc.robot.PortMap;

public class ConveyorOnShoot extends Command {

    public ConveyorOnShoot() {
        requires(Robot.shooter);
    }

    public void execute() {
        Robot.shooter.spin();
    }

    public boolean isFinished() {
        return Robot.oi.getTrigger(PortMap.XBOX_rightTriggerAxis) < 0.5;
    }

    public void end() {
        
    }
}