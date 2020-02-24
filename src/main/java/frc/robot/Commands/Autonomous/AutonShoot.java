package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonShoot extends Command {

    long startTime;

    public AutonShoot() {
        /*requires(Robot.shooter);
        requires(Robot.conveyor);
        startTime = System.currentTimeMillis();*/
    }

    public void execute() {
        /*Robot.shooter.spin();
        if (System.currentTimeMillis() - startTime % 1000 == 0) {
            Robot.conveyor.conveyorForwards();
        } else if (System.currentTimeMillis() - startTime % 1000 == 499) {
            Robot.conveyor.conveyorOff();
        }*/
    }

    public boolean isFinished() {
        //return System.currentTimeMillis() > startTime + 4000;
        return false;
    }

    public void end() {
        Robot.conveyor.conveyorOff();
        Robot.shooter.stopSpin();
    }
}