import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

/**
 * @author Jackson and Tyler
 */
@TeleOp(name="Remote Control", group="TeleOp")
public class RemoteControl extends OpMode {

  public DcMotor flDrive;
  public DcMotor frDrive;
  public DcMotor blDrive;
  public DcMotor brDrive;
  public DcMotor irDrive;
  public DcMotor ilDrive;
  public DcMotor outDrive;
  public NormalizedColorSensor colorSensor;
  double speed = 1;

  private int outPosition;
  boolean OutIsClosed = false;
  boolean clawIsDown;
  int delay = 0;
  int delay2 = 0;

  public Intake in;
  public Outtake out;
  public MiddlePassage mid;
  public Claw claw;

  public void init(){
    flDrive = hardwareMap.get(DcMotor.class, "flDrive");
    frDrive = hardwareMap.get(DcMotor.class, "frDrive");
    blDrive = hardwareMap.get(DcMotor.class, "blDrive");
    brDrive = hardwareMap.get(DcMotor.class, "brDrive");
    irDrive = hardwareMap.get(DcMotor.class, "irDrive");
    ilDrive = hardwareMap.get(DcMotor.class, "ilDrive");
    outDrive = hardwareMap.get(DcMotor.class, "outDrive");

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

    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    mid = new MiddlePassage(hardwareMap);
    claw = new Claw(hardwareMap);

    outPosition = out.getPosition();
  }

  public void loop(){
    if(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2) != 0){
      double spd = speed * (Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 + gamepad1.right_stick_x);
      flDrive.setPower(spd);
      frDrive.setPower(speed * (Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
      blDrive.setPower(speed * (Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2)  * Math.min(1 , 1 + gamepad1.right_stick_x));
      brDrive.setPower(speed * (Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
      telemetry.addData("flpower", spd);
    }
    if (gamepad1.right_bumper) {
      in.run();
      mid.runMotor();
    }else if(gamepad1.left_bumper){
      in.reverse();
      mid.runMotorBack();
    }else{
      in.stop();
      mid.stop();
    }

    if(gamepad1.left_trigger > 0.1){
      speed = 0.5;
    }else{
      speed = 1;
    }

    if (gamepad2.right_bumper && delay2 > 25) {
      if (clawIsDown) {
        claw.reset();
        clawIsDown = false;
      } else {
        claw.down();
        clawIsDown = true;
      }
      delay2 = 0;
    }

    if (gamepad2.dpad_up) {
      out.elevate();
    } else if (gamepad2.dpad_down) {
      out.lower();
    } else {
      out.stopMotor();
    }
    outPosition = out.getPosition();

    if (gamepad2.y) {
      out.runWheelsOut();
    } else if (gamepad2.a) {
      out.runWheelsIn();
    } else {
      out.stopWheels();
    }

    if (gamepad2.b && delay > 25) {
      if (!OutIsClosed) {
        out.close();
        OutIsClosed = true;
      } else {
        out.open();
        OutIsClosed = false;
      }
      delay = 0;
    }
    delay ++;
    delay2++;

    if(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2) == 0){
      flDrive.setPower(-gamepad1.right_stick_x*.6 * speed);
      frDrive.setPower(gamepad1.right_stick_x*.6 * speed);
      blDrive.setPower(-gamepad1.right_stick_x*.6 * speed);
      brDrive.setPower(gamepad1.right_stick_x*.6 * speed);
    }

    telemetry.update();
  }

  public void motorInit(DcMotor m){
    m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  }
}
