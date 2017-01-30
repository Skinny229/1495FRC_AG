package org.usfirst.frc.team1495.robot.subsystems;

import org.usfirst.frc.team1495.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**Ultrasonic Sensor
 *Ping channel is the output channel to the sensor
 *Echo channel is the input channel from the sensor
 */
public class UltrasonicSensor extends Subsystem {

	final float toMM = 5120;
	protected AnalogInput ai;
	
	public UltrasonicSensor () {
		ai = new AnalogInput(RobotMap.ULTRASONIC_CHANNEL);
	}
	
	public double getDistanceInches()
	{
		return ((ai.getVoltage() / toMM) * 25.4);
	}   

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

