package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class MotorSpinTest extends LinearOpMode {


    public void runOpMode() throws InterruptedException {

        DcMotorEx motor;
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(opModeIsActive()) {
            double motorSpeed = gamepad1.left_stick_y;

            motor.setPower(motorSpeed*.6);
            telemetry.addData("Motor Power",motor.getPower());
            telemetry.update();
        }
    }
}
