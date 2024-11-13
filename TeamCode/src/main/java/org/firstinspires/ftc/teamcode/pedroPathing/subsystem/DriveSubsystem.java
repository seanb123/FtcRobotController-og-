package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem extends SubsystemBase {
    private Motor leftFront;
    private Motor leftBack;
    private Motor rightFront;
    private Motor rightBack;
    private MecanumDrive drive;
    private String leftFrontMotorName = "leftFront";
    private String rightFrontMotorName = "rightFront";
    private String leftRearMotorName = "leftBack";
    private String rightRearMotorName = "rightBack";

    public DriveSubsystem(HardwareMap hardwareMap){
        // Initializing hardware
        leftFront = new Motor(hardwareMap, leftFrontMotorName, Motor.GoBILDA.RPM_312);
        leftBack = new Motor(hardwareMap, leftRearMotorName, Motor.GoBILDA.RPM_312);
        rightBack = new Motor(hardwareMap, rightRearMotorName, Motor.GoBILDA.RPM_312);
        rightFront = new Motor(hardwareMap, rightFrontMotorName, Motor.GoBILDA.RPM_312);

        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        leftFront.resetEncoder();
        leftBack.resetEncoder();
        rightFront.resetEncoder();
        rightBack.resetEncoder();

        drive = new MecanumDrive(leftFront, rightFront, leftBack, rightBack);
    }
    public void drive(double forward, double strafe, double rotate){
        // FTCLib
        drive.driveRobotCentric(strafe, forward, rotate);
    }
}
