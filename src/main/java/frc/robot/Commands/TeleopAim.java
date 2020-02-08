package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;

public class TeleopAim extends Command {
    
    public TeleopAim() {
        requires(Robot.Gavin);
        setInterruptible(false);
        Robot.Gavin.resetError();
        Robot.limelight.setPipeline(PortMap.targetingPipeline);
        Robot.limelight.lightOn();
    }

    public void execute() {
        Robot.Gavin.aimButWithPID(Robot.limelight.getTx());
    }

    public boolean isFinished() {
        return Robot.oi.getTrigger(PortMap.leftTriggerAxis) < 0.1;
    }

    public void end() {
        Robot.limelight.lightOff();
        Robot.limelight.setPipeline(PortMap.defaultPipeline);
    }
}