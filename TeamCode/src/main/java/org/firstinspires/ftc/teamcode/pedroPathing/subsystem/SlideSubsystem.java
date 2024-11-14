package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideSubsystem extends SubsystemBase {
    private DcMotor slide_motor;

    public SlideSubsystem(HardwareMap hardwareMap){
        // Initializing hardware
        slide_motor = hardwareMap.get(DcMotorEx.class, "slideMotor");
        slide_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        slide_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Encoders ONLY
        // slide_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // slide_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void move_slides(double speed){
        // Moves the slides with encoders
        // slide_motor.setTargetPosition(target_position);
        // slide_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        slide_motor.setPower(speed);
    }

    public void hold_position(){
        // Try to hold the current position
        int current_position = slide_motor.getCurrentPosition();
        slide_motor.setTargetPosition(current_position);
        slide_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide_motor.setPower(1);
    }

    public boolean is_slide_busy(){
        // Returns true if slide is busy, else returns false
        return slide_motor.isBusy();
    }

    public void stop_slide(){
        // Stop moving the slides
        slide_motor.setPower(0);
    }

}
