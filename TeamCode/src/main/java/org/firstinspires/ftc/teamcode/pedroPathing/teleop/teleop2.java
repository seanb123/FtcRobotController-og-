package org.firstinspires.ftc.teamcode.pedroPathing.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.command.DriveCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveActuatorCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveSlideCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.ActuatorSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;

@TeleOp(name = "MainTeleop")
public class teleop2 extends CommandOpMode {
    private DriveSubsystem drive_subsystem;
    private SlideSubsystem slide_subsystem;
    private ActuatorSubsystem actuator_subsystem;
    private GamepadEx arm_controller;
    private com.arcrobotics.ftclib.command.button.Button raise_arm_button, lower_arm_button, raise_actuator_button, lower_actuator_button;

    @Override
    public void initialize(){
        arm_controller = new GamepadEx(gamepad1);

        drive_subsystem = new DriveSubsystem(hardwareMap);
        slide_subsystem = new SlideSubsystem(hardwareMap);
        actuator_subsystem = new ActuatorSubsystem(hardwareMap);

        drive_subsystem.setDefaultCommand(new DriveCommand(drive_subsystem, gamepad1));

        raise_arm_button = (new GamepadButton(arm_controller, GamepadKeys.Button.A))
                .whenPressed(new MoveSlideCommand(slide_subsystem, 7000, 1));
        lower_arm_button = (new GamepadButton(arm_controller, GamepadKeys.Button.B))
                .whenPressed(new MoveSlideCommand(slide_subsystem, 0, 1));
        raise_actuator_button = (new GamepadButton(arm_controller, GamepadKeys.Button.X))
                .whileHeld(new MoveActuatorCommand(actuator_subsystem, 1));
        lower_actuator_button = (new GamepadButton(arm_controller, GamepadKeys.Button.Y))
                .whileHeld(new MoveActuatorCommand(actuator_subsystem, -1));

    }
}
