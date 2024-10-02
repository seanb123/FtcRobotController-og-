package org.firstinspires.ftc.teamcode.pedroPathing.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.command.DriveCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.DriveSubsystem;

@TeleOp
public class teleop2 extends CommandOpMode {
    private DriveSubsystem driveSubsystem;

    @Override
    public void initialize(){
        driveSubsystem = new DriveSubsystem(hardwareMap);
        driveSubsystem.setDefaultCommand(new DriveCommand(driveSubsystem, gamepad1));
    }
}
