import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitInstruction extends Instruction {
  static final int MILS_PER_SEC = 1000;
  int finalTime;
  double seconds;
  ElapsedTime timer;

  public WaitInstruction(HardwareMap hw, double seconds) {
    super(hw);
    timer = new ElapsedTime();
    this.seconds = seconds;
  }

  public void init() {
    finalTime = (int) (seconds * MILS_PER_SEC + timer.milliseconds());
  }

  private void end() {
    isDone = true;
  }

  @Override
  public boolean act(){
    if(isDone) {
      return true;
    } else if(trial == 0) {
      init();
    } else if(timer.milliseconds() >= finalTime) {
      end();
      return true;
    }
    trial ++;
    return false;
  }
}
