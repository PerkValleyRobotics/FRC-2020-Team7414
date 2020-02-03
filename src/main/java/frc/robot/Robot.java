package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OIHandler;
import frc.robot.Commands.Autonomous.AutonDoNothing;
import frc.robot.Commands.Autonomous.AutonDriveOffLine;
import frc.robot.Subsystems.*;
import frc.robot.Vision;

public class Robot extends TimedRobot {

  public static AHRS ahrs;
  public static OIHandler oi;
  public static DriveTrain Gavin;
  public static Shooter shooter;
  public static Intake intake;
  public static int time;
  public static Vision limelight;
  public static double startTime;
  public static WheelOfFortune colorWheel;

  public static boolean timerFlag = false;
  
  public static SendableChooser<Command> autoChooser;
  public static SendableChooser<String> positionChooser;

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private static ColorSensorV3 m_colorSensor;
  private static ColorMatch m_colorMatcher;
  private final Color K_BLUE_TARGET = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color K_GREEN_TARGET = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color K_RED_TARGET = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color K_YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524, 0.113);
  public static boolean redDetected = false;
  public static boolean blueDetected = false;
  public static boolean greenDetected = false;
  public static boolean yellowDetected = false;
  public static double colorDetected = 0.0;

  @Override
  public void robotInit() {
    shooter = new Shooter();
    Gavin = new DriveTrain();
    intake = new Intake();
    limelight = new Vision();
    limelight.lightOff();
    ahrs = new AHRS();
    m_colorSensor = new ColorSensorV3(i2cPort);
    m_colorMatcher = new ColorMatch();
    colorWheel = new WheelOfFortune();
    oi = new OIHandler();
    //ahrs.enableLogging(true);

    autoChooser = new SendableChooser<Command>();
    autoChooser.setDefaultOption("Do Nothing", new AutonDoNothing());
    autoChooser.addOption("Drive Forward", new AutonDriveOffLine());
    SmartDashboard.putData("Autonomous", autoChooser);

    positionChooser = new SendableChooser<String>();
    positionChooser.setDefaultOption("Left", "Left");
    positionChooser.addOption("Center", "Center");
    positionChooser.addOption("Right", "Right");
    SmartDashboard.putData("Position", positionChooser);

    m_colorMatcher.addColorMatch(K_BLUE_TARGET);
    m_colorMatcher.addColorMatch(K_GREEN_TARGET);
    m_colorMatcher.addColorMatch(K_RED_TARGET);
    m_colorMatcher.addColorMatch(K_YELLOW_TARGET);
  }

  @Override
  public void robotPeriodic() {
    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == K_BLUE_TARGET) {
      colorString = "Blue";
      colorDetected = 0.25;
    } else if (match.color == K_RED_TARGET) {
      colorString = "Red";
      colorDetected = 0.5;
    } else if (match.color == K_GREEN_TARGET) {
      colorString = "Green";
      colorDetected = 0.75;
    } else if (match.color == K_YELLOW_TARGET) {
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
    }*/
  }

  @Override
  public void autonomousInit() {
    super.autonomousInit();
    Scheduler.getInstance().add(autoChooser.getSelected());
  }

  @Override
  public void autonomousPeriodic() {
    if (timerFlag == false) {
      startTime = System.currentTimeMillis();
      timerFlag = true;
    }
    limelight.updateLimelight();
    Scheduler.getInstance().run();
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
      Gavin.drive(0, 0.4);
    } else if (System.currentTimeMillis() - startTime < 6000) {
      if (limelight.getTv()) {
        Gavin.autonAimbot(Robot.limelight.getTx(), Robot.limelight.getTy(), Robot.limelight.getTv(), Robot.limelight.getRange());
      } else {
        Gavin.drive(0.4, 0);
      }
    } else if (System.currentTimeMillis() - startTime > 6000) {
      Gavin.drive(0,0);
    }
  }
}
