package frc.robot.Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;

public class TeleopAim extends Command {
    
    
    public TeleopAim() {
        requires(Robot.Gavin);
    }

    public void execute() {
        Robot.Gavin.aimbot(Robot.limelight.getTx(), Robot.limelight.getTy(), Robot.limelight.getTv(), Robot.limelight.getRange(), Robot.oi.getJoystickX());
    }

    public boolean isFinished() {
        return ! Robot.oi.getButtonState(PortMap.aimBot);
    }
}