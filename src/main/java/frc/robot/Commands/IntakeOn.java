package frc.robot.Commands;

import frc.robot.PortMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeOn extends Command {

    public IntakeOn() {
        requires(Robot.intake);
        setInterruptible(true);
    }
    
    public boolean isFinished() {
        return !Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_intake);
    }
    
    public void execute() {
        Robot.intake.intakeOn();
    }

    public void interrupted() {
        Robot.intake.intakeOff();
    }

    public void end() {
        Robot.intake.intakeOff();
    }
}