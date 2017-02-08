package org.usfirst.frc.team1495.robot.subsystems;

import org.usfirst.frc.team1495.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**Ultrasonic Sensor
 *Ping channel is the output channel to the sensor
 *Echo channel is the input channel from the sensor
 */
public class UltrasonicSensor extends Subsystem {

	final float tomm = 5120;
	final float zero = .28f;
	protected AnalogInput ai;
	
	public UltrasonicSensor () {
		ai = new AnalogInput(RobotMap.ULTRASONIC_CHANNEL);
		ai.setOversampleBits(4);
		ai.setAverageBits(2);
	}
	
	public double getDistanceInches()
	{
		return ((int)((ai.getAverageVoltage() - zero)*1000))/1000;
	}   

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

