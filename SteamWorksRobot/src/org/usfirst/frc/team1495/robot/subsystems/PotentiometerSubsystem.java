package org.usfirst.frc.team1495.robot.subsystems;

import org.usfirst.frc.team1495.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class PotentiometerSubsystem extends Subsystem {

	Potentiometer pot;
	AnalogInput aInput;

	public PotentiometerSubsystem() {
		aInput = new AnalogInput(RobotMap.POTENTIOMETER_PORT);
		pot = new AnalogPotentiometer(aInput, 360, 1);
	}

	public double getAngle() {
		return pot.get();
	}

	public void initDefaultCommand() {
		// setDefaultCommnand(m_currentCommand);
	}

}