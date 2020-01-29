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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.MoveMotor;

public class OuttakeMotor extends SubsystemBase {
  /**
   * Creates a new Motor.
   */
  private final WPI_TalonSRX motor = new WPI_TalonSRX(4);
  private final WPI_TalonSRX motor2 = new WPI_TalonSRX(6);
  //private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.002, 5310/12.0);
  //private final PIDController shooterpid = new PIDController(0.5,0,0);


  public OuttakeMotor() {
    //motor2.setInverted(true);
   setDefaultCommand(new MoveMotor(this));
  }

  public void move(double speed) {
    /*
    motor.setVoltage();
    motor2.setVoltage(feedforward.calculate(speed));
  */
    motor.set(speed);
  }
  public void movemotor2(double speed){
    motor2.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
