import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Turn", group = "Autonomous")
public class Turn extends OpMode {
  TurnInstruction turn1;
  TurnInstruction turn2;
  Outtake out;
  int q;

  public void init() {
    out = new Outtake(hardwareMap);
    turn1 = new TurnInstruction(hardwareMap, -90);
    turn2 = new TurnInstruction(hardwareMap, 90);


    q = 0;
  }

  public void loop() {
    out.open();
    switch(q){
      case 0:
        if (turn1.act()) {
          q++;
        }
        break;
      case 1:
        if (turn2.act()) {
          q++;
        }
        break;
    }
    telemetry.addData("frontRight", turn1.give());
    telemetry.update();
  }
}
