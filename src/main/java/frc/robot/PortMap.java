package frc.robot;

public abstract class PortMap {
	
	//Controller
	public static int CONTROLLER_joystick = 0;
	public static int CONTROLLER_xboxController = 1;
	
	//PWM
	public static int PWM_left1 = 1;
	public static int PWM_left2 = 0;
	public static int PWM_right1 = 9;
	public static int PWM_right2 = 7;
	//public static int PWM_leftWheel = 15; //unused
	//public static int PWM_rightWheel = 14; //unused
	public static int PWM_colorWheel = 11;
	public static int PWM_intakeWheel = 4;
	public static int PWM_conveyorTop = 5;
	public static int PWM_conveyorBottom = 8;
	public static int PWM_climberHook = 3;
	//public static int PWM_climberLeft = 0; //unused
	//public static int PWM_climberRight = 2; //unused

	//Joystick Buttons
	public static int JOYSTICK_straightForwards = 8;
	public static int JOYSTICK_straightBackwards = 3;
	public static int JOYSTICK_intake = 1;
	public static int JOYSTICK_intakeReverse = 2;
	public static int JOYSTICK_slowMode = 7;
	public static int JOYSTICK_turnLeft = 9;
	public static int JOYSTICK_turnRight = 10;
	public static int JOYSTICK_intakePiston = 5;
	public static int JOYSTICK_testAutonTurn = 11;
	public static int JOYSTICK_testAutonStraight = 12;
	//public static int JOYSTICK_aimBot = 6;
	//public static int JOYSTICK_flipDirection = 5;
	//public static int JOYSTICK_colorWheelActivate = 7;
	//public static int JOYSTICK_shoot = 10;
	//public static int JOYSTICK_conveyorForwards = 9;
	//public static int JOYSTICK_flywheels = 4;
	

	//Xbox Controller Buttons
	public static int XBOX_leftTriggerAxis = 2;
	public static int XBOX_rightTriggerAxis = 3;
	public static int XBOX_leftStickYAxis = 1;
	public static int XBOX_colorWheelPiston = 11; //unused
	public static int XBOX_colorWheelColor = 13; //unused
	public static int XBOX_colorWheelSpin = 12; //unused
	public static int XBOX_conveyorForwards = 6;
	public static int XBOX_conveyorBackwards = 14; //unused
	public static int XBOX_climbLeft = 9;
	public static int XBOX_climbRight = 10;
	public static int XBOX_climbPiston = 2;
	public static int XBOX_climbDown = 15; //unused
	public static int XBOX_shooterFree = 3;
	public static int XBOX_climbHook = 4;
	public static int XBOX_climbBoth = 1;
	public static int XBOX_reverseDirection = 7;
	public static int XBOX_hookLock = 5;
	//public static int XBOX_POV_climbBoth = 9;
	//public static int XBOX_POV_conveyorForwards = 90;
	//public static int XBOX_POV_conveyorBackwards = 270;

	//DIO
	public static int DIO_flywheelEncoder1 = 9;
	public static int DIO_flywheelEncoder2 = 8;
	public static int DIO_leftDriveEncoder1 = 5;
	public static int DIO_leftDriveEncoder2 = 6;
	public static int DIO_rightDriveEncoder1 = 1;
	public static int DIO_rightDriveEncoder2 = 2;

	//Limelight Network Table Values
	public static int LIMELIGHT_lightOff = 1;
	public static int LIMELIGHT_lightOn = 3;
	public static int LIMELIGHT_defaultPipeline = 0;
	public static int LIMELIGHT_targetingPipeline = 1;

	//Analog
	public static int ANALOG_ultrasonic = 3;
	public static int ANALOG_dividedUltrasanic = 0;

	//CAN
	public static int CAN_pcm = 0;
	public static int CAN_shooterLeft = 1;
	public static int CAN_shooterRight = 2;
	public static int CAN_climbLeft = 5;
	public static int CAN_climbRight = 4;

	//PCM
	public static int PCM_thruster2 = 0; // unused
	public static int PCM_thruster1 = 1; // unused
	public static int PCM_intake1 = 4;
	public static int PCM_intake2 = 5;
	public static int PCM_climbLock1 = 6;
	public static int PCM_climbLock2 = 7;

	//constants
	public static final double k_ULTRA = 0.25;
	public static final double k_CONVEYORTIME = 0.0;
}