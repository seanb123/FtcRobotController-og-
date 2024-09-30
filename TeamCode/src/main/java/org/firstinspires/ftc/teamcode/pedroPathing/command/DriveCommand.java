package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.DriveSubsystem;

public class DriveCommand extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private Gamepad gamepad;

    public DriveCommand(DriveSubsystem driveSubsystem, Gamepad gamepad){
        this.driveSubsystem = driveSubsystem;
        this.gamepad = gamepad;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        // use the Follower within the DriveSubsystem to handle driving
        double forward = -gamepad.left_stick_y;
        double strafe = -gamepad.left_stick_x;
        double rotate = -gamepad.right_stick_x;

        driveSubsystem.drive(forward, strafe, rotate);
    }

    @Override
    public boolean isFinished() {
        return false;  // runs continuously until interrupted
    }
}
