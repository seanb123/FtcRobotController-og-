package org.firstinspires.ftc.teamcode.pedroPathing.camera;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
@Autonomous
public class Webcam extends LinearOpMode {
    private OpenCvCamera webcam = null;

    @Override
    public void runOpMode() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraViewID = hardwareMap.appContext.getResources().getIdentifier("cameraViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName,cameraViewID);
        yellow pipeline = new yellow(telemetry);
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1920, 1080, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine("ERROR: " + errorCode);
            }

        });

        waitForStart();

        yellow.Location location = pipeline.getLocation();

        switch (location) {
            case LEFT:
                // Code to execute if the object is detected on the left
                telemetry.addData("Object Location", "Left");
                break;
            case RIGHT:
                // Code to execute if the object is detected on the right
                telemetry.addData("Object Location", "Right");
                break;
            case MIDDLE:
                // Code to execute if the object is detected in the middle
                telemetry.addData("Object Location", "Middle");
                break;
            case NOT_FOUND:
                // Code to execute if the object is not found
                telemetry.addData("Object Location", "Not Found");
                break;
        }

        // Update telemetry and stop the camera stream
        telemetry.update();
        webcam.stopStreaming();
    }
}
