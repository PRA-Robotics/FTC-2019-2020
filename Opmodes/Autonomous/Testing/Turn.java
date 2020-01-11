import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Turn", group = "Autonomous")
public class Turn extends OpMode {
  TurnInstruction turn1;
  Outtake out;
  int q;

  public void init() {
    out = new Outtake(hardwareMap);
    turn1 = new TurnInstruction(hardwareMap, 2*180);


    q = 0; // add a multiplier to the speed on turn
    // get rid of req for stop within certain error
  }

  public void loop() {
    out.open();
    switch(q){
      case 0: // move forward
        if (turn1.act()) {
          q++;
        }
        break;
    }
    telemetry.addData("frontRight", turn1.give());
    telemetry.update();
  }
}
