import com.qualcomm.robotcore.hardware.*;

/**
 * @author Jackson
 */
public class Outtake {
  private int drivePos;

  private DcMotor outDrive;
  private CRServo outLeft;
  private CRServo OLDrive;
  private CRServo outRight;
  private CRServo ORDrive;

  public Outtake(HardwareMap hw) {
    outLeft = hw.get(CRServo.class, "OL");
    outRight = hw.get(CRServo.class, "OR");
    OLDrive = hw.get(CRServo.class, "OLD");
    ORDrive = hw.get(CRServo.class, "ORD");

    outDrive = hw.get(DcMotor.class, "outDrive");
    outDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    outDrive.setTargetPosition(0);
    outDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  }

  public void close() {
    
  }

  public void runToPosition(int newPos) {
    outDrive.setTargetPosition(newPos);
    outDrive.setPower(1);
  }

  public int getPosition() {
    return outDrive.getCurrentPosition();
  }

  public void stop() {
    outDrive.setPower(0);
  }

  private boolean isCloseEnough(DcMotor m) {
    if(Math.abs(m.getCurrentPosition() - m.getTargetPosition()) < 50){
      return true;
    }
    return false;
  }

  private void runTo(int position) {
    this(outDrive, position);
  }

  private void runTo(DcMotor m, int position) {
    m.setTargetPosition(position);
    m.setPower(1);
  }
}
