package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMTalonSRX;

import frc.robot.PortMap;
import frc.robot.Commands.ShooterSpinStop;

public class Shooter extends Subsystem {

    PWMTalonSRX leftWheel;
    PWMTalonSRX rightWheel;
    
    SpeedControllerGroup bothWheel;

    public Shooter() {
        leftWheel = new PWMTalonSRX(PortMap.PWM_leftWheel);
        rightWheel = new PWMTalonSRX(PortMap.PWM_rightWheel);
        //bothWheel = new SpeedControllerGroup(leftWheel, rightWheel);
    }
    
    public void spin() {
        leftWheel.set(0.65); 
        rightWheel.set(0.65);
        //bothWheel.set(.35);
    }

    public void stopSpin() {
        leftWheel.set(0);
        rightWheel.set(0);
        //bothWheel.set(0);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterSpinStop());
    }
}