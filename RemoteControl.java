
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

@TeleOp(name="Remote Control", group="TeleOp")
public class RemoteControl extends OpMode {

  public DcMotor flDrive;
  public DcMotor frDrive;
  public DcMotor blDrive;
  public DcMotor brDrive;
  public DcMotor irDrive;
  public DcMotor ilDrive;
  public NormalizedColorSensor colorSensor;

  final double IntakePower = 1.0;

  public Intake greenIn;

  public void init(){

    flDrive = hardwareMap.get(DcMotor.class, "flDrive");
    frDrive = hardwareMap.get(DcMotor.class, "frDrive");
    blDrive = hardwareMap.get(DcMotor.class, "blDrive");
    brDrive = hardwareMap.get(DcMotor.class, "brDrive");
    irDrive = hardwareMap.get(DcMotor.class, "irDrive");
    ilDrive = hardwareMap.get(DcMotor.class, "ilDrive");
    colorSensor = hardwareMap.get(NormalizedColorSensor.class, "colorSensor");
    if (colorSensor instanceof SwitchableLight) {
      ((SwitchableLight)colorSensor).enableLight(true);
    }

    motorInit(flDrive);
    motorInit(frDrive);
    motorInit(blDrive);
    motorInit(brDrive);

    blDrive.setDirection(DcMotor.Direction.REVERSE);
    flDrive.setDirection(DcMotor.Direction.REVERSE);
    irDrive.setDirection(DcMotor.Direction.REVERSE);
    greenIn = new Intake(hardwareMap);
  }

  public void loop(){
    flDrive.setPower(.5 * (Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 + gamepad1.right_stick_x));
    frDrive.setPower(.5 * (Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
    blDrive.setPower(.5 * (Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2)  * Math.min(1 , 1 + gamepad1.right_stick_x));
    brDrive.setPower(.5 * (Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
    NormalizedRGBA colors = colorSensor.getNormalizedColors();
    float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
    telemetry.addData("blue", colors.blue / max);
    telemetry.addData("red", colors.red / max);
    telemetry.addData("green", colors.green / max);
    telemetry.addData("alpha", colors.alpha / max);
    telemetry.update();
    if (gamepad1.right_bumper) {
      greenIn.run();
    }else if(gamepad1.left_bumper){
      greenIn.reverse();
    }else{
      greenIn.stop();
    }

    if(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2) == 0){
      flDrive.setPower(-gamepad1.right_stick_x*.6);
      frDrive.setPower(gamepad1.right_stick_x*.6);
      blDrive.setPower(-gamepad1.right_stick_x*.6);
      brDrive.setPower(gamepad1.right_stick_x*.6);
    }
  }

  public void motorInit(DcMotor m){
    m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
  }
}
