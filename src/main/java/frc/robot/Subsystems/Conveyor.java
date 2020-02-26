package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.*;

import frc.robot.PortMap;
import frc.robot.Commands.ConveyorOff;

public class Conveyor extends Subsystem {

    PWMVictorSPX conveyorTop;
    PWMVictorSPX conveyorBottom;
    public static final double k_SPEED = -0.3;

    public Conveyor() {
        conveyorTop = new PWMVictorSPX(PortMap.PWM_conveyorTop);
        conveyorBottom = new PWMVictorSPX(PortMap.PWM_conveyorBottom);
    }

    public void conveyorForwards() {
        conveyorTop.set(-k_SPEED);
        conveyorBottom.set(-k_SPEED);
        SmartDashboard.putBoolean("DID CONVEYOR RUN: ", true);
    }

    public void conveyorOff() {
        conveyorTop.set(0);
        conveyorBottom.set(0);
    }

    public void conveyorBackwards() {
        conveyorTop.set(k_SPEED);
        conveyorBottom.set(k_SPEED);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ConveyorOff());
    }
}