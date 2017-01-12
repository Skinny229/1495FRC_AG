package org.usfirst.frc.team1495.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**Ultrasonic Sensor
 *Ping channel is the output channel to the sensor
 *Echo channel is the input channel from the sensor
 */
public class UltrasonicSensor extends Subsystem {

    protected Ultrasonic uss;
	
	public UltrasonicSensor(int ping, int echo, Ultrasonic.Unit unit) {
		uss= new Ultrasonic(ping, echo, unit);
	}
	
	public UltrasonicSensor(DigitalOutput ping, DigitalInput echo, Ultrasonic.Unit unit) {
		uss= new Ultrasonic(ping, echo, unit);
	}

    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setAutoMode(boolean e) {
    	uss.setAutomaticMode(e);
    }
    
    public double pidGetRange() {
    	return uss.pidGet();
    }
    
    public boolean isRangeValid() {
    	return uss.isRangeValid();
    }
    
    public void destroy() {
    	uss.free();
    }  
    

}

