package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;

public class RetractSlideCommand extends CommandBase {
    private SlideSubsystem slideSubsystem;

    public RetractSlideCommand(SlideSubsystem subsystem){
        this.slideSubsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        slideSubsystem.retract_slides();
    }

    @Override
    public boolean isFinished(){
        return slideSubsystem.retracted();
    }

    @Override
    public void end(boolean interrupted){
        slideSubsystem.stop_using_encoder();
    }
}
