package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    private DcMotorEx armMotor;

    public Arm(HardwareMap hardwareMap) {
        armMotor = hardwareMap.get(DcMotorEx.class, "liftMotor"); // Assuming "liftMotor" is the name of your arm motor
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public interface Action {
        boolean run(@NonNull TelemetryPacket packet);
    }

    public class LiftUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                armMotor.setTargetPosition(3000);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(0.8);
                initialized = true;
            }

            double pos = armMotor.getCurrentPosition();
            packet.put("liftPos", pos);

            if (armMotor.isBusy()) {
                return true; // Continue running until target is reached
            } else {
                armMotor.setPower(0.2); // Hold position with a lower power
                return false; // Stop running the action
            }
        }
    }

    public Action liftUp() {
        return new LiftUp();
    }

    public class LiftDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                armMotor.setPower(-0.8);
                initialized = true;
            }

            double pos = armMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos > 100.0) {
                return true;
            } else {
                armMotor.setPower(0);
                return false;
            }
        }
    }

    public Action liftDown() {
        return new LiftDown();
    }
}