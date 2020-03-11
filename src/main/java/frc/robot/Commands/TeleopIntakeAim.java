package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;

public class TeleopIntakeAim extends Command {
    
    public TeleopIntakeAim() {
        requires(Robot.Gavin);
        setInterruptible(false);
        Robot.Gavin.resetError();
        //Robot.limelight.setPipeline(PortMap.LIMELIGHT_targetingPipeline);
        Robot.limelight.targetingSight();
        Robot.limelight.lightOn();
    }

    public void execute() {
        //Robot.Gavin.intakeAim(Robot.limelight.getTx(), Robot.oi.getJoystickX(), Robot.oi.getJoystickY());
    }

    public boolean isFinished() {
        return !Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_intake);
    }

    public void end() {
        Robot.limelight.lightOff();
        Robot.limelight.driverSight();
        //Robot.limelight.setPipeline(PortMap.LIMELIGHT_defaultPipeline);
    }
}