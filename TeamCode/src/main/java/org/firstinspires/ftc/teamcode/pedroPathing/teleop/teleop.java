package org.firstinspires.ftc.teamcode.pedroPathing.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class teleop extends LinearOpMode {
    public DcMotor slide_motor;

    @Override
    public void runOpMode(){
        slide_motor = hardwareMap.get(DcMotor.class, "sildeMotor"); //TODO: Change the name on driver hub
        slide_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        slide_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while(!isStopRequested()){

        }
    }
}
