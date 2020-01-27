import com.qualcomm.robotcore.hardware.*;

public class TurnInstruction extends Instruction {
  static final double INC_PER_THETA = .4;
  static final double CIRCUMFERENCE = .1256637;
  static final double RADIUS = 0.250825;
  static double direction = 1.0;
  boolean isNegative = false;

  public TurnInstruction(HardwareMap hw, double angle){ // 19.75 in
    super(hw);
    if (angle < 0) {
      direction = -1.0;
    }else{
      direction = 1.0;
    }
    double correctionFactor = (360.0/830.0);
    flFinal = correctionFactor * 560 * (angle * Math.PI * RADIUS / 180) / CIRCUMFERENCE;
    frFinal = correctionFactor * 560 * (angle * Math.PI * RADIUS / 180) / CIRCUMFERENCE;
    blFinal = correctionFactor * 560 * (angle * Math.PI * RADIUS / 180) / CIRCUMFERENCE;
    brFinal = correctionFactor * 560 * (angle * Math.PI * RADIUS / 180) / CIRCUMFERENCE;
    increments = (int) (direction * angle * INC_PER_THETA);
    flDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    frDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    blDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    brDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
    while(!(isCloseEnough(flDrive) && isCloseEnough(frDrive) && isCloseEnough(blDrive) && isCloseEnough(brDrive))){
      flDrive.setPower(0.2);
      frDrive.setPower(0.2);
      blDrive.setPower(0.2);
      brDrive.setPower(0.2);
      runTo(flDrive, (int)flPosition);
      runTo(frDrive, (int)frPosition);
      runTo(blDrive, (int)blPosition);
      runTo(brDrive, (int)brPosition);
    }
    flDrive.setPower(0);
    frDrive.setPower(0);
    blDrive.setPower(0);
    brDrive.setPower(0);
    runTo(flDrive, flDrive.getCurrentPosition());
    runTo(frDrive, frDrive.getCurrentPosition());
    runTo(blDrive, blDrive.getCurrentPosition());
    runTo(brDrive, brDrive.getCurrentPosition());
    isDone = true;
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
    m.setPower(1);
  }

  public double give(){
    return brDrive.getCurrentPosition();
  }
}
