package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.PortMap;

public class Climb extends Subsystem {

    PWMVictorSPX hookDeploy;
    PWMVictorSPX liftLeft;
    PWMVictorSPX liftRight;

    public Climb() {
        hookDeploy = new PWMVictorSPX(PortMap.PWM_climberHook);
        liftLeft = new PWMVictorSPX(PortMap.PWM_climberLeft);
        liftRight = new PWMVictorSPX(PortMap.PWM_climberRight);
    }

    public void deployHook() {

    }

    public void climbLeft() {

    }

    public void climbRight() {

    }

    public void climbLeftStop() {

    }

    public void climbRightStop() {

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

    protected void initDefaultCommand() {
        
    }
}