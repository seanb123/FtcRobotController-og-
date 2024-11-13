package org.firstinspires.ftc.teamcode.pedroPathing.autonomous;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.ActuatorSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;


@Config
@Autonomous
public class path1 extends CommandOpMode {
    private SlideSubsystem slide_subsystem;
    private IntakeSubsystem intake_subsystem;
    private RotateSlideSubsystem rotate_slide_subsystem;

    @Override
    public void initialize() {
        slide_subsystem = new SlideSubsystem(hardwareMap);
        intake_subsystem = new IntakeSubsystem(hardwareMap);
        rotate_slide_subsystem = new RotateSlideSubsystem(hardwareMap);

    }
    // actionBuilder builds from the drive steps passed to it
    @Override
    public void runOpMode() {
        // instantiate your MecanumDrive at a particular pose.
        Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        // actionBuilder builds from the drive steps passed to it
        TrajectoryActionBuilder myBot = drive.actionBuilder(initialPose)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width


                        .forward(30)
                        .turn(Math.toRadians(90))
                        .forward(2)
                        //spin intake to pick up brick
                        .strafeLeft(26)
                        .forward(20)
                        .turn(Math.toRadians(45))
                        //put brick into basket

                        .build();
}
