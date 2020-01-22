import com.qualcomm.robotcore.hardware.*;

public class LinTurn {
  TurnInstruction turn;

/*
  public LinTurn(HardwareMap hw, double angle) {
    turn = new TurnInstruction(hw, angle);
  }
*/
  public void run(HardwareMap hw, double angle) {
    turn = new TurnInstruction(hw, angle);
    while (!turn.act()) {}
  }
}
