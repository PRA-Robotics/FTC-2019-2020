import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Blue Inner (Linear OpMode)", group = "Autonomous")
public class FoundationAndParkingBlue extends LinearOpMode {
  Intake in;
  Outtake out;
  Claws claws;

  LinDrive drive;

  LinTurn turn;

  Wait wait;

  Color c;

  double[] skystoneColor = new double[3];

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    claws = new Claws(hardwareMap);

    drive = new LinDrive();
    wait = new Wait();
    turn = new LinTurn();

    c = new Color(hardwareMap);
  }

  @Override
  public void runOpMode() {
    drive.run(hardwareMap, 1.1, 270);
    wait.run(hardwareMap, .4);
    skystoneColor[0] = averageColor();

    drive.run(hardwareMap, .58, 170);
    wait.run(hardwareMap, .4);
    skystoneColor[1] = averageColor();

    drive.run(hardwareMap, .66, 170);
    wait.run(hardwareMap, .4);
    skystoneColor[2] = averageColor();

    if (skystoneColor[0] < skystoneColor[1] && skystoneColor[0] < skystoneColor[2]) {
      skystoneNum = 0;

      drive.run(hardwareMap, 0.6, 0);
      drive.run(hardwareMap, .62, 270);
      in.run();
      drive.run(hardwareMap, .72, 0, 0.4);
      wait.run(hardwareMap, 1.2);
      in.stop();
      drive.run(hardwareMap, 1, 90);

    } else if (skystoneColor[1] < skystoneColor[0] && skystoneColor[1] < skystoneColor[2]) {
      skystoneNum = 1;

      drive.run(hardwareMap, .62, 270);
      in.run();
      drive.run(hardwareMap, .72, 0, 0.4);
      wait.run(hardwareMap, 1.2);
      in.stop();
      drive.run(hardwareMap, 1, 90);

    } else if (skystoneColor[2] < skystoneColor[0] && skystoneColor[2] < skystoneColor[1]) {
      skystoneNum = 2;

      turn.run(hardwareMap, -55);
      drive.run(hardwareMap, 0.38, 270);
      in.run();
      drive.run(hardwareMap, 0.9, 0, 0.4);
      wait.run(hardwareMap, 1.2);
      drive.run(hardwareMap, 1, 90);
      turn.run(hardwareMap, -60);
      drive.run(hardwareMap, 3, 270);
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
