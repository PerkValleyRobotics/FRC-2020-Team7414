package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.Commands.Autonomous.AutonDriveStraight;
import frc.robot.AutonConstants;

public class DriveAimGroup extends CommandGroup {

    public DriveAimGroup(String state) {
        /*if (state.equalsIgnoreCase("Left")) {
            addSequential(new AutonDriveStraight(AutonConstants.driveCenter));
            addSequential(new AutonTurn(200)); //spin 180ish degrees
        } else if (state.equalsIgnoreCase("Right")) {
            addSequential(new AutonDriveStraight(AutonConstants.driveleft));
            addSequential(new AutonTurn(170)); //spin 150ish degrees
        } else if (state.equalsIgnoreCase("Center")) {
            addSequential(new AutonDriveStraight(AutonConstants.driveRight));
            addSequential(new AutonTurn(140)); //spin 120ish degrees
        }
        addSequential(new AutonAim());*/
    }
}