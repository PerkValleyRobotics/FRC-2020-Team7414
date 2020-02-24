package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PortMap;
import frc.robot.Robot;

public class ConveyorOnUltra extends Command {

    long flagTime;
    boolean flag;
    boolean finished;

    public ConveyorOnUltra() {
        requires(Robot.conveyor);
        setInterruptible(false);
        flag = false;
        flagTime = 0;
        finished = false;
    }
    
    public boolean isFinished() {
        return finished;
    }
    
    public void execute() {
        Robot.conveyor.conveyorForwards();
        /*if (Robot.ultrasanicDivided.getVoltage() > PortMap.k_ULTRA && !flag) {
            flag = true;
            flagTime = System.currentTimeMillis();
        }
        if (Robot.ultrasanicDivided.getVoltage() > PortMap.k_ULTRA && (flagTime + PortMap.k_CONVEYORTIME) < System.currentTimeMillis()) {
            finished = true;
        }*/
    }

    public void interrupted() {

    }

    public void end() {
        Robot.conveyor.conveyorOff();
    }
}