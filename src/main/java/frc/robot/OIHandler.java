package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Encoder;

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
	Encoder flywheelEncoder;
	Encoder leftDriveEncoder;
	Encoder rightDriveEncoder;

	public OIHandler() {
		joystick = new Joystick(PortMap.joystick);

		flywheelButton = new JoystickButton(joystick, PortMap.flywheels);
		flywheelButton.whenPressed(new TeleopSpinUp());

		intakeButton = new JoystickButton(joystick, PortMap.intake);

		aimBotButton = new JoystickButton(joystick, PortMap.aimBot);
		aimBotButton.whenPressed(new TeleopAim());
		
		colorWheelButton = new JoystickButton(joystick, PortMap.colorWheelActivate);
		colorWheelButton.whenPressed(new ColorWheelOn());
		
		flywheelEncoder = new Encoder(PortMap.flywheelEncoder1, PortMap.flywheelEncoder2);
		flywheelEncoder.reset();
		leftDriveEncoder = new Encoder(PortMap.leftDriveEncoder1, PortMap.leftDriveEncoder2);
		leftDriveEncoder.reset();
		rightDriveEncoder = new Encoder(PortMap.rightDriveEncoder1, PortMap.rightDriveEncoder2);
		rightDriveEncoder.reset();
	}

	public double getJoystickX() {
		return joystick.getX();
	}

	public double getJoystickY() {
		return -1 * joystick.getY();
	}

	public boolean getButtonState(int button) {
		return joystick.getRawButton(button);
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