import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Red Inner", group = "Autonomous")
public class RedInnerPath extends OpMode {
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

  TurnInstruction turn1;
  TurnInstruction turn2;
  TurnInstruction turn3;
  TurnInstruction turn4;
  TurnInstruction turn5;

  int q = 0;

  Color c;
  double[] skystoneColor = new double[3];
  int SkystoneNum;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);

    drive1 = new DriveInstruction(hardwareMap, SQUARE, 180);
    drive2 = new DriveInstruction(hardwareMap, 1.13712 * SQUARE, 275);
    drive3 = new DriveInstruction(hardwareMap, 0.36789 * SQUARE, 180);
    drive4 = new DriveInstruction(hardwareMap, 0.33445 * SQUARE, 180);
    drive5 = new DriveInstruction(hardwareMap, 0.5     * SQUARE, 90);

    turn1 = new TurnInstruction(hardwareMap, 180);

    drive7 = new DriveInstruction(hardwareMap, .75     * SQUARE, 135);

    c = new Color(hardwareMap);
    SkystoneNum = -1;
  }

  public void loop() {
    switch(q){
      case 0:
        if (drive1.act()) {
          q++;
        }
        break;

      case 1:
        if(drive2.act()) {
          q++;
        }
        break;

      case 2:
        skystoneColor[0] = averageColor();
        q++;
        break;

      case 3:
        if(drive3.act()) {
          q++;
        }
        break;

      case 4:
        skystoneColor[1] = averageColor();
        q++;
        break;

      case 5:
        if(drive4.act()) {
          q++;
        }
        break;

      case 6:
        skystoneColor[2] = averageColor();
        q++;
        break;

      case 7:
        if (skystoneColor[0] < skystoneColor[1] && skystoneColor[0] < skystoneColor[2]) {
          SkystoneNum = 0;
          drive6 = new DriveInstruction(hardwareMap, 0.70234 * SQUARE, 0);
        } else if (skystoneColor[1] < skystoneColor[0] && skystoneColor[1] < skystoneColor[2]) {
          SkystoneNum = 1;
          drive6 = new DriveInstruction(hardwareMap, 0.33445 * SQUARE, 0);
        } else if (skystoneColor[2] < skystoneColor[0] && skystoneColor[2] < skystoneColor[1]) {
          SkystoneNum = 2;
          drive6 = new DriveInstruction(hardwareMap, 0 * SQUARE, 0);
        } else {
          SkystoneNum = -1;
          //telemetry.addData("Could not determine");
        }
        //telemetry.addData("SkystoneNum: " + SkystoneNum);
        //telemetry.update();
        q++;
        break;

      case 8:
        if (drive5.act()) {
          q++;
        }
        break;

      case 9:
      if (drive6.act()) {
        q++;
      }
      break;

      case 10:
        if (turn1.act()) {
          q++;
        }
        break;

      case 11:
        if (drive7.act()) {
          q++;
        }
        break;
    }

  }

  public double averageColor(){
    double sum = 0;
    for(int i = 0; i < 10; i ++){
      sum += c.getRed();
    }
    return sum / 10;
  }
}
