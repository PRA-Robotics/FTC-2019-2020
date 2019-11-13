import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Red Foundation", group = "Autonomous")
public class RedFoundation extends OpMode {
  Intake in;
  Outtake out;

  final public double SQUARE = .598; // 59.8 cm

  DriveInstruction drive1;
  DriveInstruction drive2;
  DriveInstruction drive3;
  DriveInstruction drive4;
  DriveInstruction drive5;

  Color c;
  double[] skystoneColor = new double[3];
  int SkystoneNum;

  int q;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);

    drive1 = new DriveInstruction(hardwareMap, 4 * SQUARE, 0);
    drive2 = new DriveInstruction(hardwareMap, 1.5 * SQUARE, 90);
    drive3 = new DriveInstruction(hardwareMap, 4 * SQUARE, 180);
    drive4 = new DriveInstruction(hardwareMap, 2 * SQUARE, 90);

    c = new Color(hardwareMap);
    SkystoneNum = -1;
    q = 0;
  }

  public void loop() {
    switch(q){
      case 0: // move forward
        if (drive1.act()) {
          q++;
        }
        break;
      case 1: // move forward
        if (drive2.act()) {
          q++;
        }
        break;
      case 2: // move forward
        if (drive3.act()) {
          q++;
        }
        break;
      case 3: // move forward
        if (drive4.act()) {
          q++;
        }
        break;
      case 4: // move forward
        if (drive5.act()) {
          q++;
        }
        break;
    }
  }
}
