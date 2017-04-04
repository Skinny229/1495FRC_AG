package org.usfirst.frc.team1495.robot.commands;

import org.usfirst.frc.team1495.robot.Robot;
import org.usfirst.frc.team1495.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class VeryExperimentalVisionAuto extends Command {

	public VeryExperimentalVisionAuto() {
		requires(Robot.gyro);
		requires(Robot.gearUltra);
		requires(Robot.hopperUltra);
		requires(Robot.loaderSub);
		requires(Robot.shooterSub);
	}

	int onPhase;
	double turnSpeedPhase1 = .25, turnSpeedPhase3 = .3;
	boolean hasFinished;

	// Vision into peg variables
	NetworkTable visionTable;
	double midxPointWanted, midXPointArray[], midXPointDataLostDef[] = { 0.0, 0.0, 0.0 }, midXPointActual, angleToTurn,
			angleToTurnSpeed = .25;
	int targetDataLostCounter;
	double distToTarget;

	DriverStation.Alliance onAlliance;
	DriverStation.Alliance blue = DriverStation.Alliance.Blue;
	DriverStation.Alliance red = DriverStation.Alliance.Red;

	protected void initialize() {
		Robot.gyro.reset();
		onPhase = 0;
		onAlliance = DriverStation.getInstance().getAlliance();
		hasFinished = false;
		/*
		 * If on the blue alliance, all values are switched for blue alliance If
		 * the DriverStation does not send the alliance, it will auto run on RED
		 * SIDE
		 */
		if (onAlliance == blue) {
			turnSpeedPhase1 *= -1;
			turnSpeedPhase3 *= -1;
		}
		targetDataLostCounter = 0;
		// Get Vision Tables
		visionTable = NetworkTable.getTable("GRIP/gearContourReport");
	}

	/*
	 * Phase 0: Drive Foward Until Distance is met Phase 1: Turn 45 Degrees
	 * Phase 2: Drive into the peg and wait Phase 3: Drive out and aline the
	 * shooter Phase 4: Shoot for the remainder of the Auto period
	 */
	protected void execute() {
		switch (onPhase) {
		case 0:
			while (Robot.hopperUltra.getDistanceMMINT() < 2895.5) {
				Robot.roboDrive.mecanumDrive_Cartesian(0, .3, 0, Robot.gyro.getAngleDegrees());
			}
			onPhase++;
			break;
		case 1:
			while (Math.abs(Robot.gyro.getRawAngleDegrees()) < 60) {
				Robot.roboDrive.mecanumDrive_Cartesian(0, 0, turnSpeedPhase1, 0);
			}
			onPhase++;
			break;
		case 2:
			midXPointArray = visionTable.getNumberArray("centerX", midXPointDataLostDef);
			Robot.gyro.reset();
			if (midXPointArray.length == 2) {
				midXPointActual = (midXPointArray[0] + midXPointArray[1]) / 2;
				angleToTurn = Math.toDegrees(Math.atan((240 - midXPointActual) / Math.toDegrees(240 / Math.tan(30))));
				System.out.println("Turning by: " + angleToTurn + " degrees!");
				if (Math.signum(angleToTurn) == 1.0)
					angleToTurnSpeed *= -1;
				while (Math.abs(Robot.gyro.getRawAngleDegrees()) < angleToTurn)
					Robot.roboDrive.mecanumDrive_Cartesian(0, 0, angleToTurnSpeed, 0);
				while (!Robot.gearSwitch.isGearLifted())
					Robot.roboDrive.mecanumDrive_Cartesian(0, .3, 0, 0);
				onPhase++;
			} else {
				System.out
						.println("Target data not found(either too little or too much targets)! Driving Slow for now");
				Robot.roboDrive.mecanumDrive_Cartesian(0, .25, 0, 0);
				Timer.delay(.2);
				targetDataLostCounter++;
				if (targetDataLostCounter == 16 || Robot.gearSwitch.isGearLifted())
					onPhase++;
			}
			break;
		case 3:
			Timer.delay(1.5);
			while (Robot.gearUltra.getDistanceMMINT() < 750) {
				Robot.roboDrive.mecanumDrive_Cartesian(0, -.3, 0, 0);
			}
			Timer.delay(1);
			while (Math.abs(Robot.gyro.getRawAngleDegrees()) < 75) {
				Robot.roboDrive.mecanumDrive_Cartesian(0, 0, turnSpeedPhase3, 0);
			}
			Robot.roboDrive.stopMotor();
			onPhase++;
			break;
		case 4:
			Robot.shooterSub.spin(RobotMap.shootingSpeed);
			Timer.delay(.4);
			Robot.loaderSub.spin(RobotMap.LOAD_SPEED);
			double sleepTillHere = 15.0 - DriverStation.getInstance().getMatchTime();
			Timer.delay(sleepTillHere);
			hasFinished = true;
			onPhase++;
			break;
		default:
			System.out.println("WARNING: PHASE SELECTION FOR SIDE GEAR AND SHOOT FAILED! Stopping Autonomous...");
			hasFinished = true;
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.roboDrive.stopMotor();
		Robot.shooterSub.stop();
		Robot.loaderSub.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.roboDrive.stopMotor();
		Robot.shooterSub.stop();
		Robot.loaderSub.stop();
	}
}
