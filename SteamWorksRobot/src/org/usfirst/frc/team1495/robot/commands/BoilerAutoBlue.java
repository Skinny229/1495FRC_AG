package org.usfirst.frc.team1495.robot.commands;

import org.usfirst.frc.team1495.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BoilerAutoBlue extends Command {

    public BoilerAutoBlue() {
    	requires(Robot.shooterSub);
    	requires(Robot.loaderSub);
    	requires(Robot.gyro);	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.roboDrive.mecanumDrive_Cartesian(0, .2, 0, 0);
    	Timer.delay(3);
    	Robot.roboDrive.mecanumDrive_Cartesian(0, -.2, 0, 0);
    	Timer.delay(2);
    	while(Robot.gyro.getRawAngleDegrees() < -135) {
    		Robot.roboDrive.mecanumDrive_Cartesian(0, 0, -.2, 0);
    	}
    	Robot.loaderSub.spin(-.5);
    	Robot.shooterSub.spin(.71);
    	Timer.delay(7);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//ChaChaDance
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
