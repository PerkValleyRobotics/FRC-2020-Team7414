package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.AutonConstants;

public class AutonEverythingCenter extends Command {

    long startTime = 0;
    boolean flag = true;
    double leftStart;
    double rightStart;
    double length;
    boolean flag2 = true;
    
    public AutonEverythingCenter() {
        requires(Robot.conveyor);
        requires(Robot.shooter);
        //requires(Robot.intake);
        requires(Robot.Gavin);
        Robot.Gavin.resetEncoders();
        leftStart = Robot.Gavin.getLeftDegrees();
        rightStart = Robot.Gavin.getRightDegrees();
        length = 600000;
        flag2 = true;
    }

    public void execute() {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
            Robot.Gavin.resetEncoders();
            leftStart = Robot.Gavin.getLeftDegrees();
            rightStart = Robot.Gavin.getRightDegrees();
        }
        if (flag2) {
            Robot.Gavin.standardDrive(0.0, -0.4);
            if (Math.abs(Robot.Gavin.getRightDegrees()) > Math.abs(rightStart) + Math.abs(length)) {
                flag2 = false;
                Robot.Gavin.stop();
                startTime = System.currentTimeMillis();
                //Robot.intake.deployClaw();
            }
        } else {
            double time = (System.currentTimeMillis() - startTime)/1000.0;
            if (time > 0 && time < .2) {
                Robot.Gavin.standardDrive(0.35, -0.1);
                Robot.limelight.targetingSight();
            } else if (time > .2 && time < 1.2) {
                Robot.Gavin.aimButWithPID(Robot.limelight.getTx());
            } else if (time > 1.2 && time < 4.7 && Robot.limelight.getTv()) {
                Robot.Gavin.stop();
                Robot.shooter.changePower(0.455);
                //Robot.shooter.spin();
                Robot.shooter.spinVel(7700);
            } else if (time > 4.7 && time < 5.0 && Robot.limelight.getTv()) {
                Robot.conveyor.conveyorForwards();
                Robot.shooter.spinVel(7700);
            } else if (time > 5.0 && time < 6.0 && Robot.limelight.getTv()) {
                Robot.conveyor.conveyorOff();
                Robot.shooter.spinVel(7700);
            } else if (time > 6.0 && time < 6.4 && Robot.limelight.getTv()) {
                Robot.conveyor.conveyorForwards();
                Robot.shooter.spinVel(7700);
            } else if (time > 6.4 && time < 7.4 && Robot.limelight.getTv()) {
                Robot.conveyor.conveyorOff();
                Robot.shooter.spinVel(7700);
            } else if (time > 7.4 && time < 8.4 && Robot.limelight.getTv()) {
                Robot.conveyor.conveyorForwards();
                Robot.shooter.spinVel(7700);
            }
        }
    }

    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > 9500;
    }

    public void end() {
        Robot.conveyor.conveyorOff();
        Robot.Gavin.stop();
        Robot.shooter.stopSpin();
        Robot.limelight.driverSight();
    }
}