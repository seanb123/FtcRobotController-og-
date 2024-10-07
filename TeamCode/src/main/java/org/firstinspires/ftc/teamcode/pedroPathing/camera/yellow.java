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

    // This method is called for each frame captured by the camera
    @Override
    public Mat processFrame(Mat input) {
        // Convert the input image from RGB to HSV color space
        // HSV is often used for color detection as it separates hue, saturation, and value
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        // Define the lower and upper bounds for the target color in HSV
        // These values represent the range of HSV values that will be considered as the target color
        // In this case, it's set to detect yellow
        Scalar lowHSV = new Scalar(23, 50, 70); // Lower bound for yellow in HSV
        Scalar highHSV = new Scalar(32, 255, 255); // Upper bound for yellow in HSV


        // Filter the image to keep only pixels within the specified color range
        // This creates a binary mask where white pixels represent the target color and black pixels represent other colors
        Core.inRange(mat, lowHSV, highHSV, mat);

        // Extract sub mats representing the ROIs
        // These sub mats allow us to analyze specific regions of the image
        Mat left = mat.submat(LEFT_ROI);
        Mat middle = mat.submat(MIDDLE_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        // Calculate the percentage of pixels within each ROI that match the target color
        // This is done by summing the values of all pixels in the ROI and dividing by the total area
        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double middleValue = Core.sumElems(middle).val[0] / MIDDLE_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        // Release the sub mats to free up memory
        left.release();
        middle.release();
        right.release();

        // Send telemetry data to the driver station
        // This includes the raw pixel values and the calculated percentages for each ROI
        telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Middle raw value", (int) Core.sumElems(middle).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(right).val[0]);

        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Middle percentage", Math.round(middleValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");

        // Determine the location of the object based on color thresholds
        // If the percentage of pixels matching the target color in a specific ROI exceeds the threshold,
        // the object is considered to be detected in that location
        boolean stoneLeft = leftValue > PERCENT_COLOR_THRESHOLD;
        boolean stoneMiddle = middleValue > PERCENT_COLOR_THRESHOLD;
        boolean stoneRight = rightValue > PERCENT_COLOR_THRESHOLD;

        // Update the location variable and send telemetry data
        // Based on the detected location, the location variable is updated and the corresponding telemetry data is sent
        if (stoneRight) {
            location = Location.RIGHT;
            telemetry.addData("Prop Location", "RIGHT");
        } else if (stoneLeft) {
            location = Location.LEFT;
            telemetry.addData("Prop Location", "LEFT");
        } else if (stoneMiddle) {
            location = Location.MIDDLE;
            telemetry.addData("Prop Location", "MIDDLE");
        } else {
            location = Location.NOT_FOUND;
            telemetry.addData("Prop Location", "NOT FOUND");
        }

        // Update telemetry to display the data on the driver station
        telemetry.update();

        // Convert the processed image back to RGB for display
        // This is necessary as the image was previously converted to HSV for color detection
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        // Define colors for drawing rectangles around the ROIs
        // These colors will be used to highlight the detected location
        Scalar color = new Scalar(255, 0, 0); // Red color for ROIs without the object
        Scalar colorObject = new Scalar(0, 255, 0); // Green color for the ROI with the object

        // Draw rectangles around the ROIs, highlighting the detected location
        // This provides a visual representation of the object's location on the image
        Imgproc.rectangle(mat, LEFT_ROI, location == Location.LEFT ? colorObject : color);
        Imgproc.rectangle(mat, MIDDLE_ROI, location == Location.MIDDLE ? colorObject : color);
        Imgproc.rectangle(mat, RIGHT_ROI, location == Location.RIGHT ? colorObject : color);

        // Return the processed image with the drawn rectangles
        return mat;
    }

    // Getter method to retrieve the detected location of the object
    public Location getLocation() {
        return location;
    }
}