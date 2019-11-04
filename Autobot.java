import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autobot", group = "Autonomous")
public class Autobot extends OpMode{
  Intake intake;
  DriveInstruction drive;
  TurnInstruction turn;
  int q;
  public void init(){
    intake = new Intake(hardwareMap);
    drive = new DriveInstruction(hardwareMap, 1, 20);
    turn = new TurnInstruction(hardwareMap, 1080);
  }

  public void loop(){
    if(drive.act()){
      //turn.act();
    }
  }
}
