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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControlPanelMotor extends SubsystemBase {

    public static WPI_TalonSRX cpmotor = new WPI_TalonSRX(10);
    double speed = 0;
    char detectedcolor;
    char targetcolor = 'G';
    double revCount = 0;
    boolean alreadychecked = false;

    public ControlPanelMotor(){
        //setDefaultCommand(new Spintocolor(this));
    }

    public int colorCharToInt(char colorchar){
        int output;
        switch (colorchar)
        {
            case 'R':
                output = 1;
                break;
            case 'G':
                output = 2;
                break;
            case 'B':
                output = 3;
                break;
            case 'Y':
                output = 4;
                break;
                default:
                output = 0;
        }
        return output;
    }

    public Boolean Spintocolor(){
        detectedcolor = RobotContainer.colorSensorSub.getColorChar();
        int detectedColorInt = colorCharToInt(detectedcolor);

        targetcolor = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
        int targetColorInt = colorCharToInt(targetcolor);

        cpmotor.set(.5);

        detectedColorInt = (detectedColorInt + 2) % 4;

        if(targetColorInt == detectedColorInt){
            cpmotor.set(0);
            return true;
        }

        SmartDashboard.putString("detectedcolor char", "" + detectedcolor);
        SmartDashboard.putNumber("speed", speed);
        SmartDashboard.putString("targetedcolor", "" + targetcolor);

        return false;
    }
    
    public Boolean Spinnumber(int targetn, char initialcolor){
        detectedcolor = RobotContainer.colorSensorSub.getColorChar();
        
        cpmotor.set(0.5);
        if ((detectedcolor == initialcolor) && !alreadychecked)
        {
            revCount += 0.5;
            
            alreadychecked = true;
            
        } 
        if (detectedcolor != initialcolor) {
            alreadychecked = false;
        }

        if (revCount == targetn) {
            revCount = 0;
            cpmotor.set(0);
            return true;
        }
        
        SmartDashboard.putNumber("colordetectcount", revCount);
        return false;
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
