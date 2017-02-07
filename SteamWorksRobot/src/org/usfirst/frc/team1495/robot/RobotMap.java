package org.usfirst.frc.team1495.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Initiating RobotDrive Motors PWM ports
	public static final int LEFT_FRONT = 0;
	public static final int LEFT_BACK = 1;
	public static final int RIGHT_FRONT = 2;
	public static final int RIGHT_BACK = 3;
	// Inverts Motors if true
	public static final boolean isRightSideInverted = false;
	public static final boolean isLeftSideInverted = true;
	// Joystick Port
	public static final int JOYSTICK_PORT = 1;

	// Ultrasonic sensor ports
	public static final int ULTRASONIC_CHANNEL = 0;

	/* FROM HERE ON OUT... DO NOT EDIT THIS VARIABLES OR STUFF WILL GO WRONG */
	/* FROM HERE ON OUT... DO NOT EDIT THIS VARIABLES OR STUFF WILL GO WRONG */
	/* FROM HERE ON OUT... DO NOT EDIT THIS VARIABLES OR STUFF WILL GO WRONG */
	/* FROM HERE ON OUT... DO NOT EDIT THIS VARIABLES OR STUFF WILL GO WRONG */
	/* FROM HERE ON OUT... DO NOT EDIT THIS VARIABLES OR STUFF WILL GO WRONG */

	// Is true when Drive Train is being used by a command
	public static final boolean isCMDRoboDrive = false;

	//

	/*
	 * START Vision Code stuff
	 */
	public static final int MJPEG_PORT = 1186;
	public static final String MJPEG_SERVERNAME = "MJPEG Server";
	// 0 being the Camera Name and 1 being camera location
	public static final String USB_CAM[] = { "cam0", "/dev/video0" };
	public static final String USB_CAM1[] = { "cam1", "/dev/video1" };
	/*
	 * End vision Code
	 */

}
