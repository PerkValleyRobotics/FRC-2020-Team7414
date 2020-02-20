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

    protected void initDefaultCommand() {
        
    }
}