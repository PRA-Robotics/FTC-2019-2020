import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Remote Control", group="TeleOp")
public class RemoteControl extends OpMode {
  //public DcMotor[] motors = DcMotor[6];

  public DcMotor flDrive;
  public DcMotor frDrive;
  public DcMotor blDrive;
  public DcMotor brDrive;
  public DcMotor irDrive;
  public DcMotor ilDrive;

  final double IntakePower = 1.0;

  public Intake greenIn;

  public void init(){
    /*
    DcMotor[0] = hardwareMap.get(DcMotor.class, "flDrive");
    DcMotor[1] = hardwareMap.get(DcMotor.class, "frDrive");
    DcMotor[2] = hardwareMap.get(DcMotor.class, "blDrive");
    DcMotor[3] = hardwareMap.get(DcMotor.class, "brDrive");
    DcMotor[4] = hardwareMap.get(DcMotor.class, "ilDrive");
    DcMotor[5] = hardwareMap.get(DcMotor.class, "irDrive");*/

    flDrive = hardwareMap.get(DcMotor.class, "flDrive");
    frDrive = hardwareMap.get(DcMotor.class, "frDrive");
    blDrive = hardwareMap.get(DcMotor.class, "blDrive");
    brDrive = hardwareMap.get(DcMotor.class, "brDrive");
    irDrive = hardwareMap.get(DcMotor.class, "irDrive");
    ilDrive = hardwareMap.get(DcMotor.class, "ilDrive");

    motorInit(flDrive);
    motorInit(frDrive);
    motorInit(blDrive);
    motorInit(brDrive);

    blDrive.setDirection(DcMotor.Direction.REVERSE);
    irDrive.setDirection(DcMotor.Direction.REVERSE);
    Intake greenIn = new Intake(hardwareMap);
  }

  public void loop(){
    /*motors[0].setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 + gamepad1.right_stick_x));
    motors[1].setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
    motors[2].setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 + gamepad1.right_stick_x));
    motors[3].setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));*/
    flDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 + gamepad1.right_stick_x));
    frDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
    blDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2)  * Math.min(1 , 1 + gamepad1.right_stick_x));
    brDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));

    while (gamepad1.right_bumper) {
        greenIn.run();
        //Telemetry.addData("yooo");
    }

    if(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2) == 0){
      /*motors[0].setPower(-gamepad1.right_stick_x*.6);
      motors[1].setPower(-gamepad1.right_stick_x*.6);
      motors[2].setPower(-gamepad1.right_stick_x*.6);
      motors[3].setPower(-gamepad1.right_stick_x*.6);*/
      flDrive.setPower(-gamepad1.right_stick_x*.6);
      frDrive.setPower(gamepad1.right_stick_x*.6);
      blDrive.setPower(-gamepad1.right_stick_x*.6);
      brDrive.setPower(gamepad1.right_stick_x*.6);
    }
  }

  public void motorInit(DcMotor m){
    m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
  }
}
