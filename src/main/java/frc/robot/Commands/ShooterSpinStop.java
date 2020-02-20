package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterSpinStop extends Command {

    public ShooterSpinStop() {
        requires(Robot.shooter);
        setInterruptible(true);
    }
    
    public boolean isFinished() {
        return false;
    }
    
    public void execute() {
        Robot.shooter.stopSpin();
    }

    public void interrupted() {

    }

    public void end() {
        
    }
}