package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.Commands.ColorWheelOn;
import frc.robot.Commands.TeleopAim;
import frc.robot.Commands.TeleopSpinUp;

public class OIHandler {
	
	Joystick joystick;
	JoystickButton flywheelButton;
	JoystickButton intakeButton;
	JoystickButton straightButton;
	JoystickButton aimBotButton;
	JoystickButton colorWheelButton;
	XboxController xboxcontroller;
	Encoder flywheelEncoder;
	Encoder leftDriveEncoder;
	Encoder rightDriveEncoder;

	public OIHandler() {
		joystick = new Joystick(PortMap.joystick);

		xboxcontroller = new XboxController(PortMap.xboxController);

		//flywheelButton = new JoystickButton(joystick, PortMap.JOYSTICK_flywheels);
		//flywheelButton.whenPressed(new TeleopSpinUp());

		intakeButton = new JoystickButton(joystick, PortMap.JOYSTICK_intake);

		//aimBotButton = new JoystickButton(joystick, PortMap.JOYSTICK_aimBot);
		//aimBotButton.whenPressed(new TeleopAim());
		
		colorWheelButton = new JoystickButton(joystick, PortMap.JOYSTICK_colorWheelActivate);
		colorWheelButton.whenPressed(new ColorWheelOn());
		
		flywheelEncoder = new Encoder(PortMap.DIO_flywheelEncoder1, PortMap.DIO_flywheelEncoder2);
		flywheelEncoder.reset();
		leftDriveEncoder = new Encoder(PortMap.DIO_leftDriveEncoder1, PortMap.DIO_leftDriveEncoder2);
		leftDriveEncoder.reset();
		rightDriveEncoder = new Encoder(PortMap.DIO_rightDriveEncoder1, PortMap.DIO_rightDriveEncoder2);
		rightDriveEncoder.reset();
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

	public boolean getButtonPressedXbox(int button) {
		return xboxcontroller.getRawButtonPressed(button);
	}

	public boolean getButtonStateXbox(int button) {
		return xboxcontroller.getRawButton(button);
	}

	public double getRPM() {
		double rate = flywheelEncoder.getRate(); //Units are distance per second as scaled by the value from setDistancePerPulse()
		rate = rate*(1.0/flywheelEncoder.getDistancePerPulse());//find pulses per revolution
		rate = rate*4.0; //counts per pulse
		rate = rate * 1.0/1024.0; //revolutions per count
		rate = rate * 60; //seconds per minute
		return rate;
	}

	public double getRightDegrees() {
		double position = rightDriveEncoder.get(); //in counts
		position = position/4.0;  //1 pulse per 4 counts
		position = position * rightDriveEncoder.getDistancePerPulse(); //revolutions per pulse
		position = position * 360; //360 degrees per revolution
		return position;
	}

	public double getLeftDegrees() {
		double position = leftDriveEncoder.get();
		position = position/4.0;
		position  = position * leftDriveEncoder.getDistancePerPulse();
		position = position * 360;
		return position;
	}
}