package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class ClimberHookResetPosition extends Command {

    double startPositionLeft;
    double startPositionRight;
    double rightTurnValue = 5000;
    double leftTurnValue = 5000;
    
    public ClimberHookResetPosition() {
        requires(Robot.climber);
        startPositionLeft = Robot.climber.getLeftEncoder();
        startPositionRight = Robot.climber.getRightEncoder();
    }

    public void execute() {
        if (Math.abs(Robot.climber.getRightEncoder()) > Math.abs(startPositionRight)+rightTurnValue) {
            Robot.climber.reverseClimbRight();
        } else {
            Robot.climber.climbRightStop();
        }
        if (Math.abs(Robot.climber.getLeftEncoder()) > Math.abs(startPositionLeft)+leftTurnValue) {
            Robot.climber.reverseClimbLeft();
        } else {
            Robot.climber.climbLeftStop();
        }
        Robot.climber.climb();
    }

    public boolean isFinished() {
        return Math.abs(Robot.climber.getLeftEncoder()) > Math.abs(startPositionLeft) && Math.abs(Robot.climber.getRightEncoder()) > Math.abs(startPositionRight);
    }
}