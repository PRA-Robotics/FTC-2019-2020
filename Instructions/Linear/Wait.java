import com.qualcomm.robotcore.hardware.*;

public class Wait {
  static final int MILS_PER_SEC = 1000;

  public void waitTime(double seconds) {
    ElapsedTime timer = new ElapsedTime();
    finalTime = (int) (seconds * MILS_PER_SEC + timer.milliseconds());
    while (timer.milliseconds() < finalTime) {}
  }
}
