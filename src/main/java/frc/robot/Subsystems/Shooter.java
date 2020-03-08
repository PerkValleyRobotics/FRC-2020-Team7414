package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PWMTalonSRX;
 
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.PortMap;
import frc.robot.Commands.ShooterSpinStop;

public class Shooter extends Subsystem {

    //PWMTalonSRX leftWheel;
    //PWMTalonSRX rightWheel;
    TalonSRX leftShooter;
    TalonSRX rightShooter;

    double speed = 0.45; //og=.45
    double angle;

    double kF = 0.009407;
    double kP = (1023.0*0.18)/1050.0;
    double kI = 0.001;
    double kD = 10.0*(1023.0*0.15)/1050.0;
    double kRight = 0.012630;

    double prevError = 0;
    double sumError = 0;

    public Shooter() {
        //leftWheel = new PWMTalonSRX(PortMap.PWM_leftWheel);
        //rightWheel = new PWMTalonSRX(PortMap.PWM_rightWheel);
        leftShooter = new TalonSRX(PortMap.CAN_shooterLeft);
        leftShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightShooter = new TalonSRX(PortMap.CAN_shooterRight);
        rightShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        leftShooter.config_kF(0, kF, 30);
        leftShooter.config_kP(0, kP, 30);
        leftShooter.config_kI(0, kI, 30);
        leftShooter.config_kD(0, kD, 30);
        leftShooter.setInverted(true);
        leftShooter.setSensorPhase(false);

        rightShooter.config_kF(0, kF, 30);
        rightShooter.config_kP(0, kP, 30);
        rightShooter.config_kI(0, kI, 30);
        rightShooter.config_kD(0, kD, 30);
        rightShooter.setSensorPhase(false);
    }
    
    public void putSpeed() {
        SmartDashboard.putNumber("Shooter Speed: ", speed);
        SmartDashboard.putNumber("Right RPM: ", rightShooter.getSelectedSensorVelocity() * (600.0/4096.0));
        SmartDashboard.putNumber("Left RPM: ", leftShooter.getSelectedSensorVelocity() * (600.0/4096.0));
    }

    public void resetSpeed() {
        speed = 0.45;
    }

    public void spin() {
        leftShooter.set(ControlMode.PercentOutput, speed);
        rightShooter.set(ControlMode.PercentOutput, speed);
        if (leftShooter.getSelectedSensorVelocity() > 30000) {

        }
        //leftShooter.set(ControlMode.Velocity, 40000);
        /*angle = Robot.limelight.getTy();
        speed = (angle + 0) / 30; // plug in actual values, 0 should be required to get lowest value to positive, and then regress.
        if (speed < .5) {
            speed = .5;
        }*/
        //leftWheel.set(-speed); 
        //rightWheel.set(-speed);
    }

    public void spinVel(int rpm) {
        if (leftShooter.getSelectedSensorVelocity() > 20000) {
            leftShooter.set(ControlMode.Velocity, rpm * (4096.0/600.0));
        } else {
            leftShooter.set(ControlMode.PercentOutput, speed);
        }

        if (rightShooter.getSelectedSensorVelocity() > 20000) {
            rightShooter.set(ControlMode.Velocity, rpm * (4096.0/600.0));
        } else {
            rightShooter.set(ControlMode.PercentOutput, speed);
        }
    }

    public void changePower(double amount) {
        speed = amount;
        // if (speed > 0.65) {
        //     speed = 0.65;
        // }
    }

    public void increasePower(double amount) {
        speed += amount;
        if (speed > 0.6) {
            speed = 0.6;
        }
    }

    public void spinBackwards() {
        leftShooter.set(ControlMode.PercentOutput, -0.2);
        rightShooter.set(ControlMode.PercentOutput, -0.2);
    }

    public void spinRPM(int rpm) {
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

    public void shooterPID(double error) { // Skeleton for future code: this has to change in terms of values
        double kP = 0.017;
        double kI = 0.038;
        double kD = 0.0000;
        double diffError = error - prevError;
        sumError = error * 0.02;

        double speed = error*kP + sumError*kI + diffError*kD;
        
        spinPID(speed);
    }

    public void spinPID(double speed) {
        leftShooter.set(ControlMode.Velocity, speed);
        rightShooter.set(ControlMode.Velocity, speed);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterSpinStop());
    }
}