import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Turn", group = "Autonomous")
public class Turn extends OpMode {
  TurnInstruction turn1;

  int q;

  public void init() {

    turn1 = new TurnInstruction(hardwareMap, 2*180);

    q = 0; // add a multiplier to the speed on turn
    // get rid of req for stop within certain error
  }

  public void loop() {
    switch(q){
      case 0: // move forward
        if (turn1.act()) {
          q++;
        }
        break;
    }
  }
}
