package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.*;

import frc.robot.PortMap;
import frc.robot.Commands.IntakeOff;
import frc.robot.StateTrackers.IntakePositionState;

public class Intake extends Subsystem {

    DoubleSolenoid clawDeploy;
    PWMVictorSPX intakeWheel;
    public IntakePositionState positionState;

    final double k_SPEED = 0.45;

    public Intake() {
        intakeWheel = new PWMVictorSPX(PortMap.PWM_intakeWheel);
        clawDeploy = new DoubleSolenoid(PortMap.PCM_intake1, PortMap.PCM_intake2);
        positionState = IntakePositionState.UP;
        clawDeploy.set(Value.kReverse);
    }

    public void putIntake() {
        SmartDashboard.putString("Intake Solenoid: ", positionState.toString());
    }

    public void intakeOff() {
        intakeWheel.set(0);
    }

    public void intakeOn() {
        intakeWheel.set(-k_SPEED);
    }

    public void intakeReverse() {
        intakeWheel.set(k_SPEED);
    }

    public void actuateClawPiston() {
        if (positionState.equals(IntakePositionState.UP)) {
            clawDeploy.set(Value.kForward);
            positionState = IntakePositionState.DOWN;
        } else if (positionState.equals(IntakePositionState.DOWN)) {
            clawDeploy.set(Value.kReverse);
            positionState = IntakePositionState.UP;
        }
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