package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ActuatorSubsystem extends SubsystemBase {
    private DcMotor actuator_motor;

    public ActuatorSubsystem(HardwareMap hardwareMap){
        actuator_motor = hardwareMap.get(DcMotorEx.class, "actuatorMotor");
    }
    public void move_actuators(double power){
        actuator_motor.setPower(power);
    }
    public void stop_actuators(){
        actuator_motor.setPower(0);
    }
}
