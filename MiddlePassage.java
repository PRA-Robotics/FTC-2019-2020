import com.qualcomm.robotcore.hardware.*;

public class MiddlePassage {
  Servo former;
  Servo latter;

  final double AFTPOSITION = .3;
  double changingPosition;
  double foreStartPosition;
  double latterPosition;
  double latterStartPosition;

  int q;

  MiddlePassage(HardwareMap hw) {
    former = hw.get(Servo.class, "foreMidServo");
    latter = hw.get(Servo.class, "aftMidServo");

    changingPosition = AFTPOSITION;

    foreStartPosition = 0.0;
    latterStartPosition = 0.0;

    q = 0;
  }

  public void runFront() {
    former.setPosition(AFTPOSITION);
  }

  public void runBack() {
    if (q % 2 == 0) {
      changingPosition -= (q * .05);
    } else if (q % 2 == 1) {
      changingPosition += (q * .05);
    }
    latter.setPosition(changingPosition);
  }

  public void reset() {
    resetFront();
    resetBack();
  }

  public void resetFront() {
    former.setPosition(foreStartPosition);
  }

  public void resetBack() {
    latter.setPosition(latterStartPosition);
    changingPosition = AFTPOSITION;
    q = 0;
  }
}
