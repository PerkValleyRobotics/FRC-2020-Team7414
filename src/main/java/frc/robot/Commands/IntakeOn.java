package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeOn extends Command {

    public IntakeOn() {
        requires(Robot.intake);
        setInterruptible(true);
    }
    
    public boolean isFinished() {
        return false;
    }
    
    public void execute() {
        Robot.intake.intakeOn();
    }

    public void interrupted() {

    }

    public void end() {
        
    }
}