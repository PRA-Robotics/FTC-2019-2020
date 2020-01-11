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
  WaitInstruction wait2;

  TurnInstruction turn1;

  TurnInstruction turn2;
  DriveInstruction drive4;

  int q = 0;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    claws = new Claws(hardwareMap);

    drive1 = new DriveInstruction(hardwareMap, 1.38 * SQUARE, 290);
    wait1 = new WaitInstruction(hardwareMap, 1.5);
    drive2 = new DriveInstruction(hardwareMap, 1.5 * SQUARE, 90);
    turn1 = new TurnInstruction(hardwareMap, 140);
    //wait1 = new WaitInstruction(hardwareMap, 1.5);
    drive3 = new DriveInstruction(hardwareMap, 1.75 * SQUARE, 135);
    turn2 = new TurnInstruction(hardwareMap, 45);
    drive4 = new DriveInstruction(hardwareMap, 1.3 * SQUARE);

    //q = 10;
  }

  public void loop() {
    out.open();
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
        if (turn1.act()) {
          q++;
        }
        break;

      case 4:
        claws.reset();
        if (wait1.act()) {
          q++;
        }
        break;

      case 5:
        if (drive3.act()) {
          q++;
        }
        break;

      case 6:
        if(turn2.act()){
          q++;
        }
        break;

      case 7:
        if(drive4.act()){
          q++;
        }
        break;
    }
  }
}
