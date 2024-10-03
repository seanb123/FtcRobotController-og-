package org.firstinspires.ftc.teamcode.pedroPathing.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.ActuatorSubsystem;

public class MoveActuatorCommand extends CommandBase {
    private ActuatorSubsystem actuator_subsystem;
    private double speed;
    public MoveActuatorCommand(ActuatorSubsystem subsystem, double speed){
        this.actuator_subsystem = subsystem;
        this.speed = speed;
    }

    @Override
    public void initialize(){
        actuator_subsystem.move_actuators(speed);
    }

    @Override
    public void end(boolean interrupted){
        actuator_subsystem.stop_actuators();
    }
}
