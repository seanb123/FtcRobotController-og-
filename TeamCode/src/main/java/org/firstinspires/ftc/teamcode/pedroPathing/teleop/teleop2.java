package org.firstinspires.ftc.teamcode.pedroPathing.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.command.DriveCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveIntakeCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveActuatorCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveSlideCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.RotateSlideCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.ActuatorSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;

@TeleOp(name = "MainTeleop")
public class teleop2 extends CommandOpMode {
    private DriveSubsystem drive_subsystem;
    private SlideSubsystem slide_subsystem;
    private ActuatorSubsystem actuator_subsystem;
    private IntakeSubsystem intake_subsystem;
    private RotateSlideSubsystem rotate_slide_subsystem;
    private GamepadEx arm_controller;
    private com.arcrobotics.ftclib.command.button.Button raise_arm_button, lower_arm_button, raise_actuator_button, lower_actuator_button, move_intake_button, move_intake_button2, rotate_button, rotate_button2;

    @Override
    public void initialize(){
        // TODO: Gamepad rumble? Color sensor

        // Controllers
        //drive_controller = new GamepadEx(gamepad1);
        arm_controller = new GamepadEx(gamepad2);

        // Subsystems
        drive_subsystem = new DriveSubsystem(hardwareMap);
        slide_subsystem = new SlideSubsystem(hardwareMap);
        actuator_subsystem = new ActuatorSubsystem(hardwareMap);
        intake_subsystem = new IntakeSubsystem(hardwareMap);
        rotate_slide_subsystem = new RotateSlideSubsystem(hardwareMap);

        // Keybinds
        /**
         * GAMEPAD 1
         * LEFT STICK -> UP DOWN LEFT RIGHT, STRAFE
         * RIGHT STICK -> ROTATION

         * GAMEPAD 2
         * BUTTON A -> EXTENDS LINEAR SLIDES
         * BUTTON B -> RETRACTS LINEAR SLIDES
         * BUTTON X -> SPINS INTAKE CLOCKWISE
         * BUTTON Y -> SPINS INTAKE COUNTERCLOCKWISE
         * LEFT BUMPER -> RAISE LINEAR ACTUATORS
         * RIGHT BUMPER -> LOWER LINEAR ACTUATORS
         * */
        drive_subsystem.setDefaultCommand(new DriveCommand(drive_subsystem, gamepad1));

        raise_arm_button = (new GamepadButton(arm_controller, GamepadKeys.Button.A))
                .whenPressed(new MoveSlideCommand(slide_subsystem, 7000, 1));
        lower_arm_button = (new GamepadButton(arm_controller, GamepadKeys.Button.B))
                .whenPressed(new MoveSlideCommand(slide_subsystem, 0, 1));

        raise_actuator_button = (new GamepadButton(arm_controller, GamepadKeys.Button.LEFT_BUMPER))
                .whileHeld(new MoveActuatorCommand(actuator_subsystem, 1));
        lower_actuator_button = (new GamepadButton(arm_controller, GamepadKeys.Button.RIGHT_BUMPER))
                .whileHeld(new MoveActuatorCommand(actuator_subsystem, -1));

        move_intake_button = (new GamepadButton(arm_controller, GamepadKeys.Button.X))
                .whenHeld(new MoveIntakeCommand(intake_subsystem, true));
        move_intake_button2 = (new GamepadButton(arm_controller, GamepadKeys.Button.Y))
                .whenHeld(new MoveIntakeCommand(intake_subsystem, false));

        rotate_button = (new GamepadButton(arm_controller, GamepadKeys.Button.DPAD_LEFT))
                .whenPressed(new RotateSlideCommand(rotate_slide_subsystem, 1));
        rotate_button2 = (new GamepadButton(arm_controller, GamepadKeys.Button.DPAD_RIGHT))
                .whenPressed(new RotateSlideCommand(rotate_slide_subsystem, 1));

    }
}
