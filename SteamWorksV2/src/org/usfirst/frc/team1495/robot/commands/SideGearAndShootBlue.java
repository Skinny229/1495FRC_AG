package org.usfirst.frc.team1495.robot.commands;

import org.usfirst.frc.team1495.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SideGearAndShootBlue extends Command {

    public SideGearAndShootBlue() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gyro);
    	requires(Robot.shooterSub);
    	requires(Robot.loaderSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.roboDrive.mecanumDrive_Cartesian(0, .25, 0, 0);
		Timer.delay(3.95);
		Robot.roboDrive.mecanumDrive_Cartesian(0, 0, -.3, 0);
		Timer.delay(.65);
		Robot.roboDrive.stopMotor();
		Robot.roboDrive.mecanumDrive_Cartesian(0, .2, 0, 0);
		Timer.delay(4);
		Robot.roboDrive.stopMotor();
		Robot.roboDrive.mecanumDrive_Cartesian(0, -.3, 0, 0);
		Timer.delay(1.5);
		Robot.shooterSub.stop();
		Robot.loaderSub.stop();
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
