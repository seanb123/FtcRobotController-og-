package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        Pose2d initialPose = new Pose2d(-35, -60, Math.toRadians(90));
        RoadRunnerBotEntity redPathLeft = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(initialPose)
                        .strafeTo(new Vector2d(-60, -60))
                        .strafeTo(new Vector2d(-58, -56))
                        .strafeTo(new Vector2d(-54, -53))
                        .turn(Math.toRadians(135))
                        .waitSeconds(1)
                        .strafeTo(new Vector2d(-58, -56))
                        .waitSeconds(0.5)
                        .turn(Math.toRadians(-135))
                        .strafeTo(new Vector2d(-48, -40))
                        .waitSeconds(1)
                        .strafeTo(new Vector2d(-54, -53))
                        .turn(Math.toRadians(135))
                        .waitSeconds(3)
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redPathLeft)
                .start();
    }
}