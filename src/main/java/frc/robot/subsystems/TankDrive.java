package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveCommand;


public class TankDrive extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  SpeedControllerGroup leftSide = new SpeedControllerGroup(RobotContainer.frontLeft, RobotContainer.middleLeft, RobotContainer.rearLeft);

  SpeedControllerGroup rightSide = new SpeedControllerGroup(RobotContainer.frontRight, RobotContainer.middleRight, RobotContainer.rearRight);

  DifferentialDrive difDrive = new DifferentialDrive(leftSide, rightSide);

  public PIDController pid = new PIDController(0, 0, 0);

  public TankDrive() {
    setDefaultCommand(new DriveCommand(this));
    pid.setTolerance(256); //Error is within 1/4 of a revolution
    
  }

  public void init() { // middleLeft and middleRight motor must go in opposite directions from the rest of the motors.
    RobotContainer.frontLeft.follow(RobotContainer.middleLeft);
    RobotContainer.rearLeft.follow(RobotContainer.middleLeft);
  
    RobotContainer.frontLeft.setInverted(true);
    RobotContainer.rearLeft.setInverted(true);
    
    RobotContainer.frontRight.follow(RobotContainer.middleRight);
    RobotContainer.rearRight.follow(RobotContainer.middleRight); 

    RobotContainer.frontRight.setInverted(true);
    RobotContainer.rearRight.setInverted(true);
  }

  public void driveWithJoystick() {
    //ONE JOYSTICK
    //make sure throttle is at 1 or -1
    double forward = (RobotContainer.stick.getY()*0.8)*RobotContainer.stick.getThrottle();
    double turn = (RobotContainer.stick.getZ()*-0.8);


    /*deadband*/
    
    if ((Math.abs(forward)<0.05) && (Math.abs(turn)<0.05))
    {
      stop();
    }

    else 
    {
      difDrive.arcadeDrive(forward, turn);
    }
  }

  public void stop() {
    RobotContainer.frontLeft.stopMotor();
    RobotContainer.middleLeft.stopMotor();
    RobotContainer.rearLeft.stopMotor();

    RobotContainer.frontRight.stopMotor();
    RobotContainer.middleRight.stopMotor();
    RobotContainer.rearRight.stopMotor();
  }

  public void driveDistance(double distance) {
    double count = distance * (1024 / (6 * Math.PI));
    pid.setSetpoint(count);
    difDrive.arcadeDrive(
      pid.calculate(RobotContainer.middleLeft.getSelectedSensorPosition(), count) ,
      0
      );
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

}