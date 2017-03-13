
package org.usfirst.frc.team1495.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1495.robot.commands.BoilerAutoBlue;
import org.usfirst.frc.team1495.robot.commands.BoilerAutoRed;
import org.usfirst.frc.team1495.robot.commands.ChaChaDance;
import org.usfirst.frc.team1495.robot.commands.DoNothing;
import org.usfirst.frc.team1495.robot.commands.GearAuto;
import org.usfirst.frc.team1495.robot.commands.TouchlineAuto;
import org.usfirst.frc.team1495.robot.subsystems.ADXRS450Gyro;
import org.usfirst.frc.team1495.robot.subsystems.ServoMotor;
import org.usfirst.frc.team1495.robot.subsystems.TalonSingleMotor;
import org.usfirst.frc.team1495.robot.subsystems._Ultrasonic;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// Initiating Subsystems
	public static final _Ultrasonic ultra = new _Ultrasonic(RobotMap.ULTRASONIC_PORT);
	public static final ADXRS450Gyro gyro = new ADXRS450Gyro();
	public static final TalonSingleMotor shooterSub = new TalonSingleMotor(RobotMap.SHOOTER_SC_PORT);
	public static final TalonSingleMotor climberSub = new TalonSingleMotor(RobotMap.CLIMBER_SC_PORT);
	public static final TalonSingleMotor loaderSub = new TalonSingleMotor(RobotMap.LOADER_SC_PORT);
	
	// Declaring OI containing buttons with command conditions

	public OI oi;

	// Initiating RobotDrive with VictorSp's as the motors
	public static RobotDrive roboDrive = new RobotDrive(
			new VictorSP(RobotMap.LEFT_FRONT), // 0
			new VictorSP(RobotMap.LEFT_BACK), // 1
			new VictorSP(RobotMap.RIGHT_FRONT), // 2
			new VictorSP(RobotMap.RIGHT_BACK)); // 3

	// Initating the Joystick
	public Joystick stick = new Joystick(RobotMap.JOYSTICK_PORT_DRIVER);
	public Joystick operatorStick = new Joystick(RobotMap.CONTROLLER_PORT_OPERATOR);

	// Creating Choosers
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<Boolean> roboDriveEnabled = new SendableChooser<>();

	// Misc
	public boolean isRoboDriveEnabled;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// init Button Stuff
		oi = new OI();
		roboDrive.setExpiration(.5);
		roboDrive.setSensitivity(.1);
		// Setting inverted Motors
		roboDrive.setInvertedMotor(MotorType.kFrontLeft, RobotMap.isLeftSideInverted);
		roboDrive.setInvertedMotor(MotorType.kRearLeft, RobotMap.isLeftSideInverted);
		roboDrive.setInvertedMotor(MotorType.kFrontRight, RobotMap.isRightSideInverted);
		roboDrive.setInvertedMotor(MotorType.kRearRight, RobotMap.isRightSideInverted);
		// Enabling Safety
		roboDrive.setSafetyEnabled(RobotMap.STARTING_MOTOR_SAFETY);
		shooterSub.updateSafety(false);
		climberSub.updateSafety(false);
		loaderSub.updateSafety(false);
		 
		// Adding Autonomous
		chooser.addDefault("Auto-Default: Touchline", new TouchlineAuto());
		chooser.addObject("Boiler - Blue", new BoilerAutoBlue());
		chooser.addObject("Boiler - Red", new BoilerAutoRed());
		chooser.addObject("Gear", new GearAuto());
		chooser.addObject("Touchline", new TouchlineAuto());
		chooser.addObject("Do Nothing", new DoNothing());
		// Adding RobotDrive Options
		SmartDashboard.putData("Autonomous mode", chooser);
		// Ensure all data boxes are sent and created before starting match(just
		// in case)
		SmartDashboard.putNumber("GyroAngle: ", gyro.getAngleDegrees());
		SmartDashboard.putData(" ", gyro.getSendable());
		SmartDashboard.putNumber("Ultrasonic: ", ultra.getDistanceMM());
		SmartDashboard.putNumber("Percent Strength Shooter", RobotMap.shooterSpeed);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		// Recalibrating Gyro right before Match Start so it doesnt callibrate
		// as soon as you turn on
		gyro.calibrate();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override 
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
			if ((!RobotMap.isRoboDriveCMDOn) && isEnabled() && isOperatorControl()) {
				roboDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY()  * .45, -.25 * stick.getTwist(), 0);
			} 
		SmartDashboard.putNumber("GyroAngle: ", gyro.getAngleDegrees());
		SmartDashboard.putData(" ", gyro.getSendable());
		SmartDashboard.putNumber("Ultrasonic: ", ultra.getDistanceMM());
		SmartDashboard.putNumber("Percent Strength Shooter", RobotMap.shooterSpeed);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
