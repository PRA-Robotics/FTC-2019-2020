import com.qualcomm.robotcore.hardware.*;

public class MiddlePassage {
  DcMotor midDrive;

  final double STARTPOS = .4;
  double servoPos;

  MiddlePassage(HardwareMap hw) {
    midDrive = hw.get(DcMotor.class, "midDrive");
  }

  public void runMotor() {
    midDrive.setPower(1);
  }

  public void runMotorBack() {
    midDrive.setPower(-1);
  }

  public void reset() {
    stop();
  }

  public void stop() {
    midDrive.setPower(0);
  }
}
