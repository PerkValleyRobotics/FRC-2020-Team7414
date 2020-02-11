package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;

import frc.robot.PortMap;

public class Conveyor extends Subsystem {

    PWMVictorSPX conveyorWheel;

    public Conveyor() {
        conveyorWheel = new PWMVictorSPX(PortMap.PWM_conveyorWheel);
    }

    public void ConveyorOn() {
        conveyorWheel.set(0.3);
    }

    public void ConveyorOff() {
        conveyorWheel.set(0);
    }
    
    public void initDefaultCommand() {

    }
}