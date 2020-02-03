package frc.robot;

enum StartingPosition {
    LEFT, CENTER, RIGHT
}

public class PortMap {
	
	//Controller
	public static int joystick = 0;
	
	//PWM
	public static int left1 = 3;
	public static int left2 = 2;
	public static int right1 = 4;
	public static int right2 = 1;
	public static int leftWheel = 8;
	public static int rightWheel = 7;
	public static int intakeWheel = 6;
	public static int conveyorWheel = 9;
	public static int colorWheel = 0;

	//Joystick Buttons
	public static int flywheels = 4;
	public static int AdjBut = 2;
	public static int intake = 1;
	public static int slowMode = 12;
	public static int backwards = 3;
	public static int aimBot = 6;
	public static int flipDirection = 5;
	public static int colorWheelActivate = 7;
	
	//DIO
	public static int flywheelEncoder1 = 0;
	public static int flywheelEncoder2 = 1;
	public static int leftDriveEncoder1 = 2;
	public static int leftDriveEncoder2 = 3;
	public static int rightDriveEncoder1 = 4;
	public static int rightDriveEncoder2 = 5;

	//Limelight Network Table Values
	public static int lightOff = 1;
	public static int lightOn = 3;
}