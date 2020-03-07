package frc.robot;

import com.kauailabs.navx.frc.AHRS;

//import com.revrobotics.ColorSensorV3;
//import com.revrobotics.ColorMatchResult;
//import com.revrobotics.ColorMatch;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.cameraserver.CameraServer;

import frc.robot.Commands.*;
import frc.robot.OIHandler;
import frc.robot.Commands.Autonomous.AutonDoNothing;
import frc.robot.Commands.Autonomous.AutonDriveOffLine;
import frc.robot.Commands.Autonomous.AutonDriveStraight;
import frc.robot.Commands.Autonomous.AutonEverything;
import frc.robot.Commands.Autonomous.DriveAimGroup;
import frc.robot.StateTrackers.IntakePositionState;
import frc.robot.StateTrackers.StartingState;
import frc.robot.Subsystems.*;
import frc.robot.Vision;

public class Robot extends TimedRobot {

  public static DriveTrain Gavin;
  public static Intake intake;
  public static Shooter shooter;
  public static Conveyor conveyor;
  public static Climb climber;
  public static WheelOfFortune colorWheel;

  public static Rev2mDistanceSensor distanceSensor;
  //public static AHRS ahrs;
  public static Vision limelight;
  public static I2C.Port indexerPort = I2C.Port.kOnboard;
  public static CameraServer server;
  public static OIHandler oi;

  public static Compressor compressor;

  public static int time;
  public static double startTime;
  public static boolean conveyorOn;
  public static int counter = 0;
  public static boolean shooterTriggerHeld;
  public static double timePressed;
  public static boolean timerFlag = false;
  boolean speedFlag = true;
  
  public static SendableChooser<Command> autoChooser;
  public static SendableChooser<String> positionChooser;

  //private final I2C.Port i2cPort = I2C.Port.kOnboard;
  //private static ColorSensorV3 m_colorSensor;
  //private static ColorMatch m_colorMatcher;
  //private final Color k_BLUE_TARGET = ColorMatch.makeColor(0.143, 0.427, 0.429);
  //private final Color k_GREEN_TARGET = ColorMatch.makeColor(0.197, 0.561, 0.240);
  //private final Color k_RED_TARGET = ColorMatch.makeColor(0.561, 0.232, 0.114);
  //private final Color k_YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524, 0.113);
  public static boolean redDetected = false;
  public static boolean blueDetected = false;
  public static boolean greenDetected = false;
  public static boolean yellowDetected = false;
  public static double colorDetected = 0.0;

  //public static DigitalInput white;
  //public static DigitalInput blue;
  //public static DigitalInput yellow;
  //public static DigitalInput green;

  public static String startingState = "Center";

  //public static AnalogInput ultrasanicDivided;

  @Override
  public void robotInit() {
    shooter = new Shooter();
    Gavin = new DriveTrain();
    intake = new Intake();
    limelight = new Vision();
    limelight.lightOff();
    //ahrs = new AHRS();
    conveyor = new Conveyor();
    //m_colorSensor = new ColorSensorV3(i2cPort);
    //m_colorMatcher = new ColorMatch();
    colorWheel = new WheelOfFortune();
    climber = new Climb();
    distanceSensor = new Rev2mDistanceSensor(Port.kOnboard, Unit.kMillimeters, RangeProfile.kHighAccuracy);
    distanceSensor.setEnabled(true);
    distanceSensor.setAutomaticMode(true);
    compressor = new Compressor();
    //compressor.setClosedLoopControl(true);
    
    //ultrasanicDivided = new AnalogInput(PortMap.ANALOG_dividedUltrasanic);
    server = CameraServer.getInstance();
    server.startAutomaticCapture(0);
    
    oi = new OIHandler();
    //ahrs.enableLogging(true);

    startingState = "Center";

    positionChooser = new SendableChooser<String>();
    positionChooser.setDefaultOption("Left", "Left");
    positionChooser.addOption("Center", "Center");
    positionChooser.addOption("Right", "Right");
    SmartDashboard.putData("POSITION", positionChooser);

    autoChooser = new SendableChooser<Command>();
    autoChooser.setDefaultOption("Drive and Shoot", new AutonEverything());
    autoChooser.addOption("Do Nothing", new AutonDoNothing());
    autoChooser.addOption("Drive Off Line", new AutonDriveOffLine());
    SmartDashboard.putData("AUTO", autoChooser);

    /*m_colorMatcher.addColorMatch(k_BLUE_TARGET);
    m_colorMatcher.addColorMatch(k_GREEN_TARGET);
    m_colorMatcher.addColorMatch(k_RED_TARGET);
    m_colorMatcher.addColorMatch(k_YELLOW_TARGET);*/

    limelight.driverSight();
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Index Distance (mm): ", distanceSensor.getRange());
    SmartDashboard.putBoolean("Compressor Enabled: ", compressor.enabled());
    SmartDashboard.putNumber("Left Encoder: ", Gavin.getLeftDegrees());
    SmartDashboard.putNumber("Right Encoder: ", Gavin.getRightDegrees());
    SmartDashboard.putNumber("RightClimbEncoder: ", climber.getRightEncoder());
    SmartDashboard.putNumber("LeftClimbEncoder: ", climber.getLeftEncoder());
    /*Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == k_BLUE_TARGET) {
      colorString = "Blue";
      colorDetected = 0.25;
    } else if (match.color == k_RED_TARGET) {
      colorString = "Red";
      colorDetected = 0.5;
    } else if (match.color == k_GREEN_TARGET) {
      colorString = "Green";
      colorDetected = 0.75;
    } else if (match.color == k_YELLOW_TARGET) {
      colorString = "Yellow";
      colorDetected = 1.0;
    } else {
      colorString = "Unknown";
      colorDetected = 0.0;
    }

    SmartDashboard.putNumber("Red: ", detectedColor.red);
    SmartDashboard.putNumber("Green: ", detectedColor.green);
    SmartDashboard.putNumber("Blue: ", detectedColor.blue);
    SmartDashboard.putNumber("Confidence: ", match.confidence);
    SmartDashboard.putString("Detected Color: ", colorString);
    SmartDashboard.putNumber("ULTRASANIC :", ultrasanicDivided.getVoltage());*/
 }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    limelight.updateLimelight();
    compressor.setClosedLoopControl(false);
    //limelight.driverSight();
    // if (oi.getButtonStateJoystick(PortMap.JOYSTICK_testAutonStraight)) {
    //   Scheduler.getInstance().add(new AutonDriveStraight(50000));
    // }
    
    if (oi.getTrigger(PortMap.XBOX_leftTriggerAxis) > 0.5) {
      Scheduler.getInstance().add(new TeleopAim());
    }
    SmartDashboard.putNumber("Limelight In Range? ", limelight.getRange());
    
    if (oi.getButtonPressedXbox(PortMap.XBOX_climbHook)) {
      if (oi.getButtonStateXbox(PortMap.XBOX_generalSpecialFeature)) {
        Scheduler.getInstance().add(new ClimberHookDeployTimed());
      } else {
        Scheduler.getInstance().add(new ClimberHookDeploy());
      }
    }
    intake.putIntake();
    climber.putLock();
    shooter.putSpeed();
    
    if (oi.getTrigger(PortMap.XBOX_rightTriggerAxis) > 0.5){
      Scheduler.getInstance().add(new ShooterSpinUp());
      //Scheduler.getInstance().add(new ConveyorOnShoot());
    }

    // TODO: this could be our prototype limelight regressioning, so we should adjust our getRange() value and change our motor speed.
    if (oi.getTrigger(PortMap.XBOX_leftTriggerAxis) > 0.5) {
      if (!oi.getButtonStateXbox(PortMap.XBOX_shooterFree)) {
        if (limelight.getRange() > 1.6) { // CHANGE VALUES
          shooter.changePower(0.36); // CHANGE VALUES
        } else if (limelight.getRange() > 1.2) { // CHANGE VALUES
          shooter.changePower(0.38); // CHANGE VALUES
        } else if (limelight.getRange() > 1.0) {
          shooter.changePower(0.365);
        } else if (limelight.getRange() > 0.8) { // CHANGE VALUES
          shooter.changePower(0.37); // CHANGE VALUES
        } else if (limelight.getRange() > 0.5) {
          shooter.changePower(0.35);
        } else if (limelight.getRange() > 0.4) { // CHANGE VALUES
          shooter.changePower(0.375); // CHANGE VALUES
        } else if (limelight.getRange() > 0.3) { // CHANGE VALUES
          shooter.changePower(0.431); // CHANGE VALUES
        } else if (limelight.getRange() > 0.2) { // CHANGE VALUES
          shooter.changePower(0.53); // CHANGE VALUES
        } else if (limelight.getRange() > 0.1) { // CHANGE VALUES
          shooter.changePower(0.85); // CHANGE VALUES
        } else if (limelight.getRange() < 0.03) { 
          //shooter.changePower(0.0);
          //shooter.resetSpeed();
        } else {
        //shooter.changePower(0.0);+
        //shooter.resetSpeed();
        }
      }//} //else {
        //shooter.changePower(0.5);
      //}
    }

