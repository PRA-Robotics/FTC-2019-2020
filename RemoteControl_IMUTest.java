/*
// IMU Test added
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.util.RobotLog;
// end IMU Test add

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Remote Control IMU Tesnt", group="TeleOp")
public class RemoteControlIMUTest extends OpMode{
  public DcMotor flDrive;
  public DcMotor frDrive;
  public DcMotor blDrive;
  public DcMotor brDrive;

// Add IMU Test
  public BNO055IMU imu;
// End IMU Test

  public void init(){
    flDrive = hardwareMap.get(DcMotor.class, "flDrive");
    frDrive = hardwareMap.get(DcMotor.class, "frDrive");
    blDrive = hardwareMap.get(DcMotor.class, "blDrive");
    brDrive = hardwareMap.get(DcMotor.class, "brDrive");

    // Add IMUTest
    RobotLog.ii("DbgLog", "Initialise IMU");
    BNO055IMU.Parameters imuParameters;
    Orientation angles;
    Acceleration gravity;

    imu = hardwareMap.get(BNO055IMU.class, "imu");

    // Create new IMU Parameters object.
    imuParameters = new BNO055IMU.Parameters();
    // Use degrees as angle unit.
    imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    // Express acceleration as m/s^2.
    imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    // Disable logging.
    imuParameters.loggingEnabled = false;
    // Initialize IMU.
    imu.initialize(imuParameters);
  }

  public void loop(){
    flDrive.setPower((-Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 + gamepad1.right_stick_x));
    frDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
    blDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2)  * Math.min(1 , 1 + gamepad1.right_stick_x));
    brDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
    if(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2)){
      flDrive.setPower(gamepad1.right_stick_x);
      frDrive.setPower(-gamepad1.right_stick_x);
      blDrive.setPower(gamepad1.right_stick_x);
      brDrive.setPower(-gamepad1.right_stick_x);

      //Add IMU Test
      angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
      gravity = imu.getGravity();
      // Display orientation info.
      telemetry.addData("rot about Z", angles.firstAngle);
      telemetry.addData("rot about Y", angles.secondAngle);
      telemetry.addData("rot about X", angles.thirdAngle);
      RobotLog.ii("DbgLog", "rot about Z" + angles.firstAngle);
      RobotLog.ii("DbgLog", "rot about Y" + angles.secondAngle);
      RobotLog.ii("DbgLog", "rot about X" + angles.thirdAngle);

      // Display gravitational acceleration.
      telemetry.addData("gravity (Z)", gravity.zAccel);
      telemetry.addData("gravity (Y)", gravity.yAccel);
      telemetry.addData("gravity (X)", gravity.xAccel);
      RobotLog.ii("DbgLog", "gravity (Z)" + gravity.zAccel);
      RobotLog.ii("DbgLog", "gravity (Y)" + gravity.yAccel);
      RobotLog.ii("DbgLog", "gravity (X)" + gravity.xAccel);

      telemetry.update();
      //End IMU Test

    }
  }

  public void move(double x, double y){//X and Y are local positioning based of current rotation

  }
}
*/
