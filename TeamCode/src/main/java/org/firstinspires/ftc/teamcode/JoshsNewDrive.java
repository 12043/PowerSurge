package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.ftccommon.R.layout.servo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Travis on 1/12/2017.
 */

public class JoshsNewDrive extends OpMode {
    DcMotor Front_Wheel;
    DcMotor Back_Left;
    DcMotor Back_Right;
    DcMotor Front_Launcher, Back_Launcher, Spinner_right, Spinner_left;
    Servo Launcher_Servo;
    double y = gamepad1.left_stick_y;
    double x = gamepad1.left_stick_x;
    double test = 5;
    double rx = gamepad1.right_stick_x;


    @Override
    public void init() {//check for and initialize motors
        Front_Wheel = hardwareMap.dcMotor.get("Front_Wheel");
        Back_Left = hardwareMap.dcMotor.get("Back_Left");
        Back_Right = hardwareMap.dcMotor.get("Back_Right");
        Front_Launcher = hardwareMap.dcMotor.get("Front_Launcher");
        Back_Launcher = hardwareMap.dcMotor.get("Back_Launcher");
        Spinner_left = hardwareMap.dcMotor.get("Spinner_left");
        Spinner_right = hardwareMap.dcMotor.get("spinner_right");
        Spinner_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Spinner_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Front_Launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Back_Launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void loop() {
        if (gamepad1.y)
            rx = 0;
        if (gamepad1.x)
            rx = gamepad1.right_stick_x;
        telemetry.update();
        if ((y == 0) && (x == 0)) {
            Back_Left.setPower(rx);
            Back_Right.setPower(rx);
            Front_Wheel.setPower(rx);
        }

        if (gamepad1.right_trigger != 0) {
            Launcher_Servo.setPosition(0);
        } else {
            Launcher_Servo.setPosition(5);
            if (gamepad1.a)
                test = test + .05;
            if (gamepad1.b)
                test = test - .05;
            if (gamepad1.right_bumper)
                Front_Wheel.setPower(test);
                Back_Right.setPower(1);
                Back_Left.setPower(1);
            if ((y > x) && (y > -x))
                Back_Left.setPower(y + rx);
                Back_Right.setPower(y + rx);
            if ((y < x) && (y < -x))
                Back_Left.setPower(y + rx);
                Back_Right.setPower(y + rx);
            if ((y < x) && (y < -x))
                Front_Wheel.setPower((.75 * x) + rx);
                Back_Right.setPower(x + rx);
                Back_Left.setPower(x + rx);
            if ((y > x) && (y < -x))
                Front_Wheel.setPower(.75 * x + rx);
                Back_Left.setPower(x + rx);
                Back_Right.setPower(x + rx);
            //power to wheels and adding rotation at axis
            //replace .75 with correct ratio after testing
            telemetry.addData("Front_Wheel_Power:", test);
            telemetry.addData("Back left wheel", Back_Left.getPower());
            telemetry.addData("Back right wheel", Back_Right.getPower());
            telemetry.addData("Ratio:", test / x);
            telemetry.addData("right stick x", rx);
            telemetry.addData("left stick y", y);
            telemetry.addData("left stick x", x);
            if (gamepad1.dpad_up) {
                Spinner_right.setPower(1);
                Spinner_right.setPower(1);
            }
            if (gamepad1.dpad_down) {
                Spinner_right.setPower(0);
                Spinner_left.setPower(0);
            }
            //setting power to _spinner
            if (gamepad1.dpad_left) {
                Back_Launcher.setPower(1);
                Front_Launcher.setPower(1);
            }
            if (gamepad1.dpad_right) {
                Back_Launcher.setPower(0);
                Front_Launcher.setPower(0);
            }
            //setting power to _launcher

        }

    }
}