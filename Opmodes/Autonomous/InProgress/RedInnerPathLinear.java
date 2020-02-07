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

    drive.run(hardwareMap, 1.12, 270);
    wait.waitTime(.15);
    skystoneColor[0] = c.averageColor();

    drive.run(hardwareMap, .42, 174);
    wait.waitTime(.15);
    skystoneColor[1] = c.averageColor();

    drive.run(hardwareMap, .32, 174);
    wait.waitTime(.15);
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

      drive.run(hardwareMap, .48, 270);
      in.run();
      drive.run(hardwareMap, .57, 0, 0.7);
      wait.waitTime(1);
      drive.run(hardwareMap, .77, 90);
      in.stop();
      drive.run(hardwareMap, 2.85, 0);

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
    drive.run(hardwareMap, .54, 270);
    claws.down();
    wait.waitTime(.5);
    drive.run(hardwareMap, 1, 90);
    turn.run(hardwareMap, 140);
    claws.reset();
    out.close();
    wait.waitTime(.2);
    drive.run(hardwareMap, .42, 90);
    turn.run(hardwareMap, 125);
    drive.run(hardwareMap, .6, 180);
    mid.runMotor();
    out.runWheelsOut();
    wait.waitTime(3);
    out.open();
    out.stopWheels();
    mid.stop();
    drive.run(hardwareMap, 0.75, 270);
    drive.run(hardwareMap, 1.75);
  }
}
