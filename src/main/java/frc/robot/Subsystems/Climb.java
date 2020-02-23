package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.PortMap;
import frc.robot.StateTrackers.ClimbPistonState;

public class Climb extends Subsystem {

    PWMVictorSPX hookDeploy;
    PWMVictorSPX liftLeft;
    PWMVictorSPX liftRight;
    DoubleSolenoid climbLock;
    public ClimbPistonState pistonState;

    public Climb() {
        hookDeploy = new PWMVictorSPX(PortMap.PWM_climberHook);
        liftLeft = new PWMVictorSPX(PortMap.PWM_climberLeft);
        liftRight = new PWMVictorSPX(PortMap.PWM_climberRight);
        climbLock = new DoubleSolenoid(PortMap.PCM_climbLock1, PortMap.PCM_climbLock2);
        pistonState = ClimbPistonState.UNLOCKED;
        hookDeploy.set(0);
        liftLeft.set(0);
        liftRight.set(0);
    }

    public void deployHook() {

    }

    public void climbLeft() {
        liftLeft.set(0.4);
    }

    public void climbRight() {
        liftRight.set(0.4);
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
        pistonState = ClimbPistonState.LOCKED;
    }

    public void ReleasePiston() {
        climbLock.set(Value.kReverse);
        pistonState = ClimbPistonState.UNLOCKED;
    }

    protected void initDefaultCommand() {
        
    }
}