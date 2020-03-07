package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonAim extends Command {
    
    boolean flag;
    long startTime;

    public AutonAim() {
        flag = true;
        requires(Robot.Gavin);
        Robot.limelight.targetingSight();
    }

    public void execute() {
        if (flag) {
            startTime = System.currentTimeMillis();
            flag = false;
        }

        if (Robot.limelight.getTv()) {
            Robot.Gavin.aimButWithPID(Robot.limelight.getTx());
        }
    }

    public boolean isFinished() {
        if (System.currentTimeMillis() > startTime + 10000) {
            return true;
        }
        return Robot.limelight.getTv() && Robot.limelight.getTx() < 1 && Robot.Gavin.getSumError() < 2;
        //return false;
    }

    protected void interrupt() {
        //Robot.limelight.driverSight();
    }

    public void end() {
        Robot.Gavin.stop();
        //Robot.limelight.driverSight();
    }
}