package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;

import frc.robot.PortMap;

public class Conveyor extends Subsystem {

    PWMVictorSPX conveyorWheel;
    PWMVictorSPX conveyorWheel2;

    public Conveyor() {
        conveyorWheel = new PWMVictorSPX(PortMap.PWM_conveyor1);
        conveyorWheel2 = new PWMVictorSPX(PortMap.PWM_conveyor2);
    }

    public void conveyorForwards() {
        conveyorWheel.set(0.8);
        conveyorWheel2.set(-0.8);
    }

    public void conveyorOff() {
        conveyorWheel.set(0);
        conveyorWheel2.set(0);
    }

    public void conveyorBackwards() {
        conveyorWheel.set(-0.8);
        conveyorWheel2.set(0.8);
    }
    
    public void initDefaultCommand() {

    }
}