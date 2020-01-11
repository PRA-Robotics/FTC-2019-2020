import com.qualcomm.robotcore.hardware.*;

public class TurnInstruction extends Instruction {
  static final double INC_PER_THETA = .4;
  static final double CIRCUMFERENCE = .1256637;
  static final double RADIUS = 0.250825;
  boolean isNegative = false;

  public TurnInstruction(HardwareMap hw, double angle){ // 19.75 in
    super(hw);
    double correctionFactor = (360.0/830.0);
    flFinal = correctionFactor * 560 * (angle * Math.PI * RADIUS / 180) / CIRCUMFERENCE;
    frFinal = correctionFactor * 560 * (angle * Math.PI * RADIUS / 180) / CIRCUMFERENCE;
    blFinal = correctionFactor * 560 * (angle * Math.PI * RADIUS / 180) / CIRCUMFERENCE;
    brFinal = correctionFactor * 560 * (angle * Math.PI * RADIUS / 180) / CIRCUMFERENCE;
    increments = (int) (angle * INC_PER_THETA);
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
    if(Math.abs(m.getCurrentPosition() - m.getTargetPosition()) < 200){
      return true;
    }
    return true;
  }

  private void end(){
    while(!(isCloseEnough(blDrive) && isCloseEnough(brDrive) && isCloseEnough(flDrive) && isCloseEnough(frDrive))){
      runTo(flDrive, (int)flPosition);
      runTo(frDrive, (int)frPosition);
      runTo(blDrive, (int)blPosition);
      runTo(brDrive, (int)brPosition);
    }
    flDrive.setPower(0);
    frDrive.setPower(0);
    blDrive.setPower(0);
    brDrive.setPower(0);
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
