package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ClimberHookDeployTimed extends Command {

    long startTime;

    public ClimberHookDeployTimed() {
        requires(Robot.climber);
        startTime = System.currentTimeMillis();
    }

    public void execute() {
        if (System.currentTimeMillis() < startTime + 1500) {
            Robot.climber.deployHook();
        } else {
            Robot.climber.lockHook();
        }
        
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {
        Robot.climber.stopHook();
    }
}