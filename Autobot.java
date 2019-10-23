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

  public void init(){
    flDrive = hardwareMap.get(DcMotor.class, "flDrive");
    frDrive = hardwareMap.get(DcMotor.class, "frDrive");
    blDrive = hardwareMap.get(DcMotor.class, "blDrive");
    brDrive = hardwareMap.get(DcMotor.class, "brDrive");
    flDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    flDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    frDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    blDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    blDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    brDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    brDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    blDrive.setDirection(DcMotor.Direction.REVERSE);

    flDrive.setTargetPosition((int)(560*(Math.sqrt(2)/(2*CIRCUMFERENCE))));
    frDrive.setTargetPosition((int)(560*(Math.sqrt(2)/(2*CIRCUMFERENCE))));
    blDrive.setTargetPosition((int)(560*(Math.sqrt(2)/(2*CIRCUMFERENCE))));
    brDrive.setTargetPosition((int)(560*(Math.sqrt(2)/(2*CIRCUMFERENCE))));

    flDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    blDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    brDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    flDrive.setPower(.5);
    frDrive.setPower(.5);
    blDrive.setPower(.5);
    brDrive.setPower(.5);
  }

  public void loop(){

  }
}
