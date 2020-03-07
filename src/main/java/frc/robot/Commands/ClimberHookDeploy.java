package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.PortMap;

public class ClimberHookDeploy extends Command {

    long startTime;
    double powerStart = 0.235;
    double power = powerStart;

    public ClimberHookDeploy() {
        requires(Robot.climber);
        setInterruptible(true);
        startTime = System.currentTimeMillis();
    }

    public void execute() {
        power += (System.currentTimeMillis() - startTime)/500.0 * 0.025;
        if (power > 0.4) {
            power = 0.4;
        }
        Robot.climber.hookDeploy.set(power);
        if (Robot.oi.getReverse()) {
            Robot.climber.retractHook();
        }/* else {
            Robot.climber.deployHook();
        }*/
    }

    public void end() {
        Robot.climber.stopClimb();
    }

    public boolean isFinished() {
        //return System.currentTimeMillis() > startTime + 200;
        return !Robot.oi.getButtonStateXbox(PortMap.XBOX_climbHook);
    }
}