package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "intakeTest")
public class IntakeTest extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        double intakePower = 0.0;
        DcMotorEx intakeMotor;
        CRServo leftIntakeServo = null;
        CRServo rightIntakeServo = null;
        boolean intakeDebounce = false;
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
        leftIntakeServo = hardwareMap.get(CRServo.class, "leftIntakeServo");
        rightIntakeServo = hardwareMap.get(CRServo.class, "rightIntakeServo");
        waitForStart();

        while (opModeIsActive()) {

            if (!gamepad1.a && !gamepad1.x) {
                intakeDebounce = false;
            }
            if (intakePower < 1 && gamepad1.x && !intakeDebounce) {
                intakePower = Math.min(1.0, intakePower + 0.1);
                intakeDebounce = true;
            }
            if (intakePower > 0 && gamepad1.a && !intakeDebounce) {
                intakePower = Math.max(0.0, intakePower - 0.1);
                intakeDebounce = true;
            }
            intakeMotor.setPower(intakePower);
            leftIntakeServo.setPower(intakePower);
            rightIntakeServo.setPower(-intakePower);
            telemetry.addData("Intake Power", intakePower);
            telemetry.update();
        }
    }
}