     if (oi.getPOVXbox() == 0) {
       if (speedFlag) {
         shooter.increasePower(0.01);
         speedFlag = false;
       }
     } else if (oi.getPOVXbox() == 180) {
       if (speedFlag) {
         shooter.increasePower(-0.01);
         speedFlag = false;
       }
     } else {
       speedFlag = true;
     }

    if (distanceSensor.getRange() > 0 && distanceSensor.getRange() < 200 && !conveyor.getCurrentCommandName().equalsIgnoreCase("Ultrasanic") && !(oi.getButtonStateXbox(PortMap.XBOX_conveyorForwards))) {
     Scheduler.getInstance().add(new ConveyorOnUltra());
    }

    /*if (oi.getButtonStateJoystick(PortMap.JOYSTICK_intake)) {
      Scheduler.getInstance().add(new TeleopIntakeAim());
    }*/

    if (oi.getButtonStateJoystick(PortMap.JOYSTICK_intake)) {
      if (!shooterTriggerHeld) {
        shooterTriggerHeld = true;
        timePressed = System.currentTimeMillis();
      } else if (System.currentTimeMillis() - timePressed > 2000) {
        counter = 0;
      }
    }
      /*if (ultrasanicDivided.getVoltage() < PortMap.k_ULTRA) {
        Scheduler.getInstance().add(new ConveyorOnUltra());
      }*
    } else if (ultrasanicDivided.getVoltage() < PortMap.k_ULTRA && !oi.getButtonStateXbox(PortMap.XBOX_conveyorBackwards)) {
      shooterTriggerHeld = false;
      Scheduler.getInstance().add(new ConveyorOnUltra());
      conveyorOn = true;
    } else if (shooterTriggerHeld) {
      shooterTriggerHeld = false;
      if (conveyorOn) {
        counter +=1;
        conveyorOn = false;
      }
      Scheduler.getInstance().add(new ConveyorOff());
    }
    SmartDashboard.putNumber("Counter: ", counter);*/
   

    /*if (oi.getXboxAxis(PortMap.XBOX_leftStickYAxis) < -0.5) {
      Scheduler.getInstance().add(new ClimberLift());
    }*/

    /*SmartDashboard.putNumber("White Encoder: ", white.readFallingTimestamp());
    SmartDashboard.putNumber("Blue Encoder: ", blue.readFallingTimestamp());
    SmartDashboard.putNumber("Yellow Encoder: ", yellow.readFallingTimestamp());
    SmartDashboard.putNumber("Green Encoder: ", green.readFallingTimestamp());
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
    startingState = positionChooser.getSelected();
    Scheduler.getInstance().add(autoChooser.getSelected());
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    limelight.updateLimelight();
    //if (timerFlag == false) {
    //  startTime = System.currentTimeMillis();
    //  timerFlag = true;
    //}
    //SmartDashboard.putNumber("Left Encoder", oi.getLeftDegrees());
    //SmartDashboard.putNumber("Right Encoder", oi.getRightDegrees());
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
    /*if (System.currentTimeMillis() - startTime < 1000) {
      Gavin.standardDrive(0, 0.4);
    } else if (System.currentTimeMillis() - startTime < 6000) {
      if (limelight.getTv()) {
        Gavin.autonAimbot(Robot.limelight.getTx(), Robot.limelight.getTy(), Robot.limelight.getTv(), Robot.limelight.getRange());
      } else {
        Gavin.standardDrive(0.4, 0);
      }
    } else if (System.currentTimeMillis() - startTime > 6000) {
      Gavin.standardDrive(0,0);
    }*/
  }
}
