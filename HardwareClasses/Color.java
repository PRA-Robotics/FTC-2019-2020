import com.qualcomm.robotcore.hardware.*;

public class Color{
  public NormalizedColorSensor colorSensor;
  NormalizedRGBA colors;

  public Color(HardwareMap hw){
    colorSensor = hw.get(NormalizedColorSensor.class, "colorSensor");
    if (colorSensor instanceof SwitchableLight) {
      ((SwitchableLight)colorSensor).enableLight(true);
    }
  }

  public double getRed(){
    colors = colorSensor.getNormalizedColors();
    float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
    return (double) colors.red / max;
  }

  public double getBlue(){
    colors = colorSensor.getNormalizedColors();
    float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
    return (double) colors.blue / max;
  }
    public double getGreen(){
      colors = colorSensor.getNormalizedColors();
      float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
      return (double) colors.green / max;
  }
}
