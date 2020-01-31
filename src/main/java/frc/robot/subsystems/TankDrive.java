package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;


import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveCommand;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;




public class TankDrive extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  
 
   
  //private final TrapezoidProfile.Constraints m_constraints = new TrapezoidProfile.Constraints(0, 0.5);

  public final PIDController pid = new PIDController(1, 0, 0);

  public final PIDController gyroPid = new PIDController(1, 0, 0);

  public final AHRS ahrs = new AHRS();
  

  public TankDrive() {
    setDefaultCommand(new DriveCommand(this));
    pid.setTolerance(128); //Error is within 1/8 of a revolution
    gyroPid.setTolerance(5); //Error for turn is within 5 degrees
    
  }


  public void driveWithJoystick() {
    //ONE JOYSTICK
    //make sure throttle is at 1 or -1
    double forward = (RobotContainer.stick.getY())*.8;
    double turn = (RobotContainer.stick.getZ())*.8;

    /*deadband*/
    
    if ((Math.abs(forward)<0.05) && (Math.abs(turn)<0.05))
    {
      stop();
    }

    else 
    {
      RobotContainer.difDrive.arcadeDrive(forward, turn);
    }
    
    
  }
  
  

  public void stop() {
    RobotContainer.difDrive.arcadeDrive(0, 0);
  }
  


  public void driveDistance(double distance) {
    double count = -distance * (1024 / (6 * Math.PI));
    RobotContainer.middleLeft.setSelectedSensorPosition(0);
    pid.setSetpoint(count);
    
    
  }
  public void updateDrive() {
    double output = pid.calculate(RobotContainer.middleLeft.getSelectedSensorPosition(), pid.getSetpoint());
    SmartDashboard.putNumber("count", pid.getSetpoint());
    SmartDashboard.putNumber("current count", RobotContainer.middleLeft.getSelectedSensorPosition());

    
    if (output > 0.4) {
      output = 0.4;
    }
    if (output < -0.4) {
      output = -0.4;
    }
    RobotContainer.difDrive.arcadeDrive(output, 0);
  }

  public void turnSetpoint(double angle) {
    gyroPid.setSetpoint(angle);  
  }

  public void updateTurn() {
    double gyroOutput = pid.calculate(ahrs.getAngle(), gyroPid.getSetpoint());
    if (gyroOutput > 0.4) {
      gyroOutput = 0.4;
    }
    if (gyroOutput < -0.4) {
      gyroOutput = -0.4;
    }
    RobotContainer.difDrive.arcadeDrive(0, gyroOutput); 
  }

  public void receiveAngles() {
    SmartDashboard.putNumber("Yaw angle", ahrs.getAngle());
    SmartDashboard.putNumber("Roll angle", ahrs.getRoll());
    SmartDashboard.putNumber("Pitch angle", ahrs.getPitch());
    SmartDashboard.putBoolean("Connected", ahrs.isConnected());
  } 
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    receiveAngles();
  }
  

}