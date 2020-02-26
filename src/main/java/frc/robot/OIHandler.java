package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Commands.*;
import frc.robot.Commands.Autonomous.*;
import frc.robot.Subsystems.Shooter;

public class OIHandler {
	
	Joystick joystick;
	XboxController xboxcontroller;

	JoystickButton intakeButton;
	JoystickButton reverseIntakeButton;
	JoystickButton straightButton;
	JoystickButton turnLeftButton;
	JoystickButton turnRightButton;
	JoystickButton intakePistonButton;
	JoystickButton climbPistonButton;
	JoystickButton climbHookButton;
	JoystickButton climbBothButton;
	JoystickButton testAutonTurn;
	JoystickButton testAutonStraight;
	JoystickButton climbDown;
	JoystickButton testShooterButton;
	Button climbLeftButton;
	Button climbRightButton;
	Button pistonButton;
	Button rotationControlButton;
	Button positionControlButton;
	Button conveyorForwardsButton;
	Button conveyorBackwardsButton;
	Button hookDeployButton;
	Encoder flywheelEncoder;

	Encoder leftDriveEncoder;
	Encoder rightDriveEncoder;
	
	double rightAxis;

	public OIHandler() {
		joystick = new Joystick(PortMap.CONTROLLER_joystick);
		xboxcontroller = new XboxController(PortMap.CONTROLLER_xboxController);

		//drivetrain
		turnLeftButton = new JoystickButton(joystick, PortMap.JOYSTICK_turnLeft);
		turnLeftButton.whenPressed(new TeleopTurnLeft());
		turnRightButton = new JoystickButton(joystick, PortMap.JOYSTICK_turnRight);
		turnRightButton.whenPressed(new TeleopTurnRight());

		//intake
		intakeButton = new JoystickButton(joystick, PortMap.JOYSTICK_intake);
		intakeButton.whenPressed(new IntakeOn());
		reverseIntakeButton = new JoystickButton(joystick, PortMap.JOYSTICK_intakeReverse);
		reverseIntakeButton.whenPressed(new IntakeReverse());
		intakePistonButton = new JoystickButton(joystick, PortMap.JOYSTICK_intakePiston);
		intakePistonButton.whenPressed(new IntakePistonToggle());

		//conveyor
		conveyorForwardsButton = new JoystickButton(xboxcontroller, PortMap.XBOX_conveyorForwards);
		conveyorForwardsButton.whenPressed(new ConveyorOn());
		//conveyorBackwardsButton = new JoystickButton(xboxcontroller, PortMap.XBOX_conveyorBackwards);
		//conveyorBackwardsButton.whenPressed(new ConveyorBackwards());

		//climb
		climbLeftButton = new JoystickButton(xboxcontroller, PortMap.XBOX_climbLeft);
		climbLeftButton.whenPressed(new ClimberLiftOneSide());
		climbRightButton = new JoystickButton(xboxcontroller, PortMap.XBOX_climbRight);
		climbRightButton.whenPressed(new ClimberLiftOneSide());
		climbPistonButton = new JoystickButton(xboxcontroller, PortMap.XBOX_climbPiston);
		climbPistonButton.whenPressed(new ClimbLockToggle());
		climbHookButton = new JoystickButton(xboxcontroller, PortMap.XBOX_climbHook);
		climbHookButton.whenPressed(new ClimberHookDeploy());
		climbBothButton = new JoystickButton(xboxcontroller, PortMap.XBOX_climbBoth);
		climbBothButton.whenPressed(new ClimberLift());
		//climbDown = new JoystickButton(xboxcontroller, PortMap.XBOX_climbDown);
		//climbDown.whenPressed(new ClimberDown());
		
		
		//colorwheel
		//pistonButton = new JoystickButton(xboxcontroller, PortMap.XBOX_colorWheelPiston);
		//pistonButton.whenPressed(new ColorWheelLiftToggle());
		//rotationControlButton = new JoystickButton(xboxcontroller, PortMap.XBOX_colorWheelSpin);
		//rotationControlButton.whenPressed(new ColorWheelOn());
		//positionControlButton = new JoystickButton(xboxcontroller, PortMap.XBOX_colorWheelColor);
		//TODO: position control command

		//flywheelEncoder = new Encoder(PortMap.DIO_flywheelEncoder1, PortMap.DIO_flywheelEncoder2);
		//flywheelEncoder.reset();
		leftDriveEncoder = new Encoder(PortMap.DIO_leftDriveEncoder1, PortMap.DIO_leftDriveEncoder2);
		leftDriveEncoder.reset();
		rightDriveEncoder = new Encoder(PortMap.DIO_rightDriveEncoder1, PortMap.DIO_rightDriveEncoder2);
		rightDriveEncoder.reset();

		//auton test
		//testAutonTurn = new JoystickButton(joystick, PortMap.JOYSTICK_testAutonTurn);
		//testAutonTurn.whenPressed(new AutonTurn(100));
		//testAutonStraight = new JoystickButton(joystick, PortMap.JOYSTICK_testAutonStraight);
		//testAutonStraight.whenPressed(new AutonDriveStraight(100));

		testShooterButton = new JoystickButton(xboxcontroller, 5);
		testShooterButton.whenPressed(new ShooterConveyor());
	}

	public double getJoystickX() {
		return joystick.getX();
	}

	public double getJoystickY() {
		return -1 * joystick.getY();
	}

	public boolean getButtonStateJoystick(int button) {
		return joystick.getRawButton(button);
	}

	public double getTrigger(int trigger) {
		return xboxcontroller.getRawAxis(trigger);
	}

	public double getXboxAxis(int axis) {
		return xboxcontroller.getRawAxis(axis);
	}

	public boolean getButtonPressedXbox(int button) {
		return xboxcontroller.getRawButtonPressed(button);
	}

	public boolean getButtonStateXbox(int button) {
		return xboxcontroller.getRawButton(button);
	}

	public int getPOVXbox() {
		return xboxcontroller.getPOV();
	}

	public boolean getReverse() {
		return xboxcontroller.getRawButton(PortMap.XBOX_reverseDirection);
	}

	public double getRPM() {
		double rate = flywheelEncoder.getRate(); //Units are distance per second as scaled by the value from setDistancePerPulse()
		rate = rate * (1.0 / flywheelEncoder.getDistancePerPulse());//find pulses per revolution
		rate = rate * 4.0; //counts per pulse
		rate = rate * 1.0/1024.0; //revolutions per count
		rate = rate * 60; //seconds per minute
		return rate;
	}

	public double getRightDegrees() {
		double position = rightDriveEncoder.get(); //in counts
		position = position / 4.0;  //1 pulse per 4 counts
		position = position * rightDriveEncoder.getDistancePerPulse(); //revolutions per pulse
		position = position * 360; //360 degrees per revolution
		return position;
	}

	public double getLeftDegrees() {
		double position = leftDriveEncoder.get();
		position = position / 4.0;
		position  = position * leftDriveEncoder.getDistancePerPulse();
		position = position * 360;
		return position;
	}
}