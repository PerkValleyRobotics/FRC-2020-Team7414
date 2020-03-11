package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ConveyorOnUltra extends Command {

    long flagTime;
    boolean flag;
    boolean finished;

    public ConveyorOnUltra() {
        requires(Robot.conveyor);
        requires(Robot.intake);
        setInterruptible(false);
        setName("Ultrasanic");
        flag = false;
        flagTime = 0;
        finished = false;
    }
    
    public void execute() {
        Robot.conveyor.conveyorForwards();
        Robot.intake.intakeOn();
        /*if (Robot.ultrasanicDivided.getVoltage() > PortMap.k_ULTRA && !flag) {
            flag = true;
            flagTime = System.currentTimeMillis();
        }
        if (Robot.ultrasanicDivided.getVoltage() > PortMap.k_ULTRA && (flagTime + PortMap.k_CONVEYORTIME) < System.currentTimeMillis()) {
            finished = true;
        }*/
    }

    public boolean isFinished() {
        return Robot.distanceSensor.getRange() > 200; //original was 300
    }

    public void interrupted() {

    }

    public void end() {
        Robot.conveyor.conveyorOff();
        Robot.intake.intakeOff();
    }
}