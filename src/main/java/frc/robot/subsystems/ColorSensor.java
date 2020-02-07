/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DetectColor;

public class ColorSensor extends SubsystemBase {
  /**
   * Creates a new ColorSensor.
   */

  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(Constants.i2cPort);

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.37, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  String colorString;

  public ColorSensor() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);

    setDefaultCommand(new DetectColor(this));
  }

  public void getColor() {

    Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    
    

    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    /*
    int rawred = m_colorSensor.getRed();
    int rawgreen = m_colorSensor.getGreen();
    int rawblue = m_colorSensor.getBlue();
    int color = detectedcolor.hashCode();
    */
    int ir = m_colorSensor.getIR();
    int proximity = m_colorSensor.getProximity();
    Color detectedcolor = m_colorSensor.getColor();
    
    int color = detectedcolor.hashCode();
    
    /*
    if ((detectedcolor > ) && (detectedcolor < ))
      altcolorString = "Red";
    else if ((detectedcolor > ) && (detectedcolor < ))
      altcolorString = "Green";
    else if ((detectedcolor > ) && (detectedcolor < ))
      altcolorString = "Blue";
    else if ((detectedcolor > ) && (detectedcolor < ))
      altcolorString = "Yellow";
      */
    
    

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    
    
    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putNumber("Proximity",proximity);
    SmartDashboard.putNumber("IR",ir);
    //SmartDashboard.putString("Detected Color alt",altcolorString);
    
    SmartDashboard.putNumber("Color", color);
    /*
    SmartDashboard.putNumber("RawRed",rawred);
    SmartDashboard.putNumber("RawGreen",rawgreen);
    SmartDashboard.putNumber("RawBlue",rawblue);
    SmartDashboard.putString("getcolor color",detectedColor.toString());
    */
  }
    public void moving(boolean moving){
      if(moving){
      SmartDashboard.putNumber("moving", .6);
      }else{
        SmartDashboard.putNumber("moving", 0);
      }
    }
  public char getColorChar() {
    return colorString.charAt(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
