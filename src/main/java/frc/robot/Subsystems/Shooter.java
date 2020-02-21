package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMTalonSRX;

import frc.robot.PortMap;
import frc.robot.Robot;
import frc.robot.Commands.ShooterSpinStop;

public class Shooter extends Subsystem {

    PWMTalonSRX leftWheel;
    PWMTalonSRX rightWheel;
    double speed;
    double angle;

    SpeedControllerGroup bothWheel;

    public Shooter() {
        leftWheel = new PWMTalonSRX(PortMap.PWM_leftWheel);
        rightWheel = new PWMTalonSRX(PortMap.PWM_rightWheel);
        //bothWheel = new SpeedControllerGroup(leftWheel, rightWheel);
    }
    
    public void spin() {
        //Regression model code
        angle = Robot.limelight.getTy();
        speed = (angle + 0) / 30; // plug in actual values, 0 should be required to get lowest value to positive, and then regress.
        if (speed < .5) {
            speed = .5;
        }
        leftWheel.set(-speed); 
        rightWheel.set(speed);
        //bothWheel.set(.35);
    }

    public void stopSpin() {
        leftWheel.set(0);
        rightWheel.set(0);
        //bothWheel.set(0);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterSpinStop());
    }
}