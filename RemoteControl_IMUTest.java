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
  public DcMotor irDrive;
  public DcMotor ilDrive;

  final double IntakePower = 1.0;

  public Intake greenIn;

// Add IMU Test
  public BNO055IMU imu;
// End IMU Test

  public void init(){
    // Add IMU Test
    // see https://cdn-learn.adafruit.com/assets/assets/000/036/832/original/BST_BNO055_DS000_14.pdf for information on these flags
    byte AXIS_MAP_CONFIG_BYTE = 0x6; //This is what to write to the AXIS_MAP_CONFIG register to swap x and z axes
    // The identitiy of which axes to use as the new axis is defined by  Z axis is 10b, the Y axis is 01b and the K axis is 00b, 11b is illegal
    // the Akis map config byte is --ZZ YYXXb, where ZZ is one of the bit patterns above to be remapped to the Z axis,
    //                                      YY is one of the bit patterns above to be remapped to the Y axis
    //                                      XX is one of the bit patterns above to be remapped to the X axis

    byte AXIS_MAP_SIGN_BYTE = 0x1; //This is what to write to the AXIS_MAP_SIGN register to negate the z axis
    // the Axis Map Sign byte is ---- -ZYX, where Z is the sign for angle of the Z axis, Y is sign of Y axis and X is sign of X Axis
    // Z, Y, and X are either 0b meaning positive or 1b meaning negative

    // End IMU Test

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

    // Add IMUTest
    RobotLog.ii("DbgLog", "Initialise IMU");
    BNO055IMU.Parameters imuParameters;
    BNO055IMU.Parameters imu2Parameters;

    Orientation angles;
    Orientation angles2;
    Acceleration gravity;
    Acceleration gravity2;

    imu = hardwareMap.get(BNO055IMU.class, "imu");
    imu2 = hardwareMap.get(BNO055IMU.class, "imu");

    // Create new IMU Parameters object.
    imuParameters = new BNO055IMU.Parameters();
    imu2Parameters = new BNO055IMU.Parameters();
    // Use degrees as angle unit.
    imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    imu2Parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    // Express acceleration as m/s^2.
    imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    imu2Parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    // Disable logging.
    imuParameters.loggingEnabled = false;
    imu2Parameters.loggingEnabled = false;
    // Initialize IMU.
    imu.initialize(imuParameters);
    imu2.initialize(imu2Parameters);

    //Need to be in CONFIG mode to write to registers
    imu.write8(BNO055IMU.Register.OPR_MODE,BNO055IMU.SensorMode.CONFIG.bVal & 0x0F);
    imu2.write8(BNO055IMU.Register.OPR_MODE,BNO055IMU.SensorMode.CONFIG.bVal & 0x0F);

    sleep(100); //Changing modes requires a delay before doing anything else

    //Write to the AXIS_MAP_CONFIG register
    imu.write8(BNO055IMU.Register.AXIS_MAP_CONFIG,AXIS_MAP_CONFIG_BYTE & 0x3F);
    imu2.write8(BNO055IMU.Register.AXIS_MAP_CONFIG,AXIS_MAP_CONFIG_BYTE & 0x3F);

    //Write to the AXIS_MAP_SIGN register
    imu.write8(BNO055IMU.Register.AXIS_MAP_SIGN,AXIS_MAP_SIGN_BYTE & 0x07);
    imu2.write8(BNO055IMU.Register.AXIS_MAP_SIGN,AXIS_MAP_SIGN_BYTE & 0x07);

    //Need to change back into the IMU mode to use the gyro
    imu.write8(BNO055IMU.Register.OPR_MODE,BNO055IMU.SensorMode.IMU.bVal & 0x0F);
    imu2.write8(BNO055IMU.Register.OPR_MODE,BNO055IMU.SensorMode.IMU.bVal & 0x0F);

    sleep(100); //Changing modes again requires a delay
  }

  public void loop(){
    flDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 + gamepad1.right_stick_x));
    frDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));
    blDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 + Math.sqrt(2) * gamepad1.left_stick_x / 2)  * Math.min(1 , 1 + gamepad1.right_stick_x));
    brDrive.setPower((Math.sqrt(2) * gamepad1.left_stick_y / 2 - Math.sqrt(2) * gamepad1.left_stick_x / 2) * Math.min(1 , 1 - gamepad1.right_stick_x));

    if (gamepad1.right_bumper) {
      greenIn.run();
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

    //Add IMU Test
    angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    gravity = imu.getGravity();
    angles2 = imu2.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    gravity2 = imu2.getGravity();

    // Display orientation info.
    telemetry.addData("IMU1 Rot (deg) ","about Z,Y,X = (%.0f, %.0f, %.0f)", angles.firstAngle,angles.secondAngle, angles.thirdAngle);
    telemetry.addData("IMU2 rot (deg) ","about Z,Y,X = (%.0f, %.0f, %.0f)", angles2.firstAngle,angles2.secondAngle, angles2.thirdAngle);

    RobotLog.ii("DbgLog", "IMU1 rot about (Z, Y, X) = (" + angles.firstAngle+", "+angles.secondAngle+", "+angles.thirdAngle+")");
    RobotLog.ii("DbgLog", "IMU2 rot about (Z, Y, X) = (" + angles2.firstAngle+", "+angles2.secondAngle+", "+angles2.thirdAngle+")");


    // Display gravitational acceleration.
    telemetry.addData("IMU1 Gravity ","(Z, Y, X) = (%.1f, %.1f, %.1f)", gravity.zAccel, gravity.yAccel, gravity.xAccel);
    telemetry.addData("IMU1 Gravity ","(Z, Y, X) = (%.1f, %.1f, %.1f)", gravity2.zAccel, gravity2.yAccel, gravity2.xAccel);

    RobotLog.ii("DbgLog", "IMU1 gravity (Z, Y, X) = (" + gravity.zAccel+", "+gravity.yAccel+", "+gravity.xAccel+")");
    RobotLog.ii("DbgLog", "IMU2 gravity (Z, Y, X) = (" + gravity2.zAccel+", "+gravity2.yAccel+", "+gravity2.xAccel+")");


    telemetry.update();
    //End IMU Test

    }
  }


  public void motorInit(DcMotor m){
    m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
  }

  public void move(double x, double y){//X and Y are local positioning based of current rotation

  }
}
*/
