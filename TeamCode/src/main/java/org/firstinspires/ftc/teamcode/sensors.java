package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRGyro;

/**
 * Created by Travis on 12/8/2016.
 */
@TeleOp(name = "sensor")
public class sensors extends OpMode {
        ColorSensor color1;
        ModernRoboticsI2cGyro gyro1;
        @Override
        public void init() {
                color1 = hardwareMap.colorSensor.get("color");
                gyro1 = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("the_gyro");
            gyro1.calibrate();
            if (gyro1.isCalibrating()){
                telemetry.addData("gyro is calibrating",gyro1.isCalibrating());
            }else{
                telemetry.addData("gyro is ready",gyro1.isCalibrating());
            }

        }

        @Override
        public void start() {

        }
        @Override
        public void loop(){
                color1.enableLed(false);



                telemetry.update();
                telemetry.addData("alpha value", color1.alpha());
                telemetry.addData("argb value",color1.argb());
                telemetry.addData("blue value",color1.blue());
                telemetry.addData("red value",color1.red());
                telemetry.addData("green value",color1.green());
                telemetry.addData("heading value",gyro1.getHeading());
                telemetry.addData("integrated Z value",gyro1.getIntegratedZValue());

        }
        @Override
        public void stop() {

        }

    }


