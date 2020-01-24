import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Blue Foundation (Linear OpMode)", group = "Autonomous")
public class BlueFoundationLinear extends LinearOpMode {
  LinTurn turn;

  @Override
  public void runOpMode() {
    turn.run(hardwareMap, -180);
    turn.run(hardwareMap, 180);
  }
}
