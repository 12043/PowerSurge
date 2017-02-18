package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Travis on 12/8/2016.
 * A new comment
 */
@TeleOp(name = "teleopmater2")
public class teleopmater2 extends OpMode {
    DcMotor leftWheelfront;
    DcMotor rightWheelfront;
    DcMotor rightWheelback;
    DcMotor leftWheelback;
    DcMotor launcherWheelback;
    DcMotor launcherWheelfront;
    DcMotor spinner;
    Servo launcher;
    ModernRoboticsI2cRangeSensor rangeSensor;

    @Override
    public void init() {
        leftWheelfront = hardwareMap.dcMotor.get("left_wheelfront");
        rightWheelfront = hardwareMap.dcMotor.get("right_wheelfront");
        //rightWheelback = hardwareMap.dcMotor.get("right_wheelback");
        //leftWheelback = hardwareMap.dcMotor.get("left_wheelback");
        launcherWheelback = hardwareMap.dcMotor.get("backlauncher");
        launcherWheelfront = hardwareMap.dcMotor.get("frontlauncher");
        spinner = hardwareMap.dcMotor.get("_spinner");
        launcherWheelback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launcherWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launcher = hardwareMap.servo.get("launcherservo");
   }


    @Override
    public void loop() {
        telemetry.update();
        telemetry.addData("Power to back _launcher", launcherWheelback.getPower());
        telemetry.addData("Power to front _launcher :)", launcherWheelfront.getPower());
        telemetry.addData("Power to front right Wheel", rightWheelfront.getPower());
        telemetry.addData("Power to front left Wheel", leftWheelfront.getPower());
        telemetry.addData("Power to _spinner", spinner.getPower());
        telemetry.addData("Position of servo", launcher.getPosition());
        telemetry.addData("right trigger position", gamepad1.right_trigger);
        telemetry.update();

        double LTriggerPosition = gamepad1.left_trigger;
        if (LTriggerPosition > .001) {
            launcher.setPosition(0.4);
        } else {
            launcher.setPosition(0.9);
        }
        if (gamepad1.dpad_left) {
            spinner.setPower(1);
        }
        if (gamepad1.dpad_right) {
            spinner.setPower(0);
        }
        double drive1 = gamepad1.right_stick_y;        //declaring the stick values
        double drive2 = gamepad1.right_stick_x;
        double drive3 = gamepad1.right_trigger;

        if((gamepad1.left_stick_y == 0) && (gamepad1.right_stick_x != 0))
        {
            leftWheelfront.setPower(gamepad1.right_stick_x);
            rightWheelfront.setPower(gamepad1.right_stick_x);
        }
        if(gamepad1.right_stick_x == 0)
        {
            leftWheelfront.setPower(gamepad1.left_stick_y);
            rightWheelfront.setPower(gamepad1.left_stick_y);
        }
         else if (gamepad1.right_stick_x < 0)
        {
            leftWheelfront.setPower((1 + gamepad1.right_stick_x)*(gamepad1.left_stick_y));
            rightWheelfront.setPower(gamepad1.left_stick_y);
        }
        else
        {
            leftWheelfront.setPower(gamepad1.left_stick_y);
            rightWheelfront.setPower((1 - gamepad1.right_stick_x)*(gamepad1.left_stick_y));
        }


//only front wheel drive
//very smooth drive
        //_rightWheelfront.setPower(gamepad1.right_stick_y);
        //_leftWheelfront.setPower(-gamepad1.left_stick_y);
        //leftWheelback.setPower(gamepad1.right_stick_y);
        //rightWheelback.setPower(-gamepad1.left_stick_y);
        if (gamepad1.dpad_down) {
            launcherWheelback.setPower(-1);
            launcherWheelfront.setPower(-1);
        }
        if(gamepad1.dpad_up) {
            launcherWheelback.setPower(0);
            launcherWheelfront.setPower(0);
        }
    }
}



