import com.qualcomm.robotcore.hardware.*;

public class Wait {
  WaitInstruction wait;

/*
  public Wait(HardwareMap hw, double seconds) {
    wait = new WaitInstruction(hw, seconds);
  }
*/
  public void run(HardwareMap hw, double seconds) {
    wait = new WaitInstruction(hw, seconds);
    while (!wait.act()) {}
  }
}
