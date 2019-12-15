import com.qualcomm.robotcore.hardware.*;

public class Claws {
  Servo claw1;
  Servo claw2;

  double upPos;
  double downPos;
  double currentPos;

  Claws(HardwareMap hw) {
    claw1 = hw.get(Servo.class, "claw1");
    claw2 = hw.get(Servo.class, "claw2");

    //claw.setPosition(.5);
    upPos = .5;
    currentPos = upPos;
    downPos = .31;
  }

  public void down() {
    claw1.setPosition(.02);
    claw2.setPosition(.74);
    currentPos = downPos;
  }

  public void reset() {
    claw1.setPosition(.52);
    claw2.setPosition(.25);
    currentPos = upPos;
  }

  public double getPosition() {
    return currentPos;
  }
}
