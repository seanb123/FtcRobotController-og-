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
    }
    //Actions
    public interface Action {
        boolean run(@NonNull TelemetryPacket packet);
    }
    //Intake
    public class intake implements Action{
        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake_servo.setPower(1);
                initialized = true;
            }
            return false;
        }
    }
    public Action intake(){
        return new intake();
    }
    public class Output implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake_servo.setPower(-1);
            return false;
        }
    }
    public Action output(){
        return new Output();
    }
}
