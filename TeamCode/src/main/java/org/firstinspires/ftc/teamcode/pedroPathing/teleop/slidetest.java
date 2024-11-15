package org.firstinspires.ftc.teamcode.pedroPathing.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class slidetest extends LinearOpMode {
    private DcMotor slide_motor;

    @Override
    public void runOpMode() throws InterruptedException {
        slide_motor = hardwareMap.get(DcMotor.class, "rotateMotor");

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("motor pos: ", slide_motor.getCurrentPosition());
            telemetry.update();
        }
    }

}
