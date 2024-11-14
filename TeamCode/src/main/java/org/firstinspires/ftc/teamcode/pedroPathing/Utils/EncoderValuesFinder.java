package org.firstinspires.ftc.teamcode.pedroPathing.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Encoder Values Finder", group = "Utility")
public class EncoderValuesFinder extends LinearOpMode {

    private DcMotor motor;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "slideMotor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Instructions", "Use game pad to control the motor.");
        telemetry.addData("Press A", "to record the current encoder value.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double power = gamepad1.left_stick_y;
            motor.setPower(power);

            if (gamepad1.a) {
                int encoderValue = motor.getCurrentPosition();
                telemetry.addData("Recorded Encoder Value", encoderValue);
                telemetry.update();
                sleep(500);
            }

            telemetry.addData("Current Encoder Value", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}