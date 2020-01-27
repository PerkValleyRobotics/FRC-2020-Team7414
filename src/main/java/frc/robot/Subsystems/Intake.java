package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.PortMap;
import frc.robot.Commands.IntakeOff;


public class Intake extends Subsystem {

    PWMVictorSPX intakeWheel;
    PWMVictorSPX conveyorWheel;

    public Intake() {
        intakeWheel = new PWMVictorSPX(PortMap.intakeWheel);
        conveyorWheel = new PWMVictorSPX(PortMap.conveyorWheel);
    }

    public void intakeOff (){
        intakeWheel.set(0);
        conveyorWheel.set(0);
    }

    public void intakeOn(){
        intakeWheel.set(0.3);
        conveyorWheel.set(0.3);
    }

    protected void initDefaultCommand(){
        setDefaultCommand(new IntakeOff());
    }
}