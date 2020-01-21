public class Wait {
  WaitInstruction wait;

  public Wait(HardwareMap hw, double seconds) {
    wait = new WaitInstruction(hw, seconds);
  }

  public void run() {
    while (wait1.act()) {}
  }
}
