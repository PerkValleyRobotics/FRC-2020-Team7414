package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.CAN;
 
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.PortMap;
import frc.robot.Robot;
import frc.robot.Commands.ShooterSpinStop;

public class Shooter extends Subsystem {

    PWMTalonSRX leftWheel;
    PWMTalonSRX rightWheel;
    TalonSRX leftShooter;
    TalonSRX rightShooter;

    double speed;
    double angle;

    double kFLeft = 0.012476;
    double kRight = 0.012630;

    SpeedControllerGroup bothWheel;

    public Shooter() {
        //leftWheel = new PWMTalonSRX(PortMap.PWM_leftWheel);
        //rightWheel = new PWMTalonSRX(PortMap.PWM_rightWheel);
        leftShooter = new TalonSRX(PortMap.CAN_shooterLeft);
        leftShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightShooter = new TalonSRX(PortMap.CAN_shooterRight);
        rightShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    }
    
    public void spin() {
        //Regression model code
        /*angle = Robot.limelight.getTy();
        speed = (angle + 0) / 30; // plug in actual values, 0 should be required to get lowest value to positive, and then regress.
        if (speed < .5) {
            speed = .5;
        }*/
        //leftWheel.set(-speed); 
        //rightWheel.set(-speed);
        leftShooter.set(ControlMode.PercentOutput, 0.37);
        rightShooter.set(ControlMode.PercentOutput, 0.37);
        
    }

    public void spinBackwards() {
        speed = 0.1;
        leftShooter.set(ControlMode.PercentOutput, -speed);
        rightShooter.set(ControlMode.PercentOutput, -speed);
    }

    public void spin(int rpm) {
        //velocity setpoint is in units/100ms
        leftShooter.set(ControlMode.Velocity, rpm);
        rightShooter.set(ControlMode.Velocity, rpm);
    }

    public void stopSpin() {
        //leftWheel.set(0);
        //rightWheel.set(0);
        leftShooter.set(ControlMode.PercentOutput, 0);
        rightShooter.set(ControlMode.PercentOutput, 0);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterSpinStop());
    }
}