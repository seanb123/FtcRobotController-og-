package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {
    private DcMotor slideMotor;
    public Slide(HardwareMap hardwareMap) {
        slideMotor = hardwareMap.get(DcMotor.class, "slideMotor");
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public interface Action {
        boolean run(@NonNull TelemetryPacket packet);
    }
    public class extendSlide implements Action{
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            if (!initialized) {
                slideMotor.setTargetPosition(3000);
                slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideMotor.setPower(0.8);
                initialized = true;
            }
            double pos = slideMotor.getCurrentPosition();
            packet.put("slidePos", pos);

            if (slideMotor.isBusy()) {
                return true; // Continue running until target is reached
            } else {
                slideMotor.setPower(0.2); // Hold position with a lower power
                return false; // Stop running the action

            }
        }
    }
    public Action extend_slide(){
        return new extendSlide();
    }
    public class closeSlide implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slideMotor.setTargetPosition(0); // Set target position to0 (closed)
                slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideMotor.setPower(0.8); // Set power for closing
                initialized = true;
            }

            double pos = slideMotor.getCurrentPosition();
            packet.put("slidePos", pos);

            if (slideMotor.isBusy()) {
                return true; // Continue running until target is reached
            } else {
                slideMotor.setPower(0); // Stop the motor when target is reached
                return false; // Stop running the action
            }}
    }
    public Action close_slide(){
        return new closeSlide();
    }
}
