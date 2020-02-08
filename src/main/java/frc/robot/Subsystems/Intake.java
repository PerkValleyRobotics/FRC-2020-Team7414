package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

import frc.robot.PortMap;
import frc.robot.Commands.IntakeOff;


public class Intake extends Subsystem {

    PWMVictorSPX intakeWheel;
    
    AnalogInput ultrasonic;

    public Intake() {
        intakeWheel = new PWMVictorSPX(PortMap.intakeWheel);
    }

    public void intakeOff() {
        intakeWheel.set(0);
    }

    public void intakeOn() {
        intakeWheel.set(0.3);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeOff());
    }
}