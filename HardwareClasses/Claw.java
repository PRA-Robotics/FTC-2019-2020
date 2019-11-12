import com.qualcomm.robotcore.hardware.*;

public class Claw {
  Servo claw;

  double upPos;
  double downPos;
  double currentPos;

  MiddlePassage(HardwareMap hw) {
    claw = hw.get(Servo.class, "claw");

    upPos = claw.getPosition();
    currentPos = upPos;
    downPos = .5;
  }

  public void down() {
    claw.setPosition(downPos);
    currentPos = downPos;
  }

  public void reset() {
    claw.setPosition(upPos);
    currentPos = upPos;
  }

  public double getPosition() {
    return currentPos;
  }
}
