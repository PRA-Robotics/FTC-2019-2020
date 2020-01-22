import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Forward", group = "Autonomous")
public class Forward extends OpMode {
  Intake in;
  Outtake out;

  final public double SQUARE = .598; // 59.8 cm

  DriveInstruction drive1;

  Color c;
  double[] skystoneColor = new double[3];
  int SkystoneNum;

  int q;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);

    drive1 = new DriveInstruction(hardwareMap, 2 * SQUARE, 0);

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
    }
  }
}
