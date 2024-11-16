package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;

public class SlideScoreCommand extends CommandBase {
    private SlideSubsystem slideSubsystem;

    public SlideScoreCommand(SlideSubsystem subsystem){
        this.slideSubsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        slideSubsystem.goto_score_position();
    }

    @Override
    public boolean isFinished(){
        return slideSubsystem.reached_position();
    }

    @Override
    public void end(boolean interrupted){
        slideSubsystem.stop_using_encoder();
    }
}
