package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem extends SubsystemBase {
    private CRServo intake_servo;

    public IntakeSubsystem(HardwareMap hardwareMap){
        intake_servo = hardwareMap.get(CRServo.class, "intakeServo");
    }

    public void move_intake(boolean direction){
        intake_servo.setPower(direction ? 1 : -1);
    }

    public void move_intake_slow(boolean direction_slow){
        intake_servo.setPower(direction_slow ? 0.5 : -0.5);
    }

    public void stop_intake(){
        intake_servo.setPower(0);
    }
}
