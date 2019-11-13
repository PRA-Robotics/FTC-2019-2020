import com.qualcomm.robotcore.hardware.*;

public class MiddlePassage {
  Servo mid;
  DcMotor midDrive;

  final double STARTPOS = .4;
  double servoPos;


  MiddlePassage(HardwareMap hw) {
    mid = hw.get(Servo.class, "midServo");
    midDrive = hw.get(DcMotor.class, "midDrive");

    //servoPos = STARTPOS;

    //midDrive.setDirection(DcMotor.Direction.reverse);
  }

  public void runServo() {
    mid.setPosition(servoPos);
  }

  public void runMotor() {
    midDrive.setPower(1);
  }

  public void reset() {
    resetServo();
    stop();
  }

  public void stop() {
    midDrive.setPower(0);
  }

  public void resetServo() {
    mid.setPosition(STARTPOS);
  }
}
