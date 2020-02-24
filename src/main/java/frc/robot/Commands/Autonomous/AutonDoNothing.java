package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutonDoNothing extends Command {

    public AutonDoNothing() {
        //requires(Robot.Gavin);
        //requires(Robot.shooter);
        //requires(Robot.intake);
        //requires(Robot.conveyor);
    }

    public void execute() {
        //Robot.Gavin.stop();
        //Robot.shooter.stopSpin();
        //Robot.intake.intakeOff();
        //Robot.conveyor.conveyorOff();
    }

    public boolean isFinished() {
        return false;
    }
}