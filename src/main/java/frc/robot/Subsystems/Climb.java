package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.PortMap;
import frc.robot.StateTrackers.ClimbPistonState;

public class Climb extends Subsystem {

    Spark hookDeploy;
    PWMVictorSPX liftLeft;
    PWMVictorSPX liftRight;
    DoubleSolenoid climbLock;
    public ClimbPistonState pistonState;

    public Climb() {
        hookDeploy = new Spark(PortMap.PWM_climberHook);
        liftLeft = new PWMVictorSPX(PortMap.PWM_climberLeft);
        liftRight = new PWMVictorSPX(PortMap.PWM_climberRight);
        climbLock = new DoubleSolenoid(PortMap.PCM_climbLock1, PortMap.PCM_climbLock2);
        pistonState = ClimbPistonState.UNLOCKED;
        climbLock.set(Value.kForward);
        hookDeploy.set(0);
        liftLeft.set(0);
        liftRight.set(0);
    }

    public void deployHook() {
        hookDeploy.set(0.1);
    }

    public void stopHook() {
        hookDeploy.set(0);
    }

    public void reverseClimb() {
        liftLeft.set(0.5);
        liftRight.set(-0.5);
    }

    public void climbLeft() {
        liftLeft.set(-0.5);
    }

    public void climbRight() {
        liftRight.set(0.5);
    }

    public void climbLeftStop() {
        liftLeft.set(0);
    }

    public void climbRightStop() {
        liftRight.set(0);
    }
    
    public void climb() {
        climbLeft();
        climbRight();
    }

    public void stopClimb() {
        liftLeft.set(0);
        liftRight.set(0);
        hookDeploy.set(0);
    }

    public void LockPiston() {
        climbLock.set(Value.kForward);
        pistonState = ClimbPistonState.UNLOCKED;
    }

    public void ReleasePiston() {
        climbLock.set(Value.kReverse);
        pistonState = ClimbPistonState.LOCKED;
    }

    protected void initDefaultCommand() {
        
    }
}