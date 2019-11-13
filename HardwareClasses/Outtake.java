import com.qualcomm.robotcore.hardware.*;

/**
 * @author Jackson
 */
public class Outtake {
  private int servoPos;

  private DcMotor outDrive;
  private Servo outLeft;
  private CRServo OLDrive;
  private Servo outRight;
  private CRServo ORDrive;

  public Outtake(HardwareMap hw) {
    outLeft = hw.get(Servo.class, "OL");
    outRight = hw.get(Servo.class, "OR");
    OLDrive = hw.get(CRServo.class, "OLD");
    ORDrive = hw.get(CRServo.class, "ORD");

    outDrive = hw.get(DcMotor.class, "outDrive");
    outDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    outDrive.setTargetPosition(0);
    outDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  }

  public void close() {
    outLeft.setPosition(0);
    outRight.setPosition(0.82);
  }

  public void open() {
    outLeft.setPosition(.4);
    outRight.setPosition(.4);
  }

  public void runToPosition(int newPos) {
    outDrive.setTargetPosition(newPos);
    outDrive.setPower(1);
  }

  public void runWheelsIn() {
    OLDrive.setPower(.8);
    ORDrive.setPower(-0.8);
  }

  public void runWheelsOut() {
    OLDrive.setPower(-0.8);
    ORDrive.setPower(.8);
  }

  public int getPosition() {
    return outDrive.getCurrentPosition();
  }

  public void fullStop() {
    this.stopMotor();
    this.stopWheels();
  }

  public void stopMotor() {
    outDrive.setPower(0);
  }

  public void stopWheels() {
    ORDrive.setPower(0);
    OLDrive.setPower(0);
  }

  private boolean isCloseEnough(DcMotor m) {
    if(Math.abs(m.getCurrentPosition() - m.getTargetPosition()) < 50){
      return true;
    }
    return false;
  }

  private void runTo(int position) {
    this.runTo(outDrive, position);
  }

  private void runTo(DcMotor m, int position) {
    m.setTargetPosition(position);
    m.setPower(1);
  }

}
