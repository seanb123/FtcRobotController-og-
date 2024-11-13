package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.IntakeSubsystem;

public class MoveIntakeCommand extends CommandBase {
    private IntakeSubsystem intake_subsystem;
    private GamepadEx controller;
    private double left_trigger, right_trigger;

    public MoveIntakeCommand(IntakeSubsystem intake_subsystem, GamepadEx controller){
        this.intake_subsystem = intake_subsystem;
        this.controller = controller;

        addRequirements(intake_subsystem);
    }

    @Override
    public void execute(){
        left_trigger = controller.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        right_trigger = controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);

        if (left_trigger > 0){
            intake_subsystem.move_intake(true);
        } else if (right_trigger > 0){
            intake_subsystem.move_intake(false);
        } else {
            intake_subsystem.stop_intake();
        }
    }

    @Override
    public void end(boolean interrupted){
        intake_subsystem.stop_intake();
    }

    @Override
    public boolean isFinished() {
        return false;  // runs continuously until interrupted
    }
}
