import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autobot", group = "Autonomous")
public class Autobot extends OpMode{
  public DcMotor flDrive;
  public DcMotor frDrive;
  public DcMotor blDrive;
  public DcMotor brDrive;
  static final double CIRCUMFERENCE = .1256637;
  static final int INC_PER_METER = 50;
  double correctionFactor = 100/236.5;
  double distance = 1;
  double angle = 0;
  double flFinal;
  double frFinal;
  double blFinal;
  double brFinal;
  double flPosition;
  double frPosition;
  double blPosition;
  double brPosition;
  int increments;
  int q = 0;
  public void init(){
    flDrive = hardwareMap.get(DcMotor.class, "flDrive");
    frDrive = hardwareMap.get(DcMotor.class, "frDrive");
    blDrive = hardwareMap.get(DcMotor.class, "blDrive");
    brDrive = hardwareMap.get(DcMotor.class, "brDrive");

    motorInit(flDrive);
    motorInit(frDrive);
    motorInit(blDrive);
    motorInit(brDrive);

    //blDrive.setDirection(DcMotor.Direction.REVERSE);
    //flDrive.setDirection(DcMotor.Direction.REVERSE);
  }

  public void start(){
    flFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (Math.sin(angle) + Math.cos(angle))) / CIRCUMFERENCE);
    frFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (Math.sin(angle) - Math.cos(angle))) / CIRCUMFERENCE);
    blFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (Math.sin(angle) + Math.cos(angle))) / CIRCUMFERENCE);
    brFinal = correctionFactor * 560 * ((Math.sqrt(2) * distance / 2 * (Math.sin(angle) - Math.cos(angle))) / CIRCUMFERENCE);
    flPosition = flDrive.getCurrentPosition();
    frPosition = frDrive.getCurrentPosition();
    blPosition = blDrive.getCurrentPosition();
    brPosition = brDrive.getCurrentPosition();
    increments = (int) (distance * INC_PER_METER);
  }

  public void loop(){
    if(q < increments){
      flPosition += flFinal/increments;
      frPosition += frFinal/increments;
      blPosition += blFinal/increments;
      brPosition += brFinal/increments;

      telemetry.addData("Current:", flDrive.getCurrentPosition());
      telemetry.addData("Target:", flDrive.getTargetPosition());
      telemetry.addData("Final:", flFinal);
      telemetry.update();

      runTo(flDrive, (int)flPosition);
      runTo(frDrive, (int)frPosition);
      runTo(blDrive, (int)blPosition);
      runTo(brDrive, (int)brPosition);
      q ++;
    }else{
      flDrive.setPower(0);
      frDrive.setPower(0);
      blDrive.setPower(0);
      brDrive.setPower(0);
    }
  }

  public void runTo(DcMotor m, int position){
    m.setTargetPosition(position);
    m.setPower(1);
  }

  public void motorInit(DcMotor m){
    m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m.setTargetPosition(0);
    m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  }
}
