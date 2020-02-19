package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

import frc.robot.PortMap;
import frc.robot.Commands.IntakeOff;


public class Intake extends Subsystem {

    PWMVictorSPX intakeWheel;
    public static final double SPEED = -0.25;
    
    AnalogInput ultrasonic;

    public Intake() {
        intakeWheel = new PWMVictorSPX(PortMap.PWM_intakeWheel);
    }

    public void intakeOff() {
        intakeWheel.set(0);
    }

    public void intakeOn() {
        intakeWheel.set(-SPEED); //VARY THIS VALUE FOR INTAKE SPEED
    }

    public void intakeReverse() {
        intakeWheel.set(SPEED);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeOff());
    }
}