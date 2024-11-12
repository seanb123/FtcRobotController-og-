package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Encoder Values Finder", group = "Utility")
public class EncoderValuesFinder extends LinearOpMode {

    private DcMotor motor;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "yourMotorName"); // Replace with your motor name
        motor.setDirection(DcMotorSimple.Direction.FORWARD); // Set motor direction if needed
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Start without encoder for manual control

        telemetry.addData("Instructions", "Use gamepad to control the motor.");
        telemetry.addData("Press A", "to record the current encoder value.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Manual motor control
            double power = gamepad1.left_stick_y;
            motor.setPower(power);

            // Record encoder value
            if (gamepad1.a) {
                int encoderValue = motor.getCurrentPosition();
                telemetry.addData("Recorded Encoder Value", encoderValue);
                telemetry.update();
                sleep(500); // Short delay to avoid multiple recordings
            }

            telemetry.addData("Current Encoder Value", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}