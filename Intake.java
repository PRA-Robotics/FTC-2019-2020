import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {
    private double power;

    Intake() {
        //power = 1.0;
    }

    public void run(DcMotor a, DcMotor b) {
        a.setPower(power);
        b.setPower(power);
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
