/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.OIHandler;
import frc.robot.Subsystems.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Subsystems.Vision;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;


public class Robot extends TimedRobot {

  AHRS ahrs;
  

  public static OIHandler oi;
  public static DriveTrain Gavin;// DriveTrain
  public static Shooter shooter;
  //public static Intake intake;
  public static int time;
  public static Vision limelight;

  @Override
  public void robotInit() {
    shooter = new Shooter();
    Gavin = new DriveTrain();
    //intake = new Intake();
    oi = new OIHandler();
    limelight = new Vision();
    ahrs = new AHRS();
    //ahrs.enableLogging(true);
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    limelight.updateLimelight();
    SmartDashboard.putNumber("Flywheel RPM:", oi.getRPM());
    SmartDashboard.putBoolean("IMU Connected? ", ahrs.isConnected());
    SmartDashboard.putNumber("Yaw: ", ahrs.getYaw());
    SmartDashboard.putNumber("Pitch: ", ahrs.getPitch());
    SmartDashboard.putNumber("Roll: ", ahrs.getRoll());
    SmartDashboard.putNumber("X Acceleration: ", ahrs.getWorldLinearAccelX());
    SmartDashboard.putNumber("Y Acceleration: ", ahrs.getWorldLinearAccelY());
    SmartDashboard.putNumber("Z Acceleration: ", ahrs.getWorldLinearAccelZ());
    SmartDashboard.putNumber("Velocity_X", ahrs.getVelocityX());
    SmartDashboard.putNumber("Velocity_Y", ahrs.getVelocityY());
    SmartDashboard.putNumber("Displacement_X", ahrs.getDisplacementX());
    SmartDashboard.putNumber("Displacement_Y", ahrs.getDisplacementY());
    SmartDashboard.putNumber("RawGyro_X", ahrs.getRawGyroX());
    SmartDashboard.putNumber("RawGyro_Y", ahrs.getRawGyroY());
    SmartDashboard.putNumber("RawGyro_Z", ahrs.getRawGyroZ());
    SmartDashboard.putNumber("RawAccel_X", ahrs.getRawAccelX());
    SmartDashboard.putNumber("RawAccel_Y", ahrs.getRawAccelY());
    SmartDashboard.putNumber("RawAccel_Z", ahrs.getRawAccelZ());
    SmartDashboard.putNumber("RawMag_X", ahrs.getRawMagX());
    SmartDashboard.putNumber("RawMag_Y", ahrs.getRawMagY());
    SmartDashboard.putNumber("RawMag_Z", ahrs.getRawMagZ());
    SmartDashboard.putNumber("IMU_Temp_C", ahrs.getTempC());
  }

  @Override
  public void autonomousPeriodic() {
    limelight.updateLimelight();
    //Gavin.diffDrive.arcadeDrive(0.2, 0);
    // try {
    //   Thread.sleep(200);
    // } catch (InterruptedException e) {
    //   //TODOAuto-generatedcatchblock
    //   e.printStackTrace();
    // }
    Gavin.diffDrive.arcadeDrive(0, 0);
    SmartDashboard.putNumber("Flywheel RPM:", oi.getRPM());
    SmartDashboard.putBoolean("IMU Connected? ", ahrs.isConnected());
    SmartDashboard.putNumber("Yaw: ", ahrs.getYaw());
    SmartDashboard.putNumber("Pitch: ", ahrs.getPitch());
    SmartDashboard.putNumber("Roll: ", ahrs.getRoll());
    SmartDashboard.putNumber("X Acceleration: ", ahrs.getWorldLinearAccelX());
    SmartDashboard.putNumber("Y Acceleration: ", ahrs.getWorldLinearAccelY());
    SmartDashboard.putNumber("Z Acceleration: ", ahrs.getWorldLinearAccelZ());
    SmartDashboard.putNumber("Velocity_X", ahrs.getVelocityX());
    SmartDashboard.putNumber("Velocity_Y", ahrs.getVelocityY());
    SmartDashboard.putNumber("Displacement_X", ahrs.getDisplacementX());
    SmartDashboard.putNumber("Displacement_Y", ahrs.getDisplacementY());
    SmartDashboard.putNumber("RawGyro_X", ahrs.getRawGyroX());
    SmartDashboard.putNumber("RawGyro_Y", ahrs.getRawGyroY());
    SmartDashboard.putNumber("RawGyro_Z", ahrs.getRawGyroZ());
    SmartDashboard.putNumber("RawAccel_X", ahrs.getRawAccelX());
    SmartDashboard.putNumber("RawAccel_Y", ahrs.getRawAccelY());
    SmartDashboard.putNumber("RawAccel_Z", ahrs.getRawAccelZ());
    SmartDashboard.putNumber("RawMag_X", ahrs.getRawMagX());
    SmartDashboard.putNumber("RawMag_Y", ahrs.getRawMagY());
    SmartDashboard.putNumber("RawMag_Z", ahrs.getRawMagZ());
    SmartDashboard.putNumber("IMU_Temp_C", ahrs.getTempC());
  }
}
