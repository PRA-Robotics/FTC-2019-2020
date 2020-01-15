import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Red Corner", group = "Autonomous")
public class RedCorner extends OpMode {
  final public double SQUARE = .598; // 59.8 cm

  DriveInstruction drive1;
  DriveInstruction drive2;

  int q;

  public void init() {
    drive1 = new DriveInstruction(hardwareMap, 1 * SQUARE, 270);
    drive2 = new DriveInstruction(hardwareMap, 0.5 * SQUARE, 0);
  }

  public void loop() {
    switch(q){
      case 0: // move forward
        if (drive1.act()) {
          q++;
        }
        break;
      case 2:
        if (drive2.act()) {
          q++;
        }
        break;
    }
  }
}
