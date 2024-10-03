package org.firstinspires.ftc.teamcode.pedroPathing.teleop;
//Branch test
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.leftFrontMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.leftRearMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.rightFrontMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.rightRearMotorName;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
//I plan on modifying this code. To add more functionalities
/*
    TODO Add a button to make the motors preform specific tasks like extend arm and collect the samples. Rather then doing two actions A button Second controller
    TODO Extend arms and shoot the samples B button Second controller
    TODO See if i can refine the Teleop code to make it more accurate.
*/
@TeleOp
public class teleop extends LinearOpMode {
    private Follower follower;

    private DcMotor slide_motor;
    private DcMotorEx leftFront;
    private DcMotorEx leftBack;
    private DcMotorEx rightFront;
    private DcMotorEx rightBack;

    @Override
    public void runOpMode(){
        slide_motor = hardwareMap.get(DcMotor.class, "slideMotor"); //TODO: Change the name on driver hub
        slide_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        slide_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront = hardwareMap.get(DcMotorEx.class, leftFrontMotorName);
        leftBack = hardwareMap.get(DcMotorEx.class, leftRearMotorName);
        rightBack = hardwareMap.get(DcMotorEx.class, rightRearMotorName);
        rightFront = hardwareMap.get(DcMotorEx.class, rightFrontMotorName);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        follower.startTeleopDrive();

        waitForStart();

        while(!isStopRequested()){
            follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
            follower.update();

            if (gamepad1.a) {
                move_slide(3000, 1);
            } else if(gamepad1.b){
                move_slide(0, 1);
            }
        }
    }

    public void move_slide(int ticks, double speed){
         slide_motor.setTargetPosition(ticks);
         slide_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
         slide_motor.setPower(speed);

         while(opModeIsActive() && slide_motor.isBusy()){
             telemetry.addData("Moving to", " %7d", ticks);
             telemetry.addData("Currently at", "at %7d", slide_motor.getCurrentPosition());
             telemetry.update();
         }

         slide_motor.setPower(0);
         slide_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
