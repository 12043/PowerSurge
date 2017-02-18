package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Travis on 12/16/2016.
 */

public class Conroy extends OpMode {
    DcMotor leftWheelfront;
    DcMotor rightWheelfront;
    DcMotor rightWheelback;
    DcMotor leftWheelback;
    DcMotor launcherWheelback;
    DcMotor launcherWheelfront;
    DcMotor spinner;
    Servo launcher;

    @Override
    public void init() {
        leftWheelfront = hardwareMap.dcMotor.get("left_wheelfront");
        rightWheelfront = hardwareMap.dcMotor.get("right_wheelfront");
        launcherWheelfront = hardwareMap.dcMotor.get("frontlancher");
        launcherWheelback = hardwareMap.dcMotor.get("backlauncher");
        spinner = hardwareMap.dcMotor.get("_spinner");
        launcher = hardwareMap.servo.get("launcherservo");
        launcherWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launcherWheelback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    @Override
    public void loop() {
        leftWheelfront.setPower(gamepad1.left_stick_y);
        rightWheelfront.setPower(gamepad1.right_stick_y);
        //leftWheelback.setPower(gamepad1.left_stick_y);
        //rightWheelback.setPower(gamepad1.right_stick_y);

        if (gamepad1.dpad_left){
            spinner.setPower(1);
        }

        if (gamepad1.dpad_right){
            spinner.setPower(0);
        }

    }

    
}
