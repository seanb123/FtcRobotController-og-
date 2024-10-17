package org.firstinspires.ftc.teamcode.pedroPathing.camera;


//Where I learned to use OpenCV and FTC
/*
    https://deltacv.gitbook.io/eocv-sim/vision-portal/introduction-to-visionportal
    https://www.youtube.com/watch?v=JO7dqzJi8lw
 */
//TODO add more functionality to the code and add more features to make it more accurate
//resources
// Import necessary libraries for OpenCV and FTC
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

// Define a class named "yellow" that extends OpenCvPipeline for image processing
public class yellow extends OpenCvPipeline {
    // Telemetry object for sending data to the driver station
    Telemetry telemetry;
    // Mat object to store the image data
    Mat mat = new Mat();

    // Enum to define the possible locations of the detected object
    public enum Location {
        LEFT,
        RIGHT,MIDDLE,
        NOT_FOUND
    }
    // Variable to store the detected location of the object
    private Location location;

    // Define regions of interest (ROIs) for object detection
    // These ROIs represent the left, middle, and right sections of the image
    static final Rect LEFT_ROI = new Rect(
            new Point(0, 0), // Top-left corner of the left ROI
            new Point(425, 720)); // Bottom-right corner of the left ROI
    static final Rect MIDDLE_ROI = new Rect(
            new Point(426, 0), // Top-left corner of the middle ROI
            new Point(851, 720)); // Bottom-right corner of the middle ROI
    static final Rect RIGHT_ROI = new Rect(
            new Point(853, 0), // Top-left corner of the right ROI
            new Point(1280, 720)); // Bottom-right corner of the right ROI

    // Threshold for color detection (percentage of pixels within the ROI)
    // If the percentage of pixels matching the target color exceeds this threshold,
    // the object is considered to be detected in that ROI
    static double PERCENT_COLOR_THRESHOLD = 0.05;

    // Constructor for the class, initializes the telemetry object
    public yellow(Telemetry t) {
        telemetry = t;
    }
    // Constants for camera calibration and object detection
    static final double KNOWN_WIDTH = 20.0; // Adjust this based on the real width of your object
    //static final double KNOWN_HEIGHT = 20.0; // Adjust this based on the real height of your object
    static final double FOCAL_LENGTH = 20.0; // Approximate value, adjust based on camera calibration

    // This method is called for each frame captured by the camera
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(23, 50, 70);
        Scalar highHSV = new Scalar(32, 255, 255);
        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat left = mat.submat(LEFT_ROI);
        Mat middle = mat.submat(MIDDLE_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double middleValue = Core.sumElems(middle).val[0] / MIDDLE_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        left.release();
        middle.release();
        right.release();

        telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Middle raw value", (int) Core.sumElems(middle).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(right).val[0]);

        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Middle percentage", Math.round(middleValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");

        boolean sampleLeft = leftValue > PERCENT_COLOR_THRESHOLD;
        boolean sampleMiddle = middleValue > PERCENT_COLOR_THRESHOLD;
        boolean sampleRight = rightValue > PERCENT_COLOR_THRESHOLD;

        if (sampleRight) {
            location = Location.RIGHT;
            telemetry.addData("Prop Location", "RIGHT");
        } else if (sampleLeft) {
            location = Location.LEFT;
            telemetry.addData("Prop Location", "LEFT");
        } else if (sampleMiddle) {
            location = Location.MIDDLE;
            telemetry.addData("Prop Location", "MIDDLE");
        } else {
            location = Location.NOT_FOUND;
            telemetry.addData("Prop Location", "NOT FOUND");
        }

        // Calculate the perceived width of the object in pixels based on the location
        double perceivedWidth = 0;
        if (location == Location.LEFT) {
            perceivedWidth = LEFT_ROI.width;
        } else if (location == Location.MIDDLE) {
            perceivedWidth = MIDDLE_ROI.width;
        } else if (location == Location.RIGHT) {
            perceivedWidth = RIGHT_ROI.width;
        }

        // Calculate the distance using the pin-hole camera formula
        double distance = 0;
        if (perceivedWidth > 0) {
            distance = (KNOWN_WIDTH * FOCAL_LENGTH) / perceivedWidth;
        }

        // Send distance data to telemetry
        telemetry.addData("Distance to object (cm)", distance);

        telemetry.update();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        Scalar color = new Scalar(255, 0, 0);
        Scalar colorObject = new Scalar(0, 255, 0);

        Imgproc.rectangle(mat, LEFT_ROI, location == Location.LEFT ? colorObject : color);
        Imgproc.rectangle(mat, MIDDLE_ROI, location == Location.MIDDLE ? colorObject : color);
        Imgproc.rectangle(mat, RIGHT_ROI, location == Location.RIGHT ? colorObject : color);

        return mat;
    }


    // Getter method to retrieve the detected location of the object
    public Location getLocation() {
        return location;
    }
}