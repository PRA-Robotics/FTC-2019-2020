import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Blue Inner", group = "Autonomous")
public class BlueInnerPath extends OpMode {
  Intake in;
  Outtake out;

  final public double SQUARE = .598; // 59.8 cm

  DriveInstruction drive1;
  DriveInstruction drive2;
  DriveInstruction drive3;
  DriveInstruction drive4;
  DriveInstruction drive5;
  DriveInstruction drive6;
  DriveInstruction drive7;
  DriveInstruction drive8;
  DriveInstruction drive9;
  DriveInstruction drive10;
  DriveInstruction drive11;

  TurnInstruction turn1;

  WaitInstruction wait1;

  Color c;
  double[] skystoneColor = new double[3];
  int skystoneNum = -1;

  int q;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);

    drive1 = new DriveInstruction(hardwareMap, 1.1 * SQUARE, 270);

    wait1 = new WaitInstruction(hardwareMap, .3);

    drive2 = new DriveInstruction(hardwareMap, .6 * SQUARE, 170);
    drive3 = new DriveInstruction(hardwareMap, .6 * SQUARE, 170);

    drive4 = new DriveInstruction(hardwareMap, .71 * SQUARE, 270);
    drive5 = new DriveInstruction(hardwareMap, .9 * SQUARE, 0, 0.33);

    c = new Color(hardwareMap);
    q = 0;
  }

  public void loop() {
    telemetry.addData("q:", q);
    switch(q){
      case 0: // move forward
        if (drive1.act()) {
          q++;
        }
        break;

      case 1:
        if(wait1.act()){
          skystoneColor[0] = averageColor();
          q++;
        }
        break;

      case 2: // move left to start checking stones
        if(drive2.act()) {
          q++;
        }
        break;

      case 3:
        if(wait1.act()){
          skystoneColor[1] = averageColor();
          q++;
        }
        break;

      case 4: // check first stone
        if (drive3.act()) {
          q++;
        }
        break;

      case 5:
        if(wait1.act()){
          skystoneColor[2] = averageColor();
          q++;
        }
        break;

      case 6:
        if (skystoneColor[0] < skystoneColor[1] && skystoneColor[0] < skystoneColor[2]) {
          skystoneNum = 0;
        } else if (skystoneColor[1] < skystoneColor[0] && skystoneColor[1] < skystoneColor[2]) {
          skystoneNum = 1;
          q++;
        } else if (skystoneColor[2] < skystoneColor[0] && skystoneColor[2] < skystoneColor[1]) {
          skystoneNum = 2;
        }

        break;

      case 7:
        if (drive4.act()) {
          q++;
        }
        break;

      case 8:
      in.run();
        if (drive5.act()) {
          q++;
        }
        break;
    }
    telemetry.addData("Block", skystoneNum);
    telemetry.addData("Block0", skystoneColor[0]);
    telemetry.addData("Block1", skystoneColor[1]);
    telemetry.addData("Block2", skystoneColor[2]);
    telemetry.update();
  }

  public double averageColor(){
    double sum = 0;
    for(int i = 0; i < 10; i ++){
      sum += c.getRed();
    }
    return sum / 10;
  }
}
