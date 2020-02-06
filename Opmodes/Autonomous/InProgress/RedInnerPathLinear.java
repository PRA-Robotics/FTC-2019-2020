import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Red Inner (Linear OpMode)", group = "Autonomous")
public class RedInnerPathLinear extends LinearOpMode {
  Intake in;
  Outtake out;
  MiddlePassage mid;
  Claws claws;
  Color c;

  LinDrive drive;
  LinTurn turn;
  Wait wait;

  double[] skystoneColor = new double[3];

  int skystoneNum;

  @Override
  public void runOpMode() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    claws = new Claws(hardwareMap);
    mid = new MiddlePassage(hardwareMap);
    c = new Color(hardwareMap);

    drive = new LinDrive();
    wait = new Wait();
    turn = new LinTurn();

    /*

    _______________________________________________
    |        |        ()       ()        |        |
    |        |        ()       ()        |        |
    |________|        (*)     (*)        |________|
    | ______          ()       ()                 |
    |(  |  )          ()       ()                 |
    |() v ()          (*)     (*)                 |
    |                                             |
    |---------------------------------------------|
    |                                             |
    |               _____      _____              |
    |              |    |     |    |              |
    | \            |    |     |    |           /  |
    |   \          |____|     |____|         /    |
    |_____\________________________________/______|

    */

    waitForStart();

    out.open();
    claws.reset();

    drive.run(hardwareMap, 1.2, 270);
    wait.waitTime(.4);
    skystoneColor[0] = c.averageColor();

    drive.run(hardwareMap, .54, 165);
    wait.waitTime(.4);
    skystoneColor[1] = c.averageColor();

    drive.run(hardwareMap, .5, 165);
    wait.waitTime(.4);
    skystoneColor[2] = c.averageColor();

    if (skystoneColor[0] < skystoneColor[1] && skystoneColor[0] < skystoneColor[2]) {
      skystoneNum = 0;

      drive.run(hardwareMap, 0.7, 0);
      drive.run(hardwareMap, .64, 270);
      in.run();
      drive.run(hardwareMap, .7, 0, 0.5);
      wait.waitTime(1.5);
      drive.run(hardwareMap, 1, 90);
      in.stop();
      drive.run(hardwareMap, 4.7, 0);

    } else if (skystoneColor[1] < skystoneColor[0] && skystoneColor[1] < skystoneColor[2]) {
      skystoneNum = 1;

      drive.run(hardwareMap, .64, 270);
      in.run();
      drive.run(hardwareMap, .8, 0, 0.7);
      wait.waitTime(1.5);
      drive.run(hardwareMap, .85, 90);
      in.stop();
      drive.run(hardwareMap, 4.4, 0);

    } else if (skystoneColor[2] < skystoneColor[0] && skystoneColor[2] < skystoneColor[1]) {
      skystoneNum = 2;
      drive.run(hardwareMap, .48, 175);
      drive.run(hardwareMap, .64, 270);
      in.run();
      drive.run(hardwareMap, .69, 0, 0.7);
      wait.waitTime(1.5);
      drive.run(hardwareMap, .92, 90);
      in.stop();
      drive.run(hardwareMap, 4.4, 0);
    }
    drive.run(hardwareMap, 1.3, 180);
    drive.run(hardwareMap, .6, 270);
    claws.down();
    wait.waitTime(.75);
    drive.run(hardwareMap, 1.25, 90);
    turn.run(hardwareMap, 150);
    claws.reset();
    out.close();
    wait.waitTime(.75);
    drive.run(hardwareMap, .42, 90);
    turn.run(hardwareMap, 125);
    drive.run(hardwareMap, .6, 180);
    mid.runMotor();
    out.runWheelsOut();
    wait.waitTime(3);
    out.open();
    out.stopWheels();
    mid.stop();
    drive.run(hardwareMap, 1.0, 270);
    drive.run(hardwareMap, 1.75);
  }
}
