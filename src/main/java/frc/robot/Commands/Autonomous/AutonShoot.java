package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonShoot extends Command {

    long startTime;
    boolean flag = true;
    
    public AutonShoot() {
        requires(Robot.shooter);
        requires(Robot.conveyor);
        startTime = System.currentTimeMillis();
    }

    public void execute() {
        if (flag) {
            startTime = System.currentTimeMillis();
            flag = false;
        }
        if (Robot.limelight.getTv()) {
            Robot.shooter.changePower(0.35);
            Robot.shooter.spin();
            if (System.currentTimeMillis() - startTime % 1000 < 100) {
                Robot.conveyor.conveyorForwards();
            } else if (System.currentTimeMillis() - startTime % 1000 > 450 && System.currentTimeMillis() - startTime % 1000 < 550) {
                Robot.conveyor.conveyorOff();
            }
        }
    }

    public boolean isFinished() {
        return System.currentTimeMillis() > startTime + 4000;
        //return true;
    }

    public void end() {
        Robot.conveyor.conveyorOff();
        Robot.shooter.stopSpin();
        Robot.limelight.driverSight();
    }
}