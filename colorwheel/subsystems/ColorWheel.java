/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.colorwheel.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.I2C;

public class ColorWheel extends SubsystemBase {
  /**
   * Creates a new ColorWheel.
   */
  
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort); 
    private final ColorMatch colorMatcher = new ColorMatch();
    public Color detectedColor = colorSensor.getColor();
    public final  ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    public String colorString;
    public final int proximity = colorSensor.getProximity();
  public ColorWheel() {
 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   
         
         
       
          final double IR = colorSensor.getIR();
          
          SmartDashboard.putNumber("Red", detectedColor.red );
          SmartDashboard.putNumber("Blue", detectedColor.blue);
          SmartDashboard.putNumber("Green", detectedColor.green);
          SmartDashboard.putNumber("IR" , IR);
          
          SmartDashboard.putNumber("Proximity", proximity);  
          SmartDashboard.putNumber("Confidence", match.confidence);
          SmartDashboard.putString("Detected Color", colorString);
          
      
      }
    
  }
