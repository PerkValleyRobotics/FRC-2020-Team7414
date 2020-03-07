package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class AutonEverything extends Command {

    long startTime;
    boolean flag = true;
    double leftStart;
    double rightStart;
    double length;
    boolean flag2 = true;
    
    public AutonEverything() {
        requires(Robot.conveyor);
        requires(Robot.shooter);
        requires(Robot.intake);
        requires(Robot.Gavin);
        Robot.Gavin.resetEncoders();
        leftStart = Robot.Gavin.getLeftDegrees();
        rightStart = Robot.Gavin.getRightDegrees();
        length = 600000;
        flag2 = true;
    }

    public void execute() {
        if (flag2) {
            Robot.Gavin.standardDrive(0.0, -0.4);
            if (Math.abs(Robot.Gavin.getLeftDegrees()) > Math.abs(length)) {
                flag2 = false;
                Robot.Gavin.stop();
                startTime = System.currentTimeMillis();
                Robot.intake.deployClaw();
            }
        } else {
            double time = (System.currentTimeMillis() - startTime)/1000.0;
            if (time > 0 && time < 1.5) {
                Robot.Gavin.standardDrive(0.35, -0.1);
                Robot.limelight.targetingSight();
            } else if (time > 1.5 && time < 3.5) {
                Robot.Gavin.aimButWithPID(Robot.limelight.getTx());
            } else if (time > 3.5 && time < 5.0 && Robot.limelight.getTv()) {
                Robot.Gavin.stop();
                Robot.shooter.changePower(0.42);
                Robot.shooter.spin();
            } else if (time > 5.0 && time < 8.0 && Robot.limelight.getTv()) {
                Robot.conveyor.conveyorForwards();
            }
        }
    }

    public boolean isFinished() {
        return flag2 || System.currentTimeMillis() - startTime > 8000;
    }

    public void end() {
        Robot.conveyor.conveyorOff();
        Robot.Gavin.stop();
        Robot.shooter.stopSpin();
        Robot.limelight.driverSight();
    }
}