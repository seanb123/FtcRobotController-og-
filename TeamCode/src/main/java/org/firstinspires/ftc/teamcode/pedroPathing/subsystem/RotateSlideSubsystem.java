package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RotateSlideSubsystem extends SubsystemBase {
    private DcMotor rotate_motor;


    public RotateSlideSubsystem(HardwareMap hardwareMap){
        rotate_motor = hardwareMap.get(DcMotorEx.class, "rotateMotor");
        rotate_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rotate_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void rotate_slides(double speed){
        rotate_motor.setPower(speed);
    }

    public void climb(){
        rotate_motor.setTargetPosition(3000);
        rotate_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotate_motor.setPower(1);
    }

    public double get_position(){
        return rotate_motor.getCurrentPosition();
    }

    public void stop_rotating(){
        rotate_motor.setPower(0);
    }
}
