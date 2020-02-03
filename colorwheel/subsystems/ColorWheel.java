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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.I2C;

public class ColorWheel extends SubsystemBase {
  /**
   * Creates a new ColorWheel.
   */
  public char colorCase;
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort); 
    private final ColorMatch colorMatcher = new ColorMatch();
    public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    public static final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    public static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    public Color detectedColor = colorSensor.getColor();
    public final  ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    public String colorString;
    public final int proximity = colorSensor.getProximity();
  public ColorWheel() {
  colorMatcher.addColorMatch(kBlueTarget);
  colorMatcher.addColorMatch(kGreenTarget);
  colorMatcher.addColorMatch(kRedTarget);
  colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   
         
         
         if (match.color == kBlueTarget){
             colorString = "Blue";
         }
         else if (match.color == kRedTarget){
             colorString = "Red";
         }
         else if (match.color == kGreenTarget){
             colorString = "Green";
         }
         else if (match.color == kYellowTarget){
             colorString = "Yellow";
         }
         else{
             colorString = "Unknown";
         }
          final double IR = colorSensor.getIR();
          
          SmartDashboard.putNumber("Red", detectedColor.red );
          SmartDashboard.putNumber("Blue", detectedColor.blue);
          SmartDashboard.putNumber("Green", detectedColor.green);
          SmartDashboard.putNumber("IR" , IR);
          
          SmartDashboard.putNumber("Proximity", proximity);  
          SmartDashboard.putNumber("Confidence", match.confidence);
          SmartDashboard.putString("Detected Color", colorString);
          String gameData;
  gameData = DriverStation.getInstance().getGameSpecificMessage();
  if(gameData.length() > 0)
  {
    switch (gameData.charAt(0))
    {
      case 'B' :
       Robot.colorCase = kRedTarget;
       
       //Blue case code
        break;
      case 'G' :
       Robot.colorCase = kYellowTarget;
       
        //Green case code
        break;
      case 'R' :
          Robot.colorCase = kBlueTarget;
          
        //Red case code
        break;
      case 'Y' :
          Robot.colorCase = kGreenTarget;
          
        //Yellow case code
        break;
      default :
        //This is corrupt data
        break;
          }
      
      }
    
  }
}
