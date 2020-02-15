package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.Commands.Autonomous.AutonDriveStraight;
import frc.robot.StateTrackers.StartingState;
import frc.robot.AutonConstants;

public class DriveAimGroup extends CommandGroup {

    public DriveAimGroup(StartingState state) {
        if (state == StartingState.CENTER) {
            addSequential(new AutonDriveStraight(AutonConstants.driveCenter));
            addSequential(new AutonAim());
        } else if (state == StartingState.LEFT) {
            addSequential(new AutonDriveStraight(AutonConstants.driveleft));
            addSequential(new AutonAim());
        } else if (state == StartingState.RIGHT) {
            addSequential(new AutonDriveStraight(AutonConstants.driveRight));
            addSequential(new AutonAim());
        }
    }
}