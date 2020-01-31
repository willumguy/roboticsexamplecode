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
import edu.wpi.first.wpilibj.InterruptableSensorBase;
//import edu.wpi.first.wpilibj.SensorBase;

/** 
 * Add your docs here.
 */


public class IRSensor extends SubsystemBase{
    
    

    
    int LEDPIN = 13;
    int SENSORPIN = 4;

    DigitalInput IRCount = new DigitalInput(13);
    DigitalInput IRInstake = new DigitalInput(12);
    DigitalInput IROutake = new DigitalInput(11);
    
    
    public void setup(){

      if(IR.get()) {

      }
      else{

      }


    }
}




  //intialize the LED pin as an output:
  


