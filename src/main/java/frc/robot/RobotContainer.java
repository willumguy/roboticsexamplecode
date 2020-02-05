/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BothDownCommand;
import frc.robot.commands.BothOffCommand;
import frc.robot.commands.BothUpCommand;
import frc.robot.commands.LeftDownCommand;
import frc.robot.commands.LeftUpCommand;
import frc.robot.commands.RightDownCommand;
import frc.robot.commands.RightUpCommand;
import frc.robot.subsystems.PneumaticsSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final static PneumaticsSubsystem m_pneumaticssubsytem = new PneumaticsSubsystem();
  
  //private final static ClimbSolenoidsForward m_pneumaticscommandin = new ClimbSolenoidsForward(m_pneumaticssubsytem);

  private final static BothOffCommand m_pneumaticsoff = new BothOffCommand(m_pneumaticssubsytem);
  

  

  public static DoubleSolenoid RightPiston = new DoubleSolenoid(0,1);
  public static DoubleSolenoid LeftPiston = new DoubleSolenoid(2,3);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  public static Joystick mainstick = new Joystick(0);
  public static JoystickButton LeftUp = new JoystickButton(mainstick,5);
  public static JoystickButton LeftDown = new JoystickButton(mainstick,3);

  public static JoystickButton RightUp = new JoystickButton(mainstick,6);
  public static JoystickButton RightDown = new JoystickButton(mainstick,4);

  public static JoystickButton BothUp = new JoystickButton(mainstick,9);
  public static JoystickButton BothDown = new JoystickButton(mainstick,11);

  public static JoystickButton Stop = new JoystickButton(mainstick,12);

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@li5nk
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    LeftUp.whileHeld(new LeftUpCommand(m_pneumaticssubsytem));//
    LeftDown.whileHeld(new LeftDownCommand(m_pneumaticssubsytem));//

    RightUp.whileHeld(new RightUpCommand(m_pneumaticssubsytem));//
    RightDown.whileHeld(new RightDownCommand(m_pneumaticssubsytem));//

    BothUp.whileHeld(new BothUpCommand(m_pneumaticssubsytem));//
    BothDown.whileHeld(new BothDownCommand(m_pneumaticssubsytem));//

    Stop.whenPressed(new BothOffCommand(m_pneumaticssubsytem));//

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_pneumaticsoff;
  }
}
