package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static com.qualcomm.ftccommon.R.layout.servo;
/**
 * Created by Travis on 1/7/2017.
 */

public class NewDrive extends OpMode {
    DcMotor W0;
    DcMotor W1;
    DcMotor W2;

    @Override
    public void init() {
        W0 = hardwareMap.dcMotor.get("W0");
        W1 = hardwareMap.dcMotor.get("W1");
        W2 = hardwareMap.dcMotor.get("W2");
    }
    @Override
    public void loop(){
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double angle = 90;
        if(x==0){
            if(y>0){
                angle=90;
            } else if (y<0){
                angle=270;
            }
        } else {
            angle=Math.atan(y/x)*180/Math.PI;
        }
        if(angle<=180&&angle>=0){
            W0.setPower((90-angle)/90);
        }else {
            W0.setPower((angle-270)/90);
        }
        //power for wheel 0


        if(angle>=120&&angle<=300){
            W1.setPower((210-angle)/90);
        }else{
            W1.setPower(())
        }




    }

}

