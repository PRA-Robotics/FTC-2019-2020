import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

public class Runner /*extends OpMode*/ {
  public Intake in;
  public Outtake out;
  public Color c;
  public MiddlePassage mid;
  public Claw claw;

  public Runner(HardwareMap hw) {
    in = new Intake(hw);
    out = new Outtake(hw);
    c = new Color(hw);
    mid = new MiddlePassage(hw);
    claw = new Claw(hw);
  }

  public double averageColor(){
    double sum = 0;
    for(int i = 0; i < 10; i ++){
      sum += c.getRed();
    }
    return sum / 10;
  }
}
