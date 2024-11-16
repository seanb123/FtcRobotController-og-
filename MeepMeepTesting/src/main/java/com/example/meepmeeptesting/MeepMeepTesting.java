package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        Pose2d initialPose = new Pose2d(12, -68, Math.toRadians(90));
        RoadRunnerBotEntity redPathLeft = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(initialPose)
                        .forward(30)
                        .strafeLeft(60)
                        .turn(Math.toRadians(145))
                        .forward(15)
                        .strafeLeft(10)
                        .back(30)
                        .turn(Math.toRadians(-75))
                        .turn(Math.toRadians(75))
                        .forward(30)
                        .strafeRight(10)
                        .turn(Math.toRadians(-145))
                        .strafeRight(115)
                        .back(15)
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redPathLeft)
                .start();
    }
}