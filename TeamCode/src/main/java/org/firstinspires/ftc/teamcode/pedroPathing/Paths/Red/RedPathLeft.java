package org.firstinspires.ftc.teamcode.pedroPathing.Paths.Red;

// RR-specific imports

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.Arm;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.Slide;

@Config
@Autonomous(name = "Red Path Left", group = "Autonomous")
public class RedPathLeft extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {


        Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Arm arm = new Arm(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Slide slide = new Slide(hardwareMap);
//

        int visionOutputPosition = 1;
        //TODO Make actual Path use Meep Meep and transfer said path here later
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .lineToYSplineHeading(33, Math.toRadians(0))
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .lineToY(48)
                .setTangent(Math.toRadians(0))
                .lineToX(32)
                .strafeTo(new Vector2d(44.5, 30))
                .turn(Math.toRadians(180))
                .lineToX(47.5)
                .waitSeconds(3);
        Action trajectoryActionCloseOut = tab1.fresh()
                .strafeTo(new Vector2d(48, 12))
                .build();

        int startPosition = visionOutputPosition;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
        waitForStart();
        if (isStopRequested()) return;

        Action trajectoryActionChosen = tab1.build();


        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen,
                        (Action) arm.liftUp(),
                        (Action) slide.extend_slide(),
                        (Action) intake.intake(),
                        (Action) slide.close_slide(),
                        (Action) intake.output(),
                        (Action) arm.liftDown(),
                        trajectoryActionCloseOut
                )
        );

    }


}

