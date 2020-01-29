/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class IntakeMotor extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX intakeMotor = new WPI_TalonSRX(10);

  //@Override
  
  public IntakeMotor() {

    // Set the default command for a subsystem here.
    //setDefaultCommand(new MoveMotor());
  }
  public void driveWithJoystick(){
    double speed = .5;//RobotContainer.stick.getY()*0.8;
    SmartDashboard.putNumber("speed", speed);
    intakeMotor.set(speed);
  }
public void motorBackward(){
  intakeMotor.set(-0.5);
  //SmartDashboard.putNumber("velocity",)
  
}  
public void motorForward(){
    intakeMotor.set(0.5);
  }
  
  
public void motorStop(){
  intakeMotor.set(0);
}
@Override
public void periodic() {

  // super.periodic();
  //();
}

}