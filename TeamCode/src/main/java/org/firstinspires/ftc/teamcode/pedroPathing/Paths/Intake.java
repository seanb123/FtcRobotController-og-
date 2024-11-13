package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

//Intake Subsystem
public class Intake {
    private CRServo intake_servo;
    //Constructor
    public Intake(HardwareMap hardwareMap) {
        intake_servo = hardwareMap.get(CRServo.class, "intakeServo");
        intake_servo.setDirection(CRServo.Direction.FORWARD);
    }
    //Actions
    public interface Action {
        boolean run(@NonNull TelemetryPacket packet);
    }
    //Intake
    public class intake implements Action {
        private boolean initialized = false;
        private long startTime;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake_servo.setPower(1);
                startTime = System.currentTimeMillis();
            }

            long elapsedTime = System.currentTimeMillis() - startTime;

            if (elapsedTime < 3000) {
                return true;
            } else {
                intake_servo.setPower(0);
                return false;
            }
        }
    }
    public Action intake(){
        return new intake();
    }

    public class Output implements Action{
        private boolean initialized = false;
        private long startTime;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake_servo.setPower(-1);
                startTime = System.currentTimeMillis();
            }
            long elapsedTime = System.currentTimeMillis() - startTime;

            if (elapsedTime < 3000) {
                return true;
            } else {
                intake_servo.setPower(0);
                return false;
            }
        }
    }
    public Action output(){
        return new Output();
    }
}
