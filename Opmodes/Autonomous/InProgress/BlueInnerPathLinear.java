import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Blue Inner (Linear OpMode)", group = "Autonomous")
public class BlueInnerPathLinear extends LinearOpMode {
  Intake in;
  Outtake out;
  Claws claws;

  LinDrive drive;

  LinTurn turn;

  Wait wait;

  Color c;

  double[] skystoneColor = new double[3];

  int skystoneNum;

  @Override
  public void runOpMode() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    claws = new Claws(hardwareMap);

    drive = new LinDrive();
    wait = new Wait();
    turn = new LinTurn();

    c = new Color(hardwareMap);

    waitForStart();

    drive.run(hardwareMap, 1.1, 270);
    wait.waitTime(.4);
    skystoneColor[0] = averageColor();

    drive.run(hardwareMap, .6, 167);
    wait.waitTime(.4);
    skystoneColor[1] = averageColor();

    drive.run(hardwareMap, .54, 165);
    wait.waitTime(.4);
    skystoneColor[2] = averageColor();

    if (skystoneColor[0] < skystoneColor[1] && skystoneColor[0] < skystoneColor[2]) {
      skystoneNum = 0;

      drive.run(hardwareMap, 0.8, 0);
      drive.run(hardwareMap, .62, 270);
      in.run();
      drive.run(hardwareMap, .7, 0, 0.5);
      wait.waitTime(1.2);
      in.stop();
      drive.run(hardwareMap, 1, 90);

    } else if (skystoneColor[1] < skystoneColor[0] && skystoneColor[1] < skystoneColor[2]) {
      skystoneNum = 1;

      drive.run(hardwareMap, .62, 270);
      in.run();
      drive.run(hardwareMap, .84, 0, 0.7);
      wait.waitTime(1.2);
      in.stop();
      drive.run(hardwareMap, 1, 90);

    } else if (skystoneColor[2] < skystoneColor[0] && skystoneColor[2] < skystoneColor[1]) {
      skystoneNum = 2;

      turn.run(hardwareMap, -55);
      drive.run(hardwareMap, 0.38, 270);
      in.run();
      drive.run(hardwareMap, 0.8, 0, 0.7);
      wait.waitTime(1);
      drive.run(hardwareMap, 0.8, 120);
      in.stop();
      turn.run(hardwareMap, -60);
      drive.run(hardwareMap, 4.1, 270);
    }
  }

  public double averageColor(){
    double sum = 0;
    for(int i = 0; i < 1; i ++){
      sum += c.getRed();
    }
    return sum / 1;
  }
}
