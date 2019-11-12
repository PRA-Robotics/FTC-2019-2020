import com.qualcomm.robotcore.hardware.*;

public class Instruction{
  public DcMotor flDrive;
  public DcMotor frDrive;
  public DcMotor blDrive;
  public DcMotor brDrive;
  public DcMotor irDrive;
  public DcMotor ilDrive;
  public boolean isDone;
  public int trial;
  public double flFinal;
  public double frFinal;
  public double blFinal;
  public double brFinal;
  public double flPosition;
  public double frPosition;
  public double blPosition;
  public double brPosition;
  public int increments;

  public Instruction(HardwareMap hw){
    flDrive = hw.get(DcMotor.class, "flDrive");
    frDrive = hw.get(DcMotor.class, "frDrive");
    blDrive = hw.get(DcMotor.class, "blDrive");
    brDrive = hw.get(DcMotor.class, "brDrive");
    irDrive = hw.get(DcMotor.class, "irDrive");
    ilDrive = hw.get(DcMotor.class, "ilDrive");
    motorInit(flDrive);
    motorInit(frDrive);
    motorInit(blDrive);
    motorInit(brDrive);
    isDone = false;
    trial = 0;
  }

  private void motorInit(DcMotor m){
    m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m.setTargetPosition(0);
    m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  }

  public boolean act(){
    return true;
  }
}
