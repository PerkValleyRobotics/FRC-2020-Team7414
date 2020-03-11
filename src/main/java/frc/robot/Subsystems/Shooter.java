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

    double speed = 0.40; //og=.45
    //int velocity = 7400;
    int velocity = 7400;
    double angle;

    double kF = 0.009407;
    double kP = (1023.0*0.18)/1050.0;
    double kI = 0.001;
    double kD = 10.0*(1023.0*0.15)/1050.0;
    double kRight = 0.012630;

    double kP_Right = 0.17*1023.0/700.0;
    double kI_Right = 0.0005;
    double kD_Right = 1.7*1023.0/700.0;
    double kF_Right = 0.4*1023.0/47000.0;
    double kP_Left = 0.17*1023.0/550.0;
    double kI_Left = 0.001;
    double kD_Left = 2.0*1023.0/550.0;
    double kF_Left = 0.4*1023.0/40750.0;

    double prevError = 0;
    double sumError = 0;

    public Shooter() {
        //leftWheel = new PWMTalonSRX(PortMap.PWM_leftWheel);
        //rightWheel = new PWMTalonSRX(PortMap.PWM_rightWheel);
        leftShooter = new TalonSRX(PortMap.CAN_shooterLeft);
        leftShooter.configFactoryDefault();
        leftShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        rightShooter = new TalonSRX(PortMap.CAN_shooterRight);
        rightShooter.configFactoryDefault();
        rightShooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

        leftShooter.setInverted(true);
		leftShooter.setSensorPhase(false);

		leftShooter.configNominalOutputForward(0, 30);
		leftShooter.configNominalOutputReverse(0, 30);
		leftShooter.configPeakOutputForward(1, 30);
		leftShooter.configPeakOutputReverse(-1, 30);

        leftShooter.config_kF(0, kF_Left, 30);
        leftShooter.config_kP(0, kP_Left, 30);
        leftShooter.config_kI(0, kI_Left, 30);
        leftShooter.config_kD(0, kD_Left, 30);
        leftShooter.setInverted(true);
        leftShooter.setSensorPhase(false);
        
        rightShooter.config_kF(0, kF_Right, 30);
        rightShooter.config_kP(0, kP_Right, 30);
        rightShooter.config_kI(0, kI_Right, 30);
        rightShooter.config_kD(0, kD_Right, 30);
        rightShooter.setSensorPhase(false);
    }
    
    public void putSpeed() {
        SmartDashboard.putNumber("Shooter Speed: ", velocity);
        SmartDashboard.putNumber("Right Power: ", rightShooter.getMotorOutputPercent());
        SmartDashboard.putNumber("Right RPM: ", rightShooter.getSelectedSensorVelocity() * (600.0/4096.0));
        SmartDashboard.putNumber("Left Power: ", leftShooter.getMotorOutputPercent());
        SmartDashboard.putNumber("Left RPM: ", leftShooter.getSelectedSensorVelocity() * (600.0/4096.0));
    }

    public void resetSpeed() {
        speed = 0.45;
    }

    public void increaseVelocity(int rpm) {
        velocity += rpm;
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

    public void spinVel() {
        spinVel(velocity);
    }

    public void spinVel(int rpm) {
        if (leftShooter.getSelectedSensorVelocity() > rpm * 4096.0 / 600.0 - 15000) {
            leftShooter.set(ControlMode.Velocity, rpm * 4096.0 / 600.0);
        } else {
            leftShooter.set(ControlMode.PercentOutput, 0.4);
        }

        if (rightShooter.getSelectedSensorVelocity() > rpm * (4096.0/600.0) - 15000) {
            rightShooter.set(ControlMode.Velocity, rpm * (4096.0/600.0));
        } else {
            rightShooter.set(ControlMode.PercentOutput, 0.4);
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