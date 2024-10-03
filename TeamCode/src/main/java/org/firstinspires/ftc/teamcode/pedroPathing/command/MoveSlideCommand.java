package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;

public class MoveSlideCommand extends CommandBase {
    private SlideSubsystem slide_subsystem;
    private int target_position;
    private double speed;

    public MoveSlideCommand(SlideSubsystem subsystem, int target_position, double speed){
        this.slide_subsystem = subsystem;
        this.target_position = target_position;
        this.speed = speed;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        slide_subsystem.move_slides(target_position, speed);
    }

    @Override
    public boolean isFinished(){
        return !slide_subsystem.is_slide_busy();
    }

    @Override
    public void end(boolean interrupted){
        slide_subsystem.stop_slide();
    }

}
