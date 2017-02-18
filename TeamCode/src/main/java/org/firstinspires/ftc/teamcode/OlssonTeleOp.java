package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Eric Olsson on 2/11/2017.
 */

@TeleOp(name = "Olsson Test", group = "In-Test")
public class OlssonTeleOp extends LinearOpMode {
    DcMotor _leftWheelfront;
    DcMotor _rightWheelfront;
    DcMotor _launcherWheelback;
    DcMotor _launcherWheelfront;
    DcMotor _spinner;
    Servo _launcher;
    ModernRoboticsI2cRangeSensor _rangeSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        _leftWheelfront = hardwareMap.dcMotor.get("left_wheelfront");
        _leftWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        _rightWheelfront = hardwareMap.dcMotor.get("right_wheelfront");
        _rightWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        _launcherWheelback = hardwareMap.dcMotor.get("backlauncher");
        _launcherWheelback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        _launcherWheelfront = hardwareMap.dcMotor.get("frontlauncher");
        _launcherWheelfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        _spinner = hardwareMap.dcMotor.get("_spinner");
        _spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        _launcher = hardwareMap.servo.get("launcherservo");

        while(opModeIsActive()) {
            telemetry.update();
            telemetry.addData("(EO) Power to back launcher", _launcherWheelback.getPower());
            telemetry.addData("(EO) Power to front launcher :)", _launcherWheelfront.getPower());
            telemetry.addData("(EO) Power to front right Wheel", _rightWheelfront.getPower());
            telemetry.addData("(EO) Power to front left Wheel", _leftWheelfront.getPower());
            telemetry.addData("(EO) Power to _spinner", _spinner.getPower());
            telemetry.addData("(EO) Position of servo", _launcher.getPosition());
            telemetry.addData("(EO) Rt trigger position", gamepad1.right_trigger);
            telemetry.update();

            double LTriggerPosition = gamepad1.left_trigger;
            if (LTriggerPosition > .001) {
                _launcher.setPosition(0.4);
            } else {
                _launcher.setPosition(0.9);
            }
            if (gamepad1.dpad_left) {
                _spinner.setPower(1);
            }
            if (gamepad1.dpad_right) {
                _spinner.setPower(0);
            }
            double drive1 = gamepad1.right_stick_y;        //declaring the stick values
            double drive2 = gamepad1.right_stick_x;
            double drive3 = gamepad1.right_trigger;

            _leftWheelfront.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);
            _rightWheelfront.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);


            //only front wheel drive
            //very smooth drive
            if (gamepad1.dpad_down) {
                _launcherWheelback.setPower(-1);
                _launcherWheelfront.setPower(-1);
            }
            if(gamepad1.dpad_up) {
                _launcherWheelback.setPower(0);
                _launcherWheelfront.setPower(0);
            }
        }
    }
}
