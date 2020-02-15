package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.PortMap;
import frc.robot.Commands.ColorWheelOff;

public class WheelOfFortune extends Subsystem {

    PWMVictorSPX colorWheel;
    //DoubleSolenoid thruster;

    boolean retracted;

    public WheelOfFortune() {
        colorWheel = new PWMVictorSPX(PortMap.PWM_colorWheel);
        //thruster = new DoubleSolenoid(PortMap.PCM_thruster1, PortMap.PCM_thruster2);
        retracted = true;
    }

    public void colorWheelSpin() {
        colorWheel.set(0.35);
    }

    public void colorWheelStop() {
        colorWheel.set(0.0);
    }

    public void deploy() {
        //thruster.set(Value.kForward);
        retracted = false;
    }

    public void retract() {
        //thruster.set(Value.kReverse);
        retracted = true;
    }

    public boolean isRetracted() {
        return retracted;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ColorWheelOff());
    }
}