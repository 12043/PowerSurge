package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

/**
 * Created by Travis on 2/9/2017.
 */
@Autonomous(name = "AUTY")
public class AUTY extends LinearOpMode{
    DcMotor leftMotor,rightMotor,backlauncher,frontlauncher;
    Servo Servo;
    @Override public void runOpMode(){
        leftMotor = hardwareMap.dcMotor.get("left_wheelfront");
        rightMotor = hardwareMap.dcMotor.get("right_wheelfront");
        backlauncher = hardwareMap.dcMotor.get("backlauncher");
        frontlauncher = hardwareMap.dcMotor.get("frontlauncher");
        Servo = hardwareMap.servo.get("launcherservo");
        leftMotor.setMode(RUN_USING_ENCODER);
        rightMotor.setMode(RUN_USING_ENCODER);
        waitForStart();
        DriveForwardDistance(.5,1120);
    }
    public void DriveForwardDistance (double power,int distance){

        leftMotor.setMode(STOP_AND_RESET_ENCODER);
        rightMotor.setMode(STOP_AND_RESET_ENCODER);

        leftMotor.setTargetPosition(distance);
        rightMotor.setTargetPosition(distance);

        leftMotor.setMode(RUN_TO_POSITION);
        rightMotor.setMode(RUN_TO_POSITION);

        DriveForward(power);

        while(leftMotor.getCurrentPosition()<distance && rightMotor.getCurrentPosition()<distance){

        }
        StopDriving();


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

}