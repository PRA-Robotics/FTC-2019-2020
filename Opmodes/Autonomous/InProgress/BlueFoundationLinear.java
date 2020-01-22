import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Blue Foundation (Linear OpMode)", group = "Autonomous")
public class FoundationAndParkingBlue extends LinearOpMode {
  Intake in;
  Outtake out;
  Claws claws;

  LinDrive drive;

  LinTurn turn;

  Wait wait;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    claws = new Claws(hardwareMap);

    drive = new LinDrive();
    wait = new Wait();
    turn = new LinTurn();
  }

  @Override
  public void runOpMode() {
    drive.run(hardwareMap, 1.53, 250);
    claws.down();
    wait.run(hardwareMap, .75);
    drive.run(hardwareMap, 1.5, 90);
    turn.run(hardwareMap, -185);
    claws.reset();
    wait.run(hardwareMap, .75);
    drive.run(hardwareMap, 1.85, 90);
    turn.run(hardwareMap, 30);
    drive.run(hardwareMap, 1, 0);
  }
}
