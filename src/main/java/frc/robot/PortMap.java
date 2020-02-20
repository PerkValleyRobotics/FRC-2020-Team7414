package frc.robot;

public abstract class PortMap {
	
	//Controller
	public static int CONTROLLER_joystick = 0;
	public static int CONTROLLER_xboxController = 1;
	
	//PWM
	public static int PWM_left1 = 8;
	public static int PWM_left2 = 0;
	public static int PWM_right1 = 7;
	public static int PWM_right2 = 9;
	public static int PWM_leftWheel = 3;
	public static int PWM_rightWheel = 2;
	public static int PWM_colorWheel = 11;
	public static int PWM_intakeWheel = 1;
	public static int PWM_conveyorTop = 5;
	public static int PWM_conveyorBottom = 4;

	//Joystick Buttons
	public static int JOYSTICK_flywheels = 4;
	public static int JOYSTICK_AdjBut = 8;
	public static int JOYSTICK_intake = 1;
	public static int JOYSTICK_slowMode = 12;
	public static int JOYSTICK_backwards = 3;
	public static int JOYSTICK_aimBot = 6;
	public static int JOYSTICK_flipDirection = 5;
	public static int JOYSTICK_colorWheelActivate = 7;
	public static int JOYSTICK_intakeReverse = 2;
	public static int JOYSTICK_shoot = 10;
	public static int JOYSTICK_conveyorForwards = 9;

	//Xbox Controller Buttons
	public static int XBOX_leftTriggerAxis = 2;
	public static int XBOX_rightTriggerAxis = 3;
	public static int XBOX_colorWheelPiston = 1;
	public static int XBOX_colorWheelColor = 2;
	public static int XBOX_colorWheelSpin = 7;
	public static int XBOX_conveyorForwards = 6;
	public static int XBOX_conveyorBackwards = 5;
	public static int XBOX_shoot = 4;
	
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
	public static int ANALOG_dividedUltrasanic = 0;

	//CAN
	public static int CAN_compressor = 0;

	//PCM
	public static int PCM_thruster1 = 0;
	public static int PCM_thruster2 = 1;

	//constants
	public static final double k_ULTRA = .25;
	public static final double k_CONVEYORTIME = 0;
}