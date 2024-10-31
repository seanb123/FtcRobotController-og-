package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;

public class ColorSensor2 extends SubsystemBase{
    DistanceSensor sensorDistance_l;
    DistanceSensor sensorDistance_r;
    public ColorSensor2(HardwareMap hardwareMap){
        sensorDistance_l = hardwareMap.get(DistanceSensor.class, "sensor_color_distance_l");
        sensorDistance_r = hardwareMap.get(DistanceSensor.class, "sensor_color_distance_r");
    }
    public void color_sense() {
        telemetry.addData("Distance (cm)",
            String.format(Locale.US, "%.02f", sensorDistance_l.getDistance(DistanceUnit.CM)));
        telemetry.addData("Distance (cm)",
                String.format(Locale.US, "%.02f", sensorDistance_r.getDistance(DistanceUnit.CM)));
        telemetry.update();
    }
}
