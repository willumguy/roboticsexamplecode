/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.hal.sim.DigitalPWMSim;

//import org.frc.robot.RobotContainer;
//import org.frc.robot.commands.DetectBrokenBeam


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.InterruptableSensorBase;
//import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 
 * Add your docs here.
 */


public class IRSensor extends SubsystemBase{
    
    

    
    int LEDPIN = 13;
    int SENSORPIN = 4;

    DigitalInput IRCount = new DigitalInput(8);
    DigitalInput IRIntake = new DigitalInput(12);
    DigitalInput IROutake = new DigitalInput(11);
    int count = 0;
    
    
    public void setup(){

      if(!(IRCount.get())) {
       //Roller Begins to Move
       //Counts # of new balls in the indexer
        count++;    
        //RobotContainer.indexerMotor.set(0.5);
        //RobotContainer.indexerReverseMotor.set(-0.5);
      }
      /*
      if(!(IRIntake.get())){
        //Roller Stops and hold the ball in the first position
        RobotContainer.indexerMotor.set(0);
        RobotContainer.indexerReverseMotor.set(0);
      }

      if  (!(IROutake.get())){
        //counts the # of balls exiting
        count--;
      }
      */
        SmartDashboard.putNumber("Number of Balls: ", count);
    }
    
}





  //intialize the LED pin as an output:
  


