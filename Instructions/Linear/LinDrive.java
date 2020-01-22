import com.qualcomm.robotcore.hardware.*;

public class LinDrive {
  DriveInstruction drive;
  static final double SQUARE = .598;

  /*
  public LinDrive(HardwareMap hw, double distance, double angle) {
    drive = new DriveInstruction(hw, distance * SQUARE, angle);
  }

  public LinDrive(HardwareMap hw, double distance, double angle, double speed) {
    drive = new DriveInstruction(hw, distance * SQUARE, angle, speed);
  }
  */

  public void run(HardwareMap hw, double distance, double angle) {
    drive = new DriveInstruction(hw, distance * SQUARE, angle);
    while (!drive.act()) {}
  }

  public void run(HardwareMap hw, double distance, double angle, double speed) {
    drive = new DriveInstruction(hw, distance * SQUARE, angle, speed);
    while (!drive.act()) {}
  }
}
