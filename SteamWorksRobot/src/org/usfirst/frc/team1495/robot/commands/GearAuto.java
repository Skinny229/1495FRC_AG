package org.usfirst.frc.team1495.robot.commands;

import org.usfirst.frc.team1495.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearAuto extends Command {

    public GearAuto() {
        requires(Robot.ultra);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.roboDrive.setSafetyEnabled(false);
    	Robot.roboDrive.mecanumDrive_Cartesian(0, -.2, 0, 0);
    	Timer.delay(2.5);
    	Robot.roboDrive.stopMotor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.roboDrive.setSafetyEnabled(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.roboDrive.setSafetyEnabled(true);
    }
}