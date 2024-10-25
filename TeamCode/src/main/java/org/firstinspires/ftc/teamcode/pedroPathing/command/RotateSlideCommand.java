package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;

public class RotateSlideCommand extends CommandBase {
    private RotateSlideSubsystem subsystem;
    private double speed;
    private int target_position;

    public RotateSlideCommand(RotateSlideSubsystem subsystem, int target_position, double speed){
        this.subsystem = subsystem;
        this.target_position = target_position;
        this.speed = speed;

        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        subsystem.rotate_slides(target_position, speed);
    }

    @Override
    public void execute(){
        subsystem.hold_position();
    }

    @Override
    public void end(boolean interrupted){
        subsystem.stop_rotating();
    }

    @Override
    public boolean isFinished() {
        return false;  // runs continuously until interrupted
    }

}
