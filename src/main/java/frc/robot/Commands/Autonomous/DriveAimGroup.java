package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.IntakePistonToggle;
import frc.robot.Commands.Autonomous.AutonDriveStraight;
import frc.robot.AutonConstants;

public class DriveAimGroup extends CommandGroup {

    public DriveAimGroup(String state) {
        if (state.equalsIgnoreCase("Right")) {
            addSequential(new AutonDriveStraight(AutonConstants.driveCenter));
            //addSequential(new AutonTurn(30000));
        } else if (state.equalsIgnoreCase("Left")) {
            addSequential(new AutonDriveStraight(AutonConstants.driveleft));
            addSequential(new AutonTurn(70000)); //spin 150ish degrees
        } else if (state.equalsIgnoreCase("Center")) {
            addSequential(new AutonDriveStraight(AutonConstants.driveRight));
            addSequential(new AutonTurn(40000)); //spin 120ish degrees
        }
        addSequential(new IntakePistonToggle());
        addSequential(new AutonAim());
        addSequential(new AutonShoot());
    }
}