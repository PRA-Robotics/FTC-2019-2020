public class LinTurn {
  TurnInstruction turn;

  public LinTurn(HardwareMap hw, double angle) {
    turn = new DriveInstruction(hw, angle);
  }

  public void run() {
    while (turn.act()) {}
  }
}
