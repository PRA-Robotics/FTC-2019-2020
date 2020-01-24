import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Wait {
  static final int MILS_PER_SEC = 1000;

  public Wait() {}

  public void waitTime(double seconds) {
    ElapsedTime timer = new ElapsedTime();
    int finalTime = (int) (seconds * MILS_PER_SEC + timer.milliseconds());
    while (timer.milliseconds() < finalTime) {}
  }
}
