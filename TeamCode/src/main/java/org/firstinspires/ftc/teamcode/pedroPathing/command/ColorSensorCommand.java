package org.firstinspires.ftc.teamcode.pedroPathing.command;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.ActuatorSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.ColorSensor2;
import com.arcrobotics.ftclib.command.CommandBase;

public class ColorSensorCommand extends CommandBase {

    private ColorSensor2 color_sensor;
    public ColorSensorCommand(ColorSensor2 subsystem){
        this.color_sensor = subsystem;

//sus
        addRequirements(subsystem);
    }
    @Override
    public void initialize(){
        color_sensor.color_sense();
    }
}
