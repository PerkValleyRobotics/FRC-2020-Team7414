package frc.robot.Commands;

import frc.robot.PortMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeReverse extends Command {

    public IntakeReverse() {
        requires(Robot.intake);
        setInterruptible(true);
    }
    
    public boolean isFinished() {
        return !Robot.oi.getButtonStateJoystick(PortMap.JOYSTICK_intakeReverse);
    }
    
    public void execute() {
        Robot.intake.intakeReverse();
    }

    public void interrupted() {
        Robot.intake.intakeOff();
    }

    public void end() {
        Robot.intake.intakeOff();
    }
}