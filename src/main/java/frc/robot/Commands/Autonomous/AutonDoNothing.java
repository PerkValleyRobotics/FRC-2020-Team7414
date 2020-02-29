package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutonDoNothing extends Command {

    public AutonDoNothing() {
        setInterruptible(true);
    }

    public void execute() {
        
    }

    public boolean isFinished() {
        return false;
    }
}