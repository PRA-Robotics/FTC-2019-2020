import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Blue Inner", group = "Autonomous")
public class BlueInnerPath extends OpMode {
  Intake in;
  Outtake out;

  final public double SQUARE = .598; // 59.8 cm

  DriveInstruction drive1;
  DriveInstruction drive2;
  DriveInstruction drive3;
  DriveInstruction drive4;
  DriveInstruction drive5;
  DriveInstruction drive6;
  DriveInstruction drive7;
  DriveInstruction drive8;
  DriveInstruction drive9;
  DriveInstruction drive10;
  DriveInstruction drive11;

  TurnInstruction turn1;
  TurnInstruction turn2;

  WaitInstruction wait1;
  WaitInstruction wait2;

  Color c;
  double[] skystoneColor = new double[3];
  int skystoneNum = -1;

  int q;

  public void init() {
    in = new Intake(hardwareMap);
    out = new Outtake(hardwareMap);

    drive1 = new DriveInstruction(hardwareMap, 1.1 * SQUARE, 270);

    turn1 = new TurnInstruction(hardwareMap, -55);

    wait1 = new WaitInstruction(hardwareMap, .4);//Wait for color sensing
    wait2 = new WaitInstruction(hardwareMap, 1.2);//Wait for intake

    drive2 = new DriveInstruction(hardwareMap, .58 * SQUARE, 170);//Left at start
    drive3 = new DriveInstruction(hardwareMap, .66 * SQUARE, 170);//Second left at start

    drive4 = new DriveInstruction(hardwareMap, .62 * SQUARE, 270);//Sideways before intake
    drive5 = new DriveInstruction(hardwareMap, .72 * SQUARE, 0, 0.4);//Forwards for intake
    drive6 = new DriveInstruction(hardwareMap, 1 * SQUARE, 90);//Coming out after intake

    drive7 = new DriveInstruction(hardwareMap, 0.6 * SQUARE, 0);//Sideways if skystone is 0

    drive8 = new DriveInstruction(hardwareMap, 0.38 * SQUARE, 270);
    drive9 = new DriveInstruction(hardwareMap, 0.9 * SQUARE, 0, 0.4);

    drive10 = new DriveInstruction(hardwareMap, 1 * SQUARE, 90);
    turn2 = new TurnInstruction(hardwareMap, -60);

    drive11 = new DriveInstruction(hardwareMap, 3 * SQUARE, 270);


    c = new Color(hardwareMap);
    q = 0;
  }

  public void loop() {
    telemetry.addData("q:", q);
    switch(q){
      case 0: // move forward
        if (drive1.act()) {
          q++;
        }
        break;

      case 1:
        if(wait1.act()){
          skystoneColor[0] = averageColor();
          q++;
        }
        break;

      case 2: // move left to start checking stones
        if(drive2.act()) {
          q++;
        }
        break;

      case 3:
        if(wait1.act()){
          skystoneColor[1] = averageColor();
          q++;
        }
        break;

      case 4: // check first stone
        if (drive3.act()) {
          q++;
        }
        break;

      case 5:
        if(wait1.act()){
          skystoneColor[2] = averageColor();
          q++;
        }
        break;

      case 6:
        if (skystoneColor[0] < skystoneColor[1] && skystoneColor[0] < skystoneColor[2]) {
          skystoneNum = 0;
          q = 15;
        } else if (skystoneColor[1] < skystoneColor[0] && skystoneColor[1] < skystoneColor[2]) {
          skystoneNum = 1;
          q++;
        } else if (skystoneColor[2] < skystoneColor[0] && skystoneColor[2] < skystoneColor[1]) {
          skystoneNum = 2;
          q = 16;
        }

        break;

      case 7:
        if (drive4.act()) {
          q++;
        }
        break;

      case 8:
      in.run();
        if (drive5.act()) {
          q++;
        }
        break;

      case 9:
        if(wait2.act()){
          q ++;
        }
        break;

      case 10:
        in.stop();
        if(drive6.act()){
          q++;
        }
        break;

      case 15:
        if(drive7.act()){
          q = 7;
        }
        break;

      case 16:
        if(turn1.act()){
          q ++;
        }
        break;

      case 17:
        if(drive8.act()){
          q ++;
        }
        break;

      case 18:
        in.run();
        if(drive9.act()){
          q ++;
        }
        break;

      case 19:
        if(wait2.act()){
          q ++;
        }
        break;

      case 20:
        if(drive10.act()){
          q ++;
        }
        break;

      case 21:
        in.stop();
        if(turn2.act()){
          q ++;
        }
        break;

      case 22:
        if(drive11.act()){
          q ++;
        }
        break;
    }
    telemetry.addData("Block0", skystoneColor[0]);
    telemetry.addData("Block1", skystoneColor[1]);
    telemetry.addData("Block2", skystoneColor[2]);
    telemetry.update();
  }

  public double averageColor(){
    double sum = 0;
    for(int i = 0; i < 1; i ++){
      sum += c.getRed();
    }
    return sum / 1;
  }
}
