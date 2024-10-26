package org.firstinspires.ftc.teamcode.pedroPathing.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class encodertest extends LinearOpMode {
    private DcMotor leftFront, rightFront, leftBack, rightBack;

    @Override
    public void runOpMode(){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.a){
                leftFront.setPower(1);
            } else{
                leftFront.setPower(0);
            }
            if(gamepad1.b){
                rightFront.setPower(1);
            } else{
                rightFront.setPower(0);
            }
            if(gamepad1.x){
                leftBack.setPower(1);
            } else{
                leftBack.setPower(0);
            }
            if(gamepad1.y){
                rightBack.setPower(1);
            }else {
                rightBack.setPower(0);
            }

            telemetry.addData("Left front: ", leftFront.getCurrentPosition());
            telemetry.addData("Right front: ", rightFront.getCurrentPosition());
            telemetry.addData("Left Back: ", leftBack.getCurrentPosition());
            telemetry.addData("Right Back: ", rightBack.getCurrentPosition());
            telemetry.update();
        }
    }
}
