package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;

public class ArmScoreCommand extends CommandBase {
    private RotateSlideSubsystem rotate_subsystem;

    public ArmScoreCommand(RotateSlideSubsystem rotate_subsystem){
        this.rotate_subsystem = rotate_subsystem;

        addRequirements(rotate_subsystem);
    }

    @Override
    public void initialize(){
        rotate_subsystem.goto_score_position();
    }

    @Override
    public boolean isFinished(){
        return rotate_subsystem.reached_position();
    }

    @Override
    public void end(boolean interrupted){
        rotate_subsystem.stop_using_encoder();
    }
}
