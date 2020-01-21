import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Blue Foundation (Linear OpMode)", group = "Autonomous")
public class FoundationAndParkingBlue extends LinearOpMode {
  Intake in;
  Outtake out;
  Claws claws;

  LinDrive drive1;
  LinDrive drive2;
  LinDrive drive3;
  LinDrive drive4;

  LinTurn turn1;
  LinTurn turn2;

  Wait wait1;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    claws = new Claws(hardwareMap);

    drive1 = new LinDrive(hardwareMap, 1.53, 250);
    wait1 = new Wait(hardwareMap, .75);
    drive2 = new LinDrive(hardwareMap, 1.5, 90);
    turn1 = new LinTurn(hardwareMap, -185);
    drive3 = new LinDrive(hardwareMap, 1.85, 90);
    turn2 = new LinTurn(hardwareMap, 30);
    drive4 = new LinDrive(hardwareMap, 1, 0);
  }

  public void runOpMode() {
    drive1.run();
    claws.down();
    wait1.run();
    drive2.run();
    turn1.run();
    claws.reset();
    wait1.run();
    drive3.run();
    turn2.run();
    drive4.run();
  }
}
