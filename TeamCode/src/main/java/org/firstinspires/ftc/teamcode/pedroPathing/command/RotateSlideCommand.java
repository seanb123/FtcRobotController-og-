package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;

public class RotateSlideCommand extends CommandBase {
    private RotateSlideSubsystem subsystem;
    private double speed;

    public RotateSlideCommand(RotateSlideSubsystem subsystem, double speed){
        this.subsystem = subsystem;
        this.speed = speed;
    }

    @Override
    public void initialize(){
        subsystem.rotate_slides(speed);
    }

    @Override
    public void end(boolean interrupted){
        subsystem.stop_rotating();
    }

}
