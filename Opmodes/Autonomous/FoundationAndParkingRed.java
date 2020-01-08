import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Red Foundation and Parking", group = "Autonomous")
public class FoundationAndParkingRed extends OpMode {
  Intake in;
  Outtake out;
  Claws claws;

  final public double SQUARE = .598; // 59.8 cm

  DriveInstruction drive1;
  DriveInstruction drive2;
  DriveInstruction drive3;
  WaitInstruction wait1;

  int q;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    claws = new Claws(hardwareMap);

    drive1 = new DriveInstruction(hardwareMap, 1.5 * SQUARE, 270);
    wait1 = new WaitInstruction(hardwareMap, 2);
    drive2 = new DriveInstruction(hardwareMap, 2 * SQUARE, 90);
    drive3 = new DriveInstruction(hardwareMap, 2 * SQUARE, 180);

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
        claws.down();
        if (wait1.act()) {
          q++;
        }
        break;
      case 2:
        if (drive2.act()) {
          q++;
        }
        break;
      case 3:
        if (drive3.act()) {
          q++;
        }
        break;
    }
  }
}
