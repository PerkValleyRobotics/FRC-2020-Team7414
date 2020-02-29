package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;
import frc.robot.Robot;

public class ClimberHookLock extends Command {

    public ClimberHookLock() {
        requires(Robot.climber);
    }

    public void execute() {
        Robot.climber.lockHook();
    }

    public boolean isFinished() {
        return !Robot.oi.getButtonStateXbox(PortMap.XBOX_hookLock);
    }

    public void end() {
        Robot.climber.stopHook();
    }
}