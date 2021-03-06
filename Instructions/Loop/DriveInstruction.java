import com.qualcomm.robotcore.hardware.*;

public class DriveInstruction extends Instruction{
  static final int INC_PER_METER = 100;
  static final double CIRCUMFERENCE = .1256637;
  double speed = 1;

  public DriveInstruction(HardwareMap hw, double distance) {
    this(hw, distance, 0);
  }

  public DriveInstruction(HardwareMap hw, double distance, double angle){
    super(hw);
    double correctionFactor = 100.0/236.7 * 59.8/57.0;
    angle = Math.toRadians(angle);
    flFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (Math.sin(angle) + Math.cos(angle))) / CIRCUMFERENCE);
    frFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (Math.sin(angle) - Math.cos(angle))) / CIRCUMFERENCE);
    blFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (- Math.sin(angle) + Math.cos(angle))) / CIRCUMFERENCE);
    brFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (- Math.sin(angle) - Math.cos(angle))) / CIRCUMFERENCE);
    increments = (int) (distance * INC_PER_METER);
    flDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    blDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    brDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  }

  public DriveInstruction(HardwareMap hw, double distance, double angle, double s){
    super(hw);
    double correctionFactor = 100.0/236.7 * 59.8/57.0;
    angle = Math.toRadians(angle);
    flFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (Math.sin(angle) + Math.cos(angle))) / CIRCUMFERENCE);
    frFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (Math.sin(angle) - Math.cos(angle))) / CIRCUMFERENCE);
    blFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (- Math.sin(angle) + Math.cos(angle))) / CIRCUMFERENCE);
    brFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (- Math.sin(angle) - Math.cos(angle))) / CIRCUMFERENCE);
    increments = (int) (distance * INC_PER_METER / speed);
    flDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    blDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    brDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    speed = s;
  }

  private void init(){
    flPosition = flDrive.getCurrentPosition();
    frPosition = frDrive.getCurrentPosition();
    blPosition = blDrive.getCurrentPosition();
    brPosition = brDrive.getCurrentPosition();
  }

  private boolean isCloseEnough(DcMotor m){
    if(Math.abs(m.getCurrentPosition() - m.getTargetPosition()) < 50){
      return false;
    }
    return true;
  }

  private void end(){
    //while(!(isCloseEnough(flDrive) && isCloseEnough(frDrive) && isCloseEnough(blDrive) && isCloseEnough(brDrive))){
      speed = 1;
      runTo(flDrive, (int)flPosition);
      runTo(frDrive, (int)frPosition);
      runTo(blDrive, (int)blPosition);
      runTo(brDrive, (int)brPosition);
      int delay = 0;
      while(delay < 30){
        runTo(flDrive, (int)flPosition);
        runTo(frDrive, (int)frPosition);
        runTo(blDrive, (int)blPosition);
        runTo(brDrive, (int)brPosition);
        delay ++;
      }
    //}
    flDrive.setPower(0);
    frDrive.setPower(0);
    blDrive.setPower(0);
    brDrive.setPower(0);
    isDone = true;
    flDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    blDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    brDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  }

  @Override
  public boolean act(){
    if(isDone){
      return true;
    }else if(trial == 0){
      init();
    }else if(trial == increments){
      end();
      return true;
    }
    flPosition += flFinal/increments;
    frPosition += frFinal/increments;
    blPosition += blFinal/increments;
    brPosition += brFinal/increments;

    runTo(flDrive, (int)flPosition);
    runTo(frDrive, (int)frPosition);
    runTo(blDrive, (int)blPosition);
    runTo(brDrive, (int)brPosition);
    trial ++;
    return false;
  }

  private void runTo(DcMotor m, int position){
    m.setTargetPosition(position);
    m.setPower(speed);
  }
}
