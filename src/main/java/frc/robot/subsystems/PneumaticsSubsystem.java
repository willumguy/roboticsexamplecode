package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.BothJoystickCommand;


public class PneumaticsSubsystem extends SubsystemBase {
    //Creates solenoid object
    //hi its me, natarichard :) ٩(♡ε♡ )۶ 

    public PneumaticsSubsystem() { 
        setDefaultCommand(new BothJoystickCommand(this));
    }
    
    public void rightpiston (DoubleSolenoid.Value state){
        RobotContainer.RightPiston.set(state);
    }
    
    public void leftpiston (DoubleSolenoid.Value state){
        RobotContainer.LeftPiston.set(state);
    }

    public void joystickmove(){
        double joysticky = (RobotContainer.mainstick.getY()*0.8);
        SmartDashboard.putNumber("joystick y",joysticky);
        if (joysticky > .1)
            {
                RobotContainer.LeftPiston.set(DoubleSolenoid.Value.kForward);
                RobotContainer.RightPiston.set(DoubleSolenoid.Value.kForward);
                SmartDashboard.putString("Pnuematics","Forward");
            }
        else if (joysticky < -.1)
        {
            RobotContainer.LeftPiston.set(DoubleSolenoid.Value.kReverse);
            RobotContainer.RightPiston.set(DoubleSolenoid.Value.kReverse);
            SmartDashboard.putString("Pnuematics","Reverse");
        }
        else
        {
            RobotContainer.LeftPiston.set(DoubleSolenoid.Value.kOff);
            RobotContainer.RightPiston.set(DoubleSolenoid.Value.kOff);
            SmartDashboard.putString("Pnuematics","Stop");
        }
    }
}
