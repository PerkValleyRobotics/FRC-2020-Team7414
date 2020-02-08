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
        setInterruptible(true);
        flag = false;
        flagTime = 0;
        finished = false;
    }
    
    public boolean isFinished() {
        return finished;
    }
    
    public void execute() {
        Robot.conveyor.ConveyorOn();
        if (Robot.ultrasanicSensor.read() > PortMap.k_ULTRA && !flag) {
            flag = true;
            flagTime = System.currentTimeMillis();
        }
        if (Robot.ultrasanicSensor.read() > PortMap.k_ULTRA && (flagTime + 2000) < System.currentTimeMillis()) {
            finished = true;
        }
    }

    public void interrupted() {

    }

    public void end() {
        Robot.conveyor.ConveyorOff();
    }
}