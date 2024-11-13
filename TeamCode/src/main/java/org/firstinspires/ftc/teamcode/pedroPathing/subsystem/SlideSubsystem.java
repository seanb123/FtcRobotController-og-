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
        slide_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        slide_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void move_slides(double speed){
        slide_motor.setPower(speed);
    }

    public void goto_position(){
        slide_motor.setTargetPosition(1700);
        slide_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide_motor.setPower(1);
    }

    public boolean reached_position(){
        return Math.abs(1700 - slide_motor.getCurrentPosition()) <= 50;
    }

    public double get_position(){
        return slide_motor.getCurrentPosition();
    }

    public void stop_using_encoder(){
        slide_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stop_slide(){
        // Stop moving the slides
        slide_motor.setPower(0);
    }

}
