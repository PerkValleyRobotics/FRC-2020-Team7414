package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Commands.ColorWheelOn;
import frc.robot.Commands.TeleopAim;

import frc.robot.Commands.TeleopSpinUp;
//import frc.robot.Commands.IntakeOn;

public class OIHandler{
	
	Joystick joystick;
	JoystickButton flywheelButton;
	Encoder enc;
	JoystickButton intakeButton;
	JoystickButton straightButton;
	JoystickButton aimBotButton;
	JoystickButton colorWheelButton;

	public OIHandler() {
		joystick = new Joystick(PortMap.joystick);
		flywheelButton = new JoystickButton(joystick, PortMap.flywheels);
		intakeButton = new JoystickButton(joystick, PortMap.intake);
		flywheelButton.whenPressed(new TeleopSpinUp());
		//intakeButton.whenPressed(new IntakeOn());
		aimBotButton = new JoystickButton(joystick, PortMap.aimBot);
		colorWheelButton = new JoystickButton(joystick, PortMap.colorWheelActivate);
		aimBotButton.whenPressed(new TeleopAim());
		colorWheelButton.whenPressed(new ColorWheelOn());
		
		enc = new Encoder(PortMap.flywheelEncoder1, PortMap.flywheelEncoder2);
	}

	public double getJoystickX(){
		return joystick.getX();
	}

	public double getJoystickY(){
		return -1 * joystick.getY();
	}

	public boolean getButtonState(int button) {
		return joystick.getRawButton(button);
	}

	public double getRPM() {
		double rate = enc.getRate(); //Units are distance per second as scaled by the value from setDistancePerPulse()
		rate = rate*(1.0/enc.getDistancePerPulse());//find pulses per revolution
		rate = rate*4.0; //counts per pulse
		rate = rate * 1.0/1024.0; //revolutions per count
		rate = rate * 60; //seconds per minute
		return rate;
	}
}