package org.usfirst.frc.team1495.robot.commands;

import org.usfirst.frc.team1495.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetShooterSpeed extends Command {

    public ResetShooterSpeed() {
    }

    boolean bool = false;
    protected void initialize() {
       bool = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//`adjustShooter.setDefSpeed();
    	bool = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return bool;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}