import com.qualcomm.robotcore.hardware.*;

public class LinDrive {
  DriveInstruction drive;
  static final double SQUARE = .598;

  public LinDrive() {}

  public void run(HardwareMap hw, double distance, double angle) {
    drive = new DriveInstruction(hw, distance * SQUARE, angle);
    while (!drive.act()) {}
  }

  public void run(HardwareMap hw, double distance, double angle, double speed) {
    drive = new DriveInstruction(hw, distance * SQUARE, angle, speed);
    while (!drive.act()) {}
  }

  public void run(HardwareMap hw, double distance) {
    drive = new DriveInstruction(hw, distance * SQUARE, 0);
    while (!drive.act()) {}
  }
}
