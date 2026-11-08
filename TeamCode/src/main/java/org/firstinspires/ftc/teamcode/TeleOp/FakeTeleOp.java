package org.firstinspires.ftc.teamcode.TeleOp;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp(name = "FakeTeleOp")
public class FakeTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        DcMotorEx leftFront, leftRear, rightRear, rightFront;

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");

        leftRear.setDirection(FORWARD);
        leftFront.setDirection(FORWARD);
        rightFront.setDirection(REVERSE);
        rightRear.setDirection(FORWARD);

        leftRear.setZeroPowerBehavior(BRAKE);
        leftFront.setZeroPowerBehavior(BRAKE);
        rightFront.setZeroPowerBehavior(BRAKE);
        rightRear.setZeroPowerBehavior(BRAKE);

        // This initializes your separate intake class
        FakeIntake intake = new FakeIntake(hardwareMap);

        waitForStart();

        while(opModeIsActive()){
            double max;

            double xInput = gamepad1.left_stick_x;
            double yInput = gamepad1.left_stick_y;
            double rotationalInput = gamepad1.right_stick_x;

            double leftFrontPower, leftRearPower, rightFrontPower, rightRearPower;

            leftFrontPower = yInput + xInput + rotationalInput;
            rightFrontPower = yInput - xInput - rotationalInput;
            leftRearPower = yInput - xInput + rotationalInput;
            rightRearPower = yInput + xInput - rotationalInput;

            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftRearPower));
            max = Math.max(max, Math.abs(rightRearPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftRearPower /= max;
                rightRearPower /= max;
            }

            leftFront.setPower(leftFrontPower);
            leftRear.setPower(leftRearPower);
            rightFront.setPower(rightFrontPower);
            rightRear.setPower(rightRearPower); // Fixed the drivetrain typo here

            // This runs the intake code using your buttons
            intake.update(gamepad1.x, gamepad1.a);

            telemetry.addData("xInput yInput", "%4.2f, %4.2f", xInput, yInput);
            telemetry.addData("Front Left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  Left/Right", "%4.2f, %4.2f", leftRearPower, rightRearPower);
            telemetry.addData("Intake Power", intake.getPower());
            telemetry.update();
        }
    }
}
