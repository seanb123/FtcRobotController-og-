package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.leftFrontMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.leftRearMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.rightFrontMotorName;
import static org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants.rightRearMotorName;

import android.sax.StartElementListener;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;

public class DriveSubsystem extends SubsystemBase {
    private Motor leftFront;
    private Motor leftBack;
    private Motor rightFront;
    private Motor rightBack;
    private MecanumDrive drive;

    public DriveSubsystem(HardwareMap hardwareMap){
        leftFront = new Motor(hardwareMap, leftFrontMotorName, Motor.GoBILDA.RPM_312);
        leftBack = new Motor(hardwareMap, leftRearMotorName, Motor.GoBILDA.RPM_312);
        rightBack = new Motor(hardwareMap, rightRearMotorName, Motor.GoBILDA.RPM_312);
        rightFront = new Motor(hardwareMap, rightFrontMotorName, Motor.GoBILDA.RPM_312);

        leftFront.setInverted(true);
        rightBack.setInverted(true);

        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        drive = new MecanumDrive(leftFront, leftBack, rightFront, rightBack);
    }

    public void drive(double forward, double strafe, double rotate){
        drive.driveRobotCentric(forward, strafe, rotate);

    }
}
