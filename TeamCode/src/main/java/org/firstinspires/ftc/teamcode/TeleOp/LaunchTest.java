package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "launchTest")
public class LaunchTest extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        double launchPower = 0.0;
        DcMotorEx launchMotor;
        launchMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");
        boolean stickDebounce = false;
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.left_stick_y > 0 && launchPower < 1) {
                launchPower += 0.1;
            } else if (gamepad2.left_stick_y < 0 && launchPower > 0) {
                launchPower -= 0.1;
            }
            launchMotor.setPower(launchPower);
        }
    }
}
