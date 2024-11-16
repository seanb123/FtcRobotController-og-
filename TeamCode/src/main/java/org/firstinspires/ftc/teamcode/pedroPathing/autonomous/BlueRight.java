package org.firstinspires.ftc.teamcode.pedroPathing.autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
@Autonomous(name = "blue test right", group = "Autonomous")
public class BlueRight extends LinearOpMode {
    private MecanumDrive drive;
    private Pose2d beginPose;
    private DcMotor rotateMotor, slideMotor;
    private CRServo intakeServo;


    // actionBuilder builds from the drive steps passed to it
    @Override
    public void runOpMode() {
        rotateMotor = hardwareMap.get(DcMotorEx.class, "rotateMotor");
        rotateMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotateMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");

        beginPose = new Pose2d(0, 0, 0);
        drive = new MecanumDrive(hardwareMap, beginPose);

        Pose2d initialPose = new Pose2d(-35, -60, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        //1  Block
        Action firstTrackPhase1 = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(48, -58))
                .strafeTo(new Vector2d(48, -35))
                .strafeTo(new Vector2d(-48, -35))
                .strafeTo(new Vector2d(-54, -53))
                .turn(Math.toRadians(135))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(
                        firstTrackPhase1
                )
        );

    }
    // -------------------------Rotate Arm-------------------------
    private class Arm implements Action {
        DcMotor rotateMotor;
        int position;

        public Arm(DcMotor motor, int position){
            this.rotateMotor = motor;
            this.position = position;
        }

        public void goto_position(){
            rotateMotor.setTargetPosition(position);
            rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotateMotor.setPower(0.8);
        }

        public boolean reached_position(){
            return Math.abs(position - rotateMotor.getCurrentPosition()) <= 50;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            goto_position();
            return reached_position();
        }
    }

    // -------------------------Linear Slides-------------------------
    private class Slide implements Action {
        DcMotor slideMotor;
        int position;

        public Slide(DcMotor motor, int position){
            this.slideMotor = motor;
            this.position = position;
        }

        public void goto_position(){
            slideMotor.setTargetPosition(position);
            slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slideMotor.setPower(0.5);
        }

        public boolean reached_position(){
            return Math.abs(position - slideMotor.getCurrentPosition()) <= 50;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            goto_position();
            return reached_position();
        }
    }

    // -------------------------INTAKE-------------------------
    private class Intake implements Action {
        CRServo intakeServo;
        int direction;
        ElapsedTime timer;

        public Intake(CRServo servo, int dir){
            this.intakeServo = servo;
            this.direction = dir;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (timer == null){
                timer = new ElapsedTime();
            }
            intakeServo.setPower(direction == 1 ? 1 : -0.7);

            return timer.seconds() < 1 ? true : false;
        }
    }

}