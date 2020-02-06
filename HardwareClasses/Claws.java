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
    //.02
    claw1.setPosition(0.1);
    claw2.setPosition(.74);
    currentPos = downPos;
  }

  public void reset() {
    claw1.setPosition(.62);
    claw2.setPosition(.25);
    currentPos = upPos;
  }

  public double getPosition() {
    return currentPos;
  }
}
