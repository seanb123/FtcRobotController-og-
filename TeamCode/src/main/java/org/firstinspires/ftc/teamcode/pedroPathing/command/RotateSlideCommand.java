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

    private double map(double x, double  in_min, double in_max, double out_min, double out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    @Override
    public void execute(){
        double right_stick_y = -controller.getRightY();
        double current_position = subsystem.get_position();
//        subsystem.rotate_slides(right_stick_y != 0 ? (right_stick_y > 0 ? right_stick_y * speed_reducer : right_stick_y * speed_reducer * 0.25) : (subsystem.get_position() > 1100 ? (subsystem.get_position() > 1400 ? -0.25 : -0.1) : 0.25));

        double idle_speed;
        if(current_position <= 1350){
            idle_speed = map(current_position, 0, 1350, 0.25, 0);
        } else {
            idle_speed = map(current_position, 1350, 2100, 0, -0.25);
        }

        if (Math.abs(right_stick_y) > 0.1){
            double adjusted_speed = right_stick_y > 0 ? right_stick_y * speed_reducer : right_stick_y * speed_reducer * 0.25;
            subsystem.rotate_slides(adjusted_speed);
        } else {
            subsystem.rotate_slides(idle_speed);
        }
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
