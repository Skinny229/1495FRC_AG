package org.usfirst.frc.team1495.robot.subsystems;

import org.usfirst.frc.team1495.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	VictorSP shooter = new VictorSP(RobotMap.SHOOTER_SC_PORT);

	public ShooterSubsystem() {
		shooter.setSafetyEnabled(false);
	}

	public void setSaftey(boolean update) {
		shooter.setSafetyEnabled(update);
	}

	public void shoot(double speed) {
		shooter.set(speed);
	}

	public void stopShooting() {
		shooter.stopMotor();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}