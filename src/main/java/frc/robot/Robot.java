/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
//plz
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.ColorSensorV3;

import org.opencv.highgui.HighGui;

import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Subsystems.Vision;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.OIHandler;
import frc.robot.Subsystems.*;
import frc.robot.Subsystems.Vision;

public class Robot extends TimedRobot {

  public static AHRS ahrs;
  public static OIHandler oi;
  public static DriveTrain Gavin;// DriveTrain
  public static Shooter shooter;
  public static Intake intake;
  public static int time;
  public static Vision limelight;
  public static double startTime;
  public static boolean timerFlag = false;
  public static WheelOfFortune colorWheel;
  public static boolean redDetected = false;
  public static boolean blueDetected = false;
  public static boolean greenDetected = false;
  public static boolean yellowDetected = false;
  double colorDetected = 0.0;

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  @Override
  public void robotInit() {
    shooter = new Shooter();
    Gavin = new DriveTrain();
    intake = new Intake();
    limelight = new Vision();
    ahrs = new AHRS();
    colorWheel = new WheelOfFortune();
    oi = new OIHandler();
    //ahrs.enableLogging(true);
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  public void robotPeriodic() {
    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
      colorDetected = 0.25;
    } else if (match.color == kRedTarget) {
      colorString = "Red";
      colorDetected = 0.5;
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
      colorDetected = 0.75;
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
      colorDetected = 1.0;
    } else {
      colorString = "Unknown";
      colorDetected = 0.0;
    }
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    limelight.updateLimelight();
    /*SmartDashboard.putNumber("Flywheel RPM:", oi.getRPM());
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
    if (colorDetected == 0.0) {
      redDetected = false;
      blueDetected = false;
      greenDetected = false;
      yellowDetected = false;
    } else if (colorDetected == 0.25) {
      redDetected = false;
      blueDetected = true;
      greenDetected = false;
      yellowDetected = false;
    } else if (colorDetected == 0.5) {
      redDetected = true;
      blueDetected = false;
      yellowDetected = false;
      greenDetected = false;
    } else if (colorDetected == 0.75) {
      redDetected = false;
      blueDetected = false;
      yellowDetected = false;
      greenDetected = true;
    } else if (colorDetected == 1.0) {
      redDetected = false;
      blueDetected = false;
      yellowDetected = true;
      greenDetected = false;
    }
  }

  @Override
  public void autonomousPeriodic() {
    if (timerFlag == false) {
      startTime = System.currentTimeMillis();
      timerFlag = true;
    }
    limelight.updateLimelight();
    /*SmartDashboard.putNumber("Flywheel RPM:", oi.getRPM());
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
    SmartDashboard.putNumber("IMU_Temp_C", ahrs.getTempC());*/
    if (System.currentTimeMillis() - startTime < 1000) {
      Gavin.diffDrive.arcadeDrive(0.4, 0);
    } else if (System.currentTimeMillis() - startTime < 6000) {
      if (limelight.getTv()) {
        Gavin.autonAimbot(Robot.limelight.getTx(), Robot.limelight.getTy(), Robot.limelight.getTv(), Robot.limelight.getRange());
      } else {
        Gavin.diffDrive.arcadeDrive(0.0, 0.4);
      }
    } else if (System.currentTimeMillis() - startTime > 6000) {
      Gavin.diffDrive.arcadeDrive(0,0);
    }
  }
}
