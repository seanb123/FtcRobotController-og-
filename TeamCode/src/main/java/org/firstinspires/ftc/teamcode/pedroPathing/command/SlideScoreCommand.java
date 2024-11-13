package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;

public class SlideScoreCommand extends CommandBase {
    private SlideSubsystem subsystem;

    public SlideScoreCommand(SlideSubsystem subsystem){
        this.subsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        subsystem.goto_position();
    }

    @Override
    public boolean isFinished(){
        return subsystem.reached_position();
    }

    @Override
    public void end(boolean interrupted){
        subsystem.stop_using_encoder();
    }
}
