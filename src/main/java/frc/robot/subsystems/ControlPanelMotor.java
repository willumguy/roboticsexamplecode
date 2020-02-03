/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import java.lang.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.Spintocolor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControlPanelMotor extends SubsystemBase {

    public static WPI_TalonSRX cpmotor = new WPI_TalonSRX(8);
    double speed = 0;
    
    DriverStation driver;
    char targetcolor = 'G';
    
    char detectedcolor = 'G';
    String gameData = (String) driver.getInstance().getGameSpecificMessage();
    //targetcolor = gameData.charAt(0);
    
    
    public ControlPanelMotor(){
        setDefaultCommand(new Spintocolor(this));
    }

    public void Spintocolor(){
        detectedcolor = RobotContainer.colorSensorSub.getColorChar();
        speed = 0;
        if(targetcolor == detectedcolor){
            speed = 0.5;
            cpmotor.set(speed);
        }
        SmartDashboard.putNumber("speed", speed);
    }

    public void SpinClockwise(){
        speed = 0.5;
        cpmotor.set(speed);
    }

    public void SpinCounterClockwise(){
        speed = -0.5;
        cpmotor.set(speed);
       // RobotContainer.colorSensorSub.moving(false);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
