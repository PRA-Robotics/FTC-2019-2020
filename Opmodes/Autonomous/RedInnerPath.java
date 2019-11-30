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
  DriveInstruction drive8;
  DriveInstruction drive9;
  DriveInstruction drive10;
  DriveInstruction drive11;

  TurnInstruction turn1;
  TurnInstruction turn2;

  WaitInstruction wait1;

  Color c;
  double[] skystoneColor = new double[3];
  int SkystoneNum;

  int q;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);

    drive1 = new DriveInstruction(hardwareMap, 1.15 * SQUARE, 270);
    drive2 = new DriveInstruction(hardwareMap, 0.75     * SQUARE, 180);
    drive3 = new DriveInstruction(hardwareMap, 0.6 * SQUARE, 180);
    drive4 = new DriveInstruction(hardwareMap, 0.6 * SQUARE, 180);
    drive5 = new DriveInstruction(hardwareMap, 0.2     * SQUARE, 90);

    turn1 = new TurnInstruction(hardwareMap, 180);

    drive7 = new DriveInstruction(hardwareMap, .5     * SQUARE, 90);
    drive8 = new DriveInstruction(hardwareMap, .75     * SQUARE);
    drive9 = new DriveInstruction(hardwareMap, 1.0     * SQUARE, 270);
    drive10 = new DriveInstruction(hardwareMap, 3.0    * SQUARE, 180);

    turn2 = new TurnInstruction(hardwareMap, 90);

    wait1 = new WaitInstruction(hardwareMap, 2);

    drive11 = new DriveInstruction(hardwareMap, 2.0    * SQUARE, 270);

    c = new Color(hardwareMap);
    SkystoneNum = -1;
    q = 1;
  }

  public void loop() {

    switch(q){
      case 1: // move forward
        if (drive1.act()) {
          q++;
        }
        break;

      case 2: // move left to start checking stones
        if(drive2.act()) {
          q++;
        }
        break;

      case 3: // check first stone
        skystoneColor[0] = averageColor();
        q++;
        break;

      case 4: // move left
        if(drive3.act()) {
          q++;
        }
        break;

      case 5: // check second stone
        skystoneColor[1] = averageColor();
        q++;
        break;

      case 6: // move left
        if(drive4.act()) {
          q++;
        }
        break;

      case 7: // check third stone
        skystoneColor[2] = averageColor();
        q++;
        break;

      case 8: // sets drive6 to a variable distance depending on which stone is skystone
        if ((skystoneColor[0] < skystoneColor[1]) && (skystoneColor[0] < skystoneColor[2])) {
          SkystoneNum = 0;
        } else if ((skystoneColor[1] < skystoneColor[0]) && (skystoneColor[1] < skystoneColor[2])) {
          SkystoneNum = 1;
        } else if ((skystoneColor[2] < skystoneColor[0]) && (skystoneColor[2] < skystoneColor[1])) {
          SkystoneNum = 2;
        } else {
          SkystoneNum = -1;
        }
        q++;
        break;

      case 9: // back up
        if (drive5.act()) {
          q++;
        }
        break;

      case 10:
        if (SkystoneNum == 0) {
          drive6 = new DriveInstruction(hardwareMap, 1.3 * SQUARE);
          turn1 = new TurnInstruction(hardwareMap, 135);
          drive7 = new DriveInstruction(hardwareMap, 135);
        } else if (SkystoneNum == 1) {
          drive6 = new DriveInstruction(hardwareMap, 0.8 * SQUARE);
        } else if (SkystoneNum == 2) {
          drive6 = new DriveInstruction(hardwareMap, 0.5 * SQUARE);
        } else {
          drive6 = new DriveInstruction(hardwareMap, 0);
          q = -1;
        }
        q++;
        break;

      case 11: // move the variable distance
      if (drive6.act()) {
        q++;
      }
      break;

      case 12: // rotate so we can get the block
        if (turn1.act()) {
          q++;
        }
        break;

      case 13: // move to intercept skystone
        if (drive7.act()) {
          q++;
        }
        break;

      case 14: // grab stone
        in.run();
        if (drive8.act()) {
          q++;
        }
        break;

      case 15: // stop intake, get outtake in position
        in.stop();
        out.close();
        q++;
        break;

      case 16: // move back to switch over
        if (drive9.act()) {
          q++;
        }
        break;

      case 17: // cross under bridge
        if (drive10.act()) {
          q++;
        }
        break;

      case 18: // turn to be in position to push stone out
        if (turn2.act()) {
          q++;
        }
        break;

      case 19: // push skystone out
        out.runWheelsOut();
        if (wait1.act()) {
          q++;
        }
        break;

      case 20: // stop wheels, open outtake to not hit anything while going back
        out.stopWheels();
        out.open();
        q++;
        break;

      case 21: // move back under bridge
        if (drive11.act()) {
          q++;
        }
        break;

      default:
        telemetry.addData("Out of bounds, step", q);
    }

    telemetry.addData("step", q);
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
