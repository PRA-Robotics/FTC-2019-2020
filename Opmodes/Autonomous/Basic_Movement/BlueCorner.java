import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Blue Corner", group = "Autonomous")
public class BlueCorner extends OpMode {

  final public double SQUARE = .598; // 59.8 cm

  DriveInstruction drive1;
  DriveInstruction drive2;

  int q;

  public void init() {
    drive1 = new DriveInstruction(hardwareMap, 1 * SQUARE, 90);
    drive2 = new DriveInstruction(hardwareMap, 1 * SQUARE);

    q = 0;
  }

  public void loop() {
    switch(q){
      case 0: // move forward
        if (drive1.act()) {
          q++;
        }
        break;

      case 1:
        if (drive2.act()) {
          q++;
        }
        break;
    }
  }
}
