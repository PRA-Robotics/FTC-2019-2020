import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Autobot", group = "Autonomous")
public class Autobot extends OpMode{
  Intake in;
  Outtake out;
  DriveInstruction drive1;
  DriveInstruction drive2;
  DriveInstruction drive3;
  TurnInstruction turn;
  int q = 0;
  Color c;
  double[] skystoneColor = new double[3];
  public void init(){
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);
    drive1 = new DriveInstruction(hardwareMap, .22, 180);
    drive2 = new DriveInstruction(hardwareMap, .2, 180);
    drive3 = new DriveInstruction(hardwareMap, .68, 275);
    turn = new TurnInstruction(hardwareMap, 1080);
    c = new Color(hardwareMap);
  }

  public void loop(){
    switch(q){
      case 0:
        if(drive3.act()){
          q ++;
        }
        break;
      case 1:
        skystoneColor[0] = averageColor();
        q++;
        break;

      case 2:
        if(drive1.act()){
          q ++;
        }
        break;

      case 3:
        skystoneColor[1] = averageColor();
        q++;
        break;

      case 4:
        if(drive2.act()){
          q ++;
        }
        break;

      case 5:
        skystoneColor[2] = averageColor();
        q++;
        break;

      case 6:
        telemetry.addData("1:", skystoneColor[0]);
        telemetry.addData("2:", skystoneColor[1]);
        telemetry.addData("3:", skystoneColor[2]);
        telemetry.update();
    }
  }

  public double averageColor(){
    double sum = 0;
    for(int i = 0; i < 10; i ++){
      sum += c.getRed();
    }
    return sum / 10;
  }
}
