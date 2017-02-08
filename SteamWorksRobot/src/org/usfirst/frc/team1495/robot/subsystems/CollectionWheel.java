package org.usfirst.frc.team1495.robot.subsystems;

import org.usfirst.frc.team1495.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CollectionWheel extends Subsystem {

	VictorSP wheel = new VictorSP(RobotMap.COLLECTION_WHEEL);

	public CollectionWheel()
	{
		wheel.setSafetyEnabled(true);
	}

	@Override
    protected void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
   public void spinPositive() {
    	wheel.set(1);
    }
    
    public void spinNegative() {
    	wheel.set(-1);
    }
    
    public void stopSpin() {
    	wheel.set(0);
    }
}