package org.firstinspires.ftc.teamcode.pedroPathing.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp
public class autoteleop extends LinearOpMode {
    enum Mode {
        DRIVER_MODE,
        AUTO_MODE
    }

    private Mode m;

    @Override
    public void runOpMode(){
        // inits
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        m = Mode.AUTO_MODE;

        waitForStart();

        while(opModeIsActive()){
            switch (m){
                case AUTO_MODE:
                    if (gamepad1.y){
                        m = Mode.DRIVER_MODE;
                    }
                    Actions.runBlocking(
                            drive.actionBuilder(new Pose2d(0, 0, 0))
                                    .lineToX(40)
                                    .lineToX(0)
                                    .build()
                    );
                case DRIVER_MODE:
                    if (gamepad1.b){
                        m = Mode.AUTO_MODE;
                    }

                    drive.setDrivePowers(new PoseVelocity2d(
                            new Vector2d(
                                    -gamepad1.left_stick_y,
                                    -gamepad1.left_stick_x
                            ),
                            -gamepad1.right_stick_x
                    ));
            }
        }
    }

}
