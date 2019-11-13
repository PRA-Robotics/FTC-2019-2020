import com.qualcomm.robotcore.hardware.*;

public class Claw {
  Servo claw;

  double upPos;
  double downPos;
  double currentPos;

  Claw(HardwareMap hw) {
    claw = hw.get(Servo.class, "claw");

    //claw.setPosition(.5);
    upPos = .365;
    currentPos = upPos;
    downPos = .31;
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
