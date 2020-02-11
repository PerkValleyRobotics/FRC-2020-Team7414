package frc.robot;

enum StartingPosition {
    LEFT, CENTER, RIGHT
}

public class PortMap {
	
	//Controller
	public static int joystick = 0;
	public static int xboxController = 1;
	public static int XBOX_leftTriggerAxis = 2;
	public static int XBOX_rightTriggerAxis = 3;
	
	//PWM
	public static int PWM_left1 = 0;
	public static int PWM_left2 = 2;
	public static int PWM_right1 = 1;
	public static int PWM_right2 = 3;
	public static int PWM_leftWheel = 6;
	public static int PWM_rightWheel = 7;
	public static int PWM_intakeWheel = 8;
	public static int PWM_conveyorWheel = 9;
	public static int PWM_colorWheel = 4;

	//Joystick Buttons
	public static int JOYSTICK_flywheels = 1;
	public static int JOYSTICK_AdjBut = 2;
	public static int JOYSTICK_intake = 4;
	public static int JOYSTICK_slowMode = 12;
	public static int JOYSTICK_backwards = 3;
	public static int JOYSTICK_aimBot = 6;
	public static int JOYSTICK_flipDirection = 5;
	public static int JOYSTICK_colorWheelActivate = 7;
	
	//DIO
	public static int DIO_flywheelEncoder1 = 0;
	public static int DIO_flywheelEncoder2 = 1;
	public static int DIO_leftDriveEncoder1 = 2;
	public static int DIO_leftDriveEncoder2 = 3;
	public static int DIO_rightDriveEncoder1 = 4;
	public static int DIO_rightDriveEncoder2 = 5;

	//Limelight Network Table Values
	public static int LIMELIGHT_lightOff = 1;
	public static int LIMELIGHT_lightOn = 3;
	public static int LIMELIGHT_defaultPipeline = 0;
	public static int LIMELIGHT_targetingPipeline = 1;

	//Analog
	public static int ANALOG_ultrasonic = 3;

	//constants
	public static final double k_ULTRA = 2;
}