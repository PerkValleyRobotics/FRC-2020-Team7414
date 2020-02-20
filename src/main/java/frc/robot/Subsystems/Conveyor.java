package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;

import frc.robot.PortMap;
import frc.robot.Commands.ConveyorOff;

public class Conveyor extends Subsystem {

    PWMVictorSPX conveyorTop;
    PWMVictorSPX conveyorBottom;
    public static final double SPEED = -0.3; //VARY THIS VALUE FOR CONVEYOR

    public Conveyor() {
        conveyorTop = new PWMVictorSPX(PortMap.PWM_conveyorTop);
        conveyorBottom = new PWMVictorSPX(PortMap.PWM_conveyorBottom);
    }

    public void conveyorForwards() {
        conveyorTop.set(-SPEED);
        conveyorBottom.set(-SPEED);
    }

    public void conveyorOff() {
        conveyorTop.set(0);
        conveyorBottom.set(0);
    }

    public void conveyorBackwards() {
        conveyorTop.set(SPEED);
        conveyorBottom.set(SPEED);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ConveyorOff());
    }
}