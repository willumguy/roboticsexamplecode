/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.RunShooter;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Motor.
   */
  private final WPI_TalonSRX motor = new WPI_TalonSRX(4);
  private final WPI_TalonSRX motor2 = new WPI_TalonSRX(6);
  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.05, 12/5310.0);
  private final PIDController shooterpid = new PIDController(0.5,0,0);

  
  public Shooter() {
    motor2.setInverted(true);
    motor2.follow(motor);
    shooterpid.setTolerance(200);
    setDefaultCommand(new RunShooter(this, 1700));
  }

  public void move(double speed) {
    shooterpid.setSetpoint(speed);
    double ffvalue = feedforward.calculate(speed);
    double pidvalue = shooterpid.calculate(motor.getSelectedSensorVelocity(), speed);
    double voltage = ffvalue+pidvalue;

    SmartDashboard.putNumber("feedforward", ffvalue);
    SmartDashboard.putNumber("pid", pidvalue);
    SmartDashboard.putNumber("voltage", voltage);
    SmartDashboard.putNumber("RPM", motor.getSelectedSensorVelocity()*60000);


    motor.setVoltage(voltage);
    
    
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
