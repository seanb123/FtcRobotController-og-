package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;

public class ClimbCommand extends CommandBase {
    private RotateSlideSubsystem subsystem;

    public ClimbCommand(RotateSlideSubsystem subsystem){
        this.subsystem = subsystem;

    }

    @Override
    public void initialize(){
        subsystem.climb();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
