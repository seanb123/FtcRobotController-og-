package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RotateSlideSubsystem extends SubsystemBase {
    private DcMotor rotate_motor;
    private PIDController controller;
    private double p, i, d, f;
    private double ticks_in_degree = 537.7 / 360;


    public RotateSlideSubsystem(HardwareMap hardwareMap){
        rotate_motor = hardwareMap.get(DcMotorEx.class, "rotateMotor");
        rotate_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rotate_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        controller = new PIDController(p, i, d);
    }

    public void rotate_slides(int target_position, double speed){
        rotate_motor.setTargetPosition(target_position);
        rotate_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotate_motor.setPower(speed);
    }

    public void hold_position(){
        // Try to hold the current position
        int current_position = rotate_motor.getCurrentPosition();
        rotate_motor.setTargetPosition(current_position);
        rotate_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotate_motor.setPower(1);
    }

    public void rotate_slide(double target){
        double arm_pos = rotate_motor.getCurrentPosition();
        double pid = controller.calculate(arm_pos, target);

        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;
        double power = pid + ff;
        rotate_motor.setPower(power);
    }

    public boolean is_at_target(double target){
        return controller.atSetPoint();
    }

    public boolean is_slide_busy(){
        // Returns true if slide is busy, else returns false
        return rotate_motor.isBusy();
    }
    public void stop_rotating(){
        rotate_motor.setPower(0);
    }
}
