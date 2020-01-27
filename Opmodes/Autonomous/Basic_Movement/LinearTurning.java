import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Linear Turning", group = "Autonomous")
public class LinearTurning extends LinearOpMode {
  LinTurn turn;
  Wait wait;

  @Override
  public void runOpMode() {
    turn = new LinTurn();
    wait = new Wait();
    waitForStart();
    turn.run(hardwareMap, -180);
    wait.waitTime(1);
    turn.run(hardwareMap, 180);
  }
}
