package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideSubsystem {
    private DcMotor slide_motor;

    public SlideSubsystem(HardwareMap hardwareMap){
        slide_motor = hardwareMap.get(DcMotorEx.class, "slideMotor");
        slide_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
