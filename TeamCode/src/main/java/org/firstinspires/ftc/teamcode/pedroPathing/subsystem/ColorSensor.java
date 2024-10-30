package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;

public class ColorSensor extends LinearOpMode {

    com.qualcomm.robotcore.hardware.ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    @Override
    public void runOpMode() {
        init();
        // get a reference to the distance sensor that shares the same name.

        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");
        waitForStart();
        while (opModeIsActive()) {


            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));
            telemetry.update();
        }
    }
}
