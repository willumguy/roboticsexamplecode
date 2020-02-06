/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.hal.sim.DigitalPWMSim;

//import org.frc.robot.RobotContainer;
//import org.frc.robot.commands.DetectBrokenBeam


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
//import edu.wpi.first.wpilibj.InterruptableSensorBase;
//import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 
 * Add your docs here.
 */


public class IRSensor extends SubsystemBase{
    
    

    
  int LEDPIN = 13;
  int SENSORPIN = 4;

  DigitalInput IRCount = new DigitalInput(9);
  DigitalInput IRIntake = new DigitalInput(5);
  DigitalInput IROutake = new DigitalInput(7);
  int count = 0;
  Boolean LastValue = true;
  Boolean OuttakeValue = true;

    
  public void setup(){
  
    //local variable #
   //local variable name
    if(!IRCount.get()) { 
        //Roller Begins to Move
        //Counts # of new balls in the indexer
      if(LastValue == true) {
        count++;    
        RobotContainer.indexerMotor.set(0.5);
        //RobotContainer.indexerReverseMotor.set(-0.5);
      } 

      LastValue = IRCount.get();
      
    }
    /*
    if(!(IRIntake.get())){
      //Roller Stops and hold the ball in the first position
      RobotContainer.indexerMotor.set(0);
      RobotContainer.indexerReverseMotor.set(0);
      
    }

    if  (!(IROutake.get())){
      //counts the # of balls exiting
      if(OuttakeValue = true){
        count--; 
      }
      OuttakeValue = IROutake.get();
      
    }
        
    */
    SmartDashboard.putNumber("Number of Balls: ", count);
    LastValue = IRCount.get();
  }
    
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setup();
  }

}





  //intialize the LED pin as an output:
  


