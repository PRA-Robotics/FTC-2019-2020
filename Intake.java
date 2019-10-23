import com.qualcomm.robotcore.hardware.*;

public class Intake {
    private double power;
    DcMotor IntakeRight;
    DcMotor IntakeLeft;

    Intake(DcMotor a, DcMotor b) {
        power = 1.0;
        IntakeLeft = a;
        IntakeRight = b;
    }

    Intake(HardwareMap hw) {
      IntakeRight = hw.get(DcMotor.class, "irDrive");
      IntakeLeft = hw.get(DcMotor.class, "ilDrive");;
    }

    public void run() {
        IntakeLeft.setPower(power);
        IntakeRight.setPower(power);
        //irDrive.setPower(power);
        //ilDrive.setPower(power);
    }

    public double getPower() {
        return this.power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
