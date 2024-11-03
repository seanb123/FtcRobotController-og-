package org.firstinspires.ftc.teamcode.pedroPathing.teleop;
import static com.arcrobotics.ftclib.kotlin.extensions.gamepad.GamepadExExtKt.whenActive;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Encoder;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Localizer;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Matrix;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.MathFunctions;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Vector;
import org.firstinspires.ftc.teamcode.pedroPathing.util.NanoTimer;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.leftFrontMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.leftRearMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.rightFrontMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.rightRearMotorName;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.command.DriveCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveIntakeCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveActuatorCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.MoveSlideCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.command.RotateSlideCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Encoder;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.ActuatorSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.RotateSlideSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSubsystem;

public class encoder_check extends LinearOpMode {
    private Motor leftFront;
    private Motor leftBack;
    private Motor rightFront;
    private Motor rightBack;
    private MecanumDrive drive;





    @Override
    public void runOpMode()  {

        leftFront = new Motor(hardwareMap, leftFrontMotorName, Motor.GoBILDA.RPM_312);
        leftBack = new Motor(hardwareMap, leftRearMotorName, Motor.GoBILDA.RPM_312);
        rightBack = new Motor(hardwareMap, rightRearMotorName, Motor.GoBILDA.RPM_312);
        rightFront = new Motor(hardwareMap, rightFrontMotorName, Motor.GoBILDA.RPM_312);


        while (opModeIsActive()) {
            telemetry.addData("leftFront", leftFront.getCurrentPosition());
            telemetry.addData("rightFront", rightFront.getCurrentPosition());
            telemetry.addData("leftBack", leftBack.getCurrentPosition());
            telemetry.addData("rightBack", rightBack.getCurrentPosition());

            telemetry.update();

        }


    }
}
