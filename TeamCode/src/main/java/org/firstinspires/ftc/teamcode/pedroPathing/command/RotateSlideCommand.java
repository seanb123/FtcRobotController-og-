package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;

public class RotateSlideCommand extends CommandBase {
    private RotateSlideSubsystem subsystem;
    private GamepadEx controller;
    private final double speed_reducer = 0.5;

    public RotateSlideCommand(RotateSlideSubsystem subsystem, GamepadEx controller){
        this.subsystem = subsystem;
        this.controller = controller;

        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        double right_stick_y = -controller.getRightY();
        subsystem.rotate_slides(right_stick_y != 0 ? (right_stick_y > 0 ? right_stick_y * speed_reducer : (subsystem.get_position()) > 1400 ? right_stick_y : right_stick_y * 0.1) : (subsystem.get_position() > 1300 ? (subsystem.get_position() > 1400 ? -0.25 : 0) : 0.25));
    }

    @Override
    public boolean isFinished() {
        return false;  // runs continuously until interrupted
    }

    @Override
    public void end(boolean interrupted){
        subsystem.stop_rotating();
    }
}
