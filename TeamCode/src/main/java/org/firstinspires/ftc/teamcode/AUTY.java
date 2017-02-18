package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRGyro;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static org.firstinspires.ftc.teamcode.R.layout.servo;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Travis on 2/9/2017.
 */
@Autonomous(name = "AUTY")
public class AUTY extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;
    boolean[] steps = new boolean [8];
    boolean tj = true;


    DcMotor leftMotor,rightMotor,backlauncher,frontlauncher;
    Servo Servo1;
    ModernRoboticsI2cGyro gyro;
    ModernRoboticsI2cColorSensor color;
    @Override public void runOpMode() {
        leftMotor = hardwareMap.dcMotor.get("left_wheelfront");
        rightMotor = hardwareMap.dcMotor.get("right_wheelfront");
        backlauncher = hardwareMap.dcMotor.get("backlauncher");
        frontlauncher = hardwareMap.dcMotor.get("frontlauncher");
        Servo1 = hardwareMap.servo.get("launcherservo");
        color = (ModernRoboticsI2cColorSensor)hardwareMap.colorSensor.get("color");
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        leftMotor.setMode(RUN_USING_ENCODER);
        rightMotor.setMode(RUN_USING_ENCODER);
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();
        gyro.calibrate();
        idle();


        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                leftMotor.getCurrentPosition(),
                rightMotor.getCurrentPosition());
        telemetry.update();
        gyro.calibrate();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        if(gyro.isCalibrating()){
            telemetry.addData("gyro is calibrating",gyro.isCalibrating());
        }else{
            steps[0]=true;


            //calibrate color sensor
        }
        if(steps[0]=true){

            Servo1.setPosition(.4);
            encoderDrive(DRIVE_SPEED, 36,-36,5.0);//move forward 36in with a 5 sec timeout
            encoderDrive(TURN_SPEED, 12,12,4.0);//turn right 12in with a 4 sec timeout
            backlauncher.setPower(-1);
            frontlauncher.setPower(1);
            Servo1.setPosition(.9);
            Servo1.setPosition(.4);
            Servo1.setPosition(.9);
            Servo1.setPosition(.4);

            }
        }




    public void DriveForward (double power){
        leftMotor.setPower(power);
        rightMotor.setPower(-power);}
    public void StopDriving(){
        DriveForward(0);
    }
    public void TurnLeft (double power){
        leftMotor.setPower(-power);
        rightMotor.setPower(-power);
    }
    public void TurnRight (double power){
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftMotor.setPower(Math.abs(speed));
            rightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        leftMotor.getCurrentPosition(),
                        rightMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
    }
