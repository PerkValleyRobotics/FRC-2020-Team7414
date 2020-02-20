package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasanic {
    
    AnalogInput input;

    public Ultrasanic(int channel) {
        input = new AnalogInput(channel);
    }

    /* Reads the distance of the nearest object from the sensor
     * using only one sample; less accurate than an average.
     * Note that the distance is measured in CENTIMETERS (cm) */
    public double read() {
        return input.getValue();
        /* For our sensor, a voltage step of 1/512 is equal to a
         * distance of 1 cm but our ADC records 4096 steps, not 512  */
    }
}