package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;

import frc.robot.PortMap;

public class Conveyor extends Subsystem {

    PWMVictorSPX conveyorWheel;

    public Conveyor() {
        conveyorWheel = new PWMVictorSPX(PortMap.PWM_conveyorWheel);
    }

    public void conveyorForwards() {
        conveyorWheel.set(0.3);
    }

    public void conveyorOff() {
        conveyorWheel.set(0);
    }

    public void conveyorBackwards() {
        conveyorWheel.set(-0.3);
    }
    
    public void initDefaultCommand() {

    }
}