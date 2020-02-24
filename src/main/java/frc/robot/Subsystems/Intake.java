package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.PortMap;
import frc.robot.Commands.IntakeOff;
import frc.robot.StateTrackers.IntakePositionState;

public class Intake extends Subsystem {

    DoubleSolenoid clawDeploy;
    PWMVictorSPX intakeWheel;
    final double SPEED = -0.25;
    public IntakePositionState positionState;

    public Intake() {
        intakeWheel = new PWMVictorSPX(PortMap.PWM_intakeWheel);
        clawDeploy = new DoubleSolenoid(PortMap.PCM_intake1, PortMap.PCM_intake2);
        positionState = IntakePositionState.DOWN;
        clawDeploy.set(Value.kForward);
    }

    public void intakeOff() {
        intakeWheel.set(0);
    }

    public void intakeOn() {
        intakeWheel.set(-SPEED); //VARY THIS VALUE FOR INTAKE SPEED
    }

    public void intakeReverse() {
        intakeWheel.set(SPEED);
    }

    public void deployClaw() {
        clawDeploy.set(Value.kForward);
        positionState = IntakePositionState.DOWN;
    }

    public void retractClaw() {
        clawDeploy.set(Value.kReverse);
        positionState = IntakePositionState.UP;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeOff());
    }
}