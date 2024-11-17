package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;

public class MoveSlideCommand extends CommandBase {
    private SlideSubsystem slide_subsystem;
    private GamepadEx controller;

    // private int target_position;
    // private double speed;

    public MoveSlideCommand(SlideSubsystem subsystem, GamepadEx controller){
        this.slide_subsystem = subsystem;
        this.controller = controller;

        // this.speed = speed;
        // this.target_position = target_position;

        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        double left_stick_y = controller.getLeftY();
        slide_subsystem.move_slides(slide_subsystem.get_position() > 2100 ? (left_stick_y > 0 ? 0 : left_stick_y) : left_stick_y);
    }

    @Override
    public void end(boolean interrupted){
        slide_subsystem.stop_slide();
    }

    @Override
    public boolean isFinished() {
        return false;  // runs continuously until interrupted
    }
}
