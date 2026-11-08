package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FakeIntake {
    private DcMotorEx intakeMotor;
    private CRServo leftIntakeServo;
    private CRServo rightIntakeServo;

    private double intakePower = 0.0;
    private boolean intakeDebounce = false;

    public FakeIntake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
        leftIntakeServo = hardwareMap.get(CRServo.class, "leftIntakeServo");
        rightIntakeServo = hardwareMap.get(CRServo.class, "rightIntakeServo");
    }

    public void update(boolean buttonX, boolean buttonA) {
        if (!buttonX && !buttonA) {
            intakeDebounce = false;
        }
        if (intakePower < 1.0 && buttonX && !intakeDebounce) {
            intakePower = Math.min(1.0, intakePower + 0.1);
            intakeDebounce = true;
        }
        if (intakePower > 0.0 && buttonA && !intakeDebounce) {
            intakePower = Math.max(0.0, intakePower - 0.1);
            intakeDebounce = true;
        }

        intakeMotor.setPower(intakePower);
        leftIntakeServo.setPower(intakePower);
        rightIntakeServo.setPower(-intakePower);
    }

    public double getPower() {
        return intakePower;
    }
}
