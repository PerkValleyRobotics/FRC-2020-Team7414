package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.PortMap;
import frc.robot.Commands.ColorWheelOff;

public class WheelOfFortune extends Subsystem {

    PWMVictorSPX colorWheel;

    public WheelOfFortune() {
        colorWheel = new PWMVictorSPX(PortMap.PWM_colorWheel);
    }

    public void colorWheelSpin() {
        colorWheel.set(0.35);
    }

    public void colorWheelStop() {
        colorWheel.set(0.0);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ColorWheelOff());
    }
}