package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.IntakeSubsystem;

public class MoveIntakeCommand extends CommandBase {
    private IntakeSubsystem intake_subsystem;
    private boolean direction;

    public MoveIntakeCommand(IntakeSubsystem intake_subsystem, boolean direction){
        this.intake_subsystem = intake_subsystem;
        this.direction = direction;

        addRequirements(intake_subsystem);
    }

    @Override
    public void initialize(){
        intake_subsystem.move_intake(direction);
    }

    @Override
    public void end(boolean interrupted){
        intake_subsystem.stop_intake();
    }

}
