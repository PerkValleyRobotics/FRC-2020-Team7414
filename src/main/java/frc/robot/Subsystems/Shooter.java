package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.PortMap;
import frc.robot.Commands.TeleopSpinStop;

public class Shooter extends Subsystem {

    PWMVictorSPX leftWheel;
    PWMVictorSPX rightWheel;

    public Shooter() {
        leftWheel = new PWMVictorSPX(PortMap.leftWheel);
        rightWheel = new PWMVictorSPX(PortMap.rightWheel);
    }
    
    public void spin() {
        leftWheel.set(-0.65); 
        rightWheel.set(-0.65);
    }

    public void stopSpin() {
        leftWheel.set(0);
        rightWheel.set(0);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopSpinStop());
    }
}