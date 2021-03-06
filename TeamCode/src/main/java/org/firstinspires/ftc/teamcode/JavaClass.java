package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.ftccommon.R.layout.servo;

/**
 * Created by Travis on 12/8/2016.
 * A new comment
 */
@TeleOp(name = "BestOpMode", group = "Awesome")
public class JavaClass extends OpMode {
    DcMotor leftWheelfront;
    DcMotor rightWheelfront;
    DcMotor rightWheelback;
    DcMotor leftWheelback;
    DcMotor launcherWheelback;
    DcMotor launcherWheelfront;
    DcMotor spinner;
    Servo launcher;
    Servo LeftPusher,RightPusher;
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
        RightPusher = hardwareMap.servo.get("RightPusher");
        LeftPusher = hardwareMap.servo.get("LeftPusher");
        LeftPusher.setPosition(0);
        RightPusher.setPosition(1);
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
        telemetry.addData("left pusher position", LeftPusher.getPosition());
        telemetry.addData("right pusher position", RightPusher.getPosition());
        telemetry.update();

        double drive1 = gamepad1.right_stick_y;//currently unused in program
        double drive2 = gamepad1.right_stick_x;//currently unused in program
        double drive3 = gamepad1.right_trigger;//currently unused in program
        
        setMovement_3();
        setParticleInteraction_1();
        
        
    }//loop()
    public void setMovement_1()
    {
        //only front wheel drive
        //very smooth drive
        rightWheelfront.setPower(gamepad1.right_stick_y);
        leftWheelfront.setPower(gamepad1.left_stick_y);
        leftWheelback.setPower(gamepad1.right_stick_y);
        rightWheelback.setPower(-gamepad1.left_stick_y);
    }//setMovement_1()
    
    public void setMovement_2()//used at first three competitions
    {
        leftWheelfront.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);
        rightWheelfront.setPower(gamepad1.left_stick_y-gamepad1.right_stick_x);
    }
    public void setMovement_3()//smooth turning method
    {
        //drive functions
        if((gamepad1.left_stick_y < .1 && gamepad1.left_stick_y > -.1) && (gamepad1.right_stick_x != 0))
        {
            leftWheelfront.setPower(gamepad1.right_stick_x);
            rightWheelfront.setPower(gamepad1.right_stick_x);
        }
        else if(gamepad1.right_stick_x < .1 && gamepad1.right_stick_x > -.1)
        {
            leftWheelfront.setPower(-gamepad1.left_stick_y);
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
    }//setMovement_3
    public void setParticleInteraction_1()
    {//This method used the first 3 competitions
        if (gamepad1.a) {
            launcher.setPosition(.5);
        } else {
            launcher.setPosition(0);
        }
        
        if (gamepad1.dpad_left) {
            spinner.setPower(-1);
        }
        if (gamepad1.dpad_right) {
            spinner.setPower(0);
        }

        if (gamepad1.dpad_down) {
            launcherWheelback.setPower(-1);
            launcherWheelfront.setPower(1);
        }
        if(gamepad1.dpad_up) {
            launcherWheelback.setPower(0);
            launcherWheelfront.setPower(0);
        }
        if(gamepad1.y){
            LeftPusher.setPosition(1);
            RightPusher.setPosition(0);
        }
        if(gamepad1.x){
            LeftPusher.setPosition(0);
            RightPusher.setPosition(1);
        }
    }//setParticleInteraction_1()
    
    public void setParticleInteraction_2()
    {
        //we want to use the left bumper to collect balls
        //we want to use the right bumper to push away enemy particles
        //we want to use the right trigger to fire
        //we want to use the left trigger to prep for firing and to stop the sweeper
        //the firing function is the most important, so we should make the right trigger override all other inputs
        //the preparing to fire function is the second most important, so we should make the left trigger override all
        //inputs other than the right trigger
        //
        if(gamepad1.right_trigger>0)
        {
            spinner.setPower(0);
            launcherWheelback.setPower(-1);
            launcherWheelfront.setPower(-1);
            launcher.setPosition(0.4);
        }
        else if(gamepad1.left_trigger>0)
        {
            spinner.setPower(0);
            launcherWheelback.setPower(0);
            launcherWheelfront.setPower(0);
        }
        else if(gamepad1.left_bumper)
        {
            spinner.setPower(1);
        }
        else if(gamepad1.left_bumper)
        {
            spinner.setPower(-1);
        }
        if(gamepad1.right_trigger ==0)
        {
            launcher.setPosition(0.9);
        }


    }//setParticleInteraction_2()
    
}//JavaClass



