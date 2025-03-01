package org.firstinspires.ftc.teamcode.pedroPathing.teleop;

import static com.arcrobotics.ftclib.kotlin.extensions.gamepad.GamepadExExtKt.whenActive;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.command.ArmScoreCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.DriveCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveIntakeCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveActuatorCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveSlideCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.RetractSlideCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.RotateSlideCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.SlideScoreCommand;
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
    private GamepadEx arm_controller, drive_controller;

    private Trigger left_trigger, right_trigger;

    private com.arcrobotics.ftclib.command.button.Button raise_arm_button, lower_arm_button, lower_slide_button, raise_actuator_button, lower_actuator_button, raise_slide_button, move_intake_button, move_intake_button2, rotate_button, rotate_button2;

    @Override
    public void initialize(){
        // TODO: Gamepad rumble? Color sensor

        // Controllers
        drive_controller = new GamepadEx(gamepad1);
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
        slide_subsystem.setDefaultCommand(new MoveSlideCommand(slide_subsystem, arm_controller));
        intake_subsystem.setDefaultCommand(new MoveIntakeCommand(intake_subsystem, arm_controller));
        rotate_slide_subsystem.setDefaultCommand(new RotateSlideCommand(rotate_slide_subsystem, arm_controller));

//        raise_arm_button = (new GamepadButton(arm_controller, GamepadKeys.Button.A))
//                .whenPressed(new MoveSlideCommand(slide_subsystem, 7000, 1));
//        lower_arm_button = (new GamepadButton(arm_controller, GamepadKeys.Button.B))
//                .whenPressed(new MoveSlideCommand(slide_subsystem, 0, 1));

        raise_arm_button = (new GamepadButton(arm_controller, GamepadKeys.Button.A))
                .whenPressed(new ArmScoreCommand(rotate_slide_subsystem));

        raise_slide_button = (new GamepadButton(arm_controller, GamepadKeys.Button.B))
                .whenPressed(new SlideScoreCommand(slide_subsystem));

        lower_slide_button = (new GamepadButton(arm_controller, GamepadKeys.Button.Y))
                .whenPressed(new RetractSlideCommand(slide_subsystem));

        raise_actuator_button = (new GamepadButton(arm_controller, GamepadKeys.Button.LEFT_BUMPER))
                .whileHeld(new MoveActuatorCommand(actuator_subsystem, 1));
        lower_actuator_button = (new GamepadButton(arm_controller, GamepadKeys.Button.RIGHT_BUMPER))
                .whileHeld(new MoveActuatorCommand(actuator_subsystem, -1));

//        move_intake_button = (new GamepadButton(drive_controller, GamepadKeys.Button.X))
//                .whenHeld(new MoveIntakeCommand(intake_subsystem, true));
//        move_intake_button2 = (new GamepadButton(drive_controller, GamepadKeys.Button.Y))
//                .whenHeld(new MoveIntakeCommand(intake_subsystem, false));

//        rotate_button = (new GamepadButton(arm_controller, GamepadKeys.Button.X))
//                .whenPressed(new RotateSlideCommand(rotate_slide_subsystem, 1000, 0.25));
//        rotate_button2 = (new GamepadButton(arm_controller, GamepadKeys.Button.Y))
//                .whenPressed(new RotateSlideCommand(rotate_slide_subsystem, 0, 0.25));


//        left_trigger.whenActive(new MoveIntakeCommand(intake_subsystem, true));
//        right_trigger.whenActive(new MoveIntakeCommand(intake_subsystem, false));
//
//        left_trigger = (new (arm_controller, GamepadKeys.Trigger.LEFT_TRIGGER))
//                .whenActive(new MoveIntakeCommand(intake_subsystem, true));

    }
}
