package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        // Declare our first bot
        RoadRunnerBotEntity myFirstBot = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myFirstBot.runAction(myFirstBot.getDrive().actionBuilder(new Pose2d(10.6, -60.1, Math.toRadians(90)))
                .strafeTo(new Vector2d(38,-37))
                .strafeTo(new Vector2d(38,-12))
                .strafeTo(new Vector2d(47,-12))
                .strafeTo(new Vector2d(47,-55))
                .strafeTo(new Vector2d(47,-12))
                .strafeTo(new Vector2d(58,-12))
                .strafeTo(new Vector2d(58,-55))
                .strafeTo(new Vector2d(58,-12))
                .strafeTo(new Vector2d(62,-12))
                .strafeTo(new Vector2d(62,-55))
                .strafeTo(new Vector2d(58,-60))
                .turn(Math.toRadians(180))
                .strafeTo(new Vector2d(0,-40))
                .turn(Math.toRadians(180))
                .strafeTo(new Vector2d(0,-33))
                .turn(Math.toRadians(180))
                .strafeTo(new Vector2d(58,-60))
                .turn(Math.toRadians(180))
                .strafeTo(new Vector2d(0,-40))
                .turn(Math.toRadians(180))
                .strafeTo(new Vector2d(0,-33))






                //Pick up piece

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(1.0f)
                // Add both of our declared bot entities`
                .addEntity(myFirstBot)
                .start();
    }
}