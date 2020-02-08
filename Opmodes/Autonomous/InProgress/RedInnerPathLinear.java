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
    | _180__          ()       ()                 |
    |(  |  )270       ()       ()                 |
    |() v (*)         (*)     (*)                 |
    |   0                                         |
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
    wait.waitTime(.1);
    skystoneColor[0] = c.averageColor();

    drive.run(hardwareMap, .39, 174);
    wait.waitTime(.1);
    skystoneColor[1] = c.averageColor();

    drive.run(hardwareMap, .32, 174);
    wait.waitTime(.1);
    skystoneColor[2] = c.averageColor();

    if (skystoneColor[0] < skystoneColor[1] && skystoneColor[0] < skystoneColor[2]) {
      skystoneNum = 0;

      drive.run(hardwareMap, 0.44, 0);
      drive.run(hardwareMap, .48, 270);
      in.run();
      drive.run(hardwareMap, .46, 0, 0.5);
      wait.waitTime(.65);
      drive.run(hardwareMap, .61, 90);
      in.stop();
      drive.run(hardwareMap, 2.87, 0);

    } else if (skystoneColor[1] < skystoneColor[0] && skystoneColor[1] < skystoneColor[2]) {
      skystoneNum = 1;

      drive.run(hardwareMap, .48, 270);
      in.run();
      drive.run(hardwareMap, .57, 0, 0.7);
      wait.waitTime(.8);
      drive.run(hardwareMap, .65, 90);
      in.stop();
      drive.run(hardwareMap, 3.3, 0);

    } else if (skystoneColor[2] < skystoneColor[0] && skystoneColor[2] < skystoneColor[1]) {
      skystoneNum = 0;

      drive.run(hardwareMap, 0.25, 177);
      drive.run(hardwareMap, .48, 270);
      in.run();
      drive.run(hardwareMap, .5, 0, 0.5);
      wait.waitTime(.65);
      drive.run(hardwareMap, .65, 90);
      in.stop();
      drive.run(hardwareMap, 3.7, 0);
    }
    drive.run(hardwareMap, .5, 270);
    claws.down();
    wait.waitTime(.4);
    drive.run(hardwareMap, 1, 90);
    turn.run(hardwareMap, 145);
    claws.reset();
    out.close();
    wait.waitTime(.4);
    drive.run(hardwareMap, .42, 90);
    turn.run(hardwareMap, 92);
    drive.run(hardwareMap, .6, 180);
    mid.runMotor();
    out.runWheelsOut();
    wait.waitTime(2.5);
    out.open();
    out.stopWheels();
    mid.stop();
    drive.run(hardwareMap, 0.6, 90);
    drive.run(hardwareMap, 1.75);
  }
}
