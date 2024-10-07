package org.firstinspires.ftc.teamcode.pedroPathing.camera;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
@Autonomous(name = "AutonomousWithCamera", group = "Autonomous")
public class Webcam extends LinearOpMode {
    private OpenCvCamera webcam;

    @Override
    public void runOpMode() {
        // Initialize the webcam and pipeline
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        yellow pipeline = new yellow(telemetry);
        webcam.setPipeline(pipeline);

        // Start the camera stream
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                // Handle camera errors
            }
        });

        // Wait for the start button to be pressed
        waitForStart();

        // Get the detected location of the object
        yellow.Location location = pipeline.getLocation();

        // Perform actions based on the detected location
        switch (location) {
            case LEFT:
                // Code to execute if the object is detected on the lefttelemetry.addData("Object Location", "Left");
                // ... your autonomous logic here ...
                break;
            case RIGHT:
                // Code to execute if the object is detected on the right
                telemetry.addData("Object Location", "Right");
                // ... your autonomous logic here ...
                break;
            case MIDDLE:
                // Code to execute if the object is detected in the middle
                telemetry.addData("Object Location", "Middle");
                // ... your autonomous logic here ...
                break;
            case NOT_FOUND:
                // Code to execute if the object is not found
                telemetry.addData("Object Location", "Not Found");
                // ... your autonomous logic here ...
                break;
        }

        // Update telemetry and stop the camera stream
        telemetry.update();
        webcam.stopStreaming();
    }
}
