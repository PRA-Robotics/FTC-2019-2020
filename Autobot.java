import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autobot", group = "Autonomous")
public class Autobot extends OpMode{
  public DcMotor flDrive;
  public DcMotor frDrive;
  public DcMotor blDrive;
  public DcMotor brDrive;
  static final double CIRCUMFERENCE = 12.56637;
  static final int INC_PER_METER = 1000;

  public void init(){
    flDrive = hardwareMap.get(DcMotor.class, "flDrive");
    frDrive = hardwareMap.get(DcMotor.class, "frDrive");
    blDrive = hardwareMap.get(DcMotor.class, "blDrive");
    brDrive = hardwareMap.get(DcMotor.class, "brDrive");

    motorInit(flDrive);
    motorInit(frDrive);
    motorInit(blDrive);
    motorInit(brDrive);

    blDrive.setDirection(DcMotor.Direction.REVERSE);

    //drive(1, 0);
  }

  public void loop(){
    drive(1, 0);
  }

  public void drive(double distance, double angle){
    double flFinal = 560 * (Math.sqrt(2) * distance / 2 * (Math.sin(angle) + Math.cos(angle))) / (CIRCUMFERENCE * Math.PI);
    double frFinal = 560 * (Math.sqrt(2) * distance / 2 * (Math.sin(angle) - Math.cos(angle))) / (CIRCUMFERENCE * Math.PI);
    double blFinal = 560 * (Math.sqrt(2) * distance / 2 * (Math.sin(angle) + Math.cos(angle))) / (CIRCUMFERENCE * Math.PI);
    double brFinal = 560 * (Math.sqrt(2) * distance / 2 * (Math.sin(angle) - Math.cos(angle))) / (CIRCUMFERENCE * Math.PI);
    double flPosition = flDrive.getCurrentPosition();
    double frPosition = frDrive.getCurrentPosition();
    double blPosition = blDrive.getCurrentPosition();
    double brPosition = brDrive.getCurrentPosition();
    int increments = (int) (distance * INC_PER_METER);
    for(int i = 0; i < increments; i ++){
      flPosition += flFinal/increments;
      frPosition += frFinal/increments;
      blPosition += blFinal/increments;
      brPosition += brFinal/increments;
      flDrive.setPower(flPosition - flDrive.getCurrentPosition());
      frDrive.setPower(frPosition - frDrive.getCurrentPosition());
      blDrive.setPower(blPosition - blDrive.getCurrentPosition());
      brDrive.setPower(brPosition - brDrive.getCurrentPosition());
    }
    while(Math.sqrt(Math.pow(flPosition - flDrive.getCurrentPosition(), 2) + Math.pow(frPosition - frDrive.getCurrentPosition(), 2) + Math.pow(blPosition - blDrive.getCurrentPosition(), 2) + Math.pow(brPosition - brDrive.getCurrentPosition(), 2)) > 100){
      flDrive.setPower(flPosition - flDrive.getCurrentPosition());
      frDrive.setPower(frPosition - frDrive.getCurrentPosition());
      blDrive.setPower(blPosition - blDrive.getCurrentPosition());
      brDrive.setPower(brPosition - brDrive.getCurrentPosition());
    }
    flDrive.setPower(0);
    frDrive.setPower(0);
    blDrive.setPower(0);
    brDrive.setPower(0);
  }

  public void motorInit(DcMotor m){
    m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
  }
}
