package org.firstinspires.ftc.teamcode;




import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;




@TeleOp(name = "_2027robot (Blocks to Java)")
public class _2027robot extends LinearOpMode {
   private DcMotor frontLeftMotor;
   private DcMotor frontRightMotor;
   private DcMotor backLeftMotor;
   private DcMotor backRightMotor;
   private DcMotor Flywheel;
   private DcMotor intake;
    //this is a test of coding

   private CRServo feeder;




   double leftStickY;
   double leftStickX;
   double rightStickY;
   double rightStickX;




   @Override
   public void runOpMode() {
       frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
       backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
       frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
       backRightMotor = hardwareMap.dcMotor.get("backRightMotor");




       Flywheel = hardwareMap.dcMotor.get("flywheel");


       intake = hardwareMap.dcMotor.get("intake");


       feeder = hardwareMap.crservo.get("feeder");




       frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
       backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);




       waitForStart();




       if (opModeIsActive()){
           while (opModeIsActive()){
               leftStickY = gamepad1.left_stick_y;
               leftStickX = gamepad1.left_stick_x;
               rightStickY = gamepad1.right_stick_y;
               rightStickX = gamepad1.right_stick_x;




//                TankDrive();
//                ArcadeDrive();
               MecanumDrive();
               intake();




               if(gamepad1.x){
                   intake.setPower(1);
               }
               spinFeeder();








           }
       }
   }




   public void TankDrive(){
       frontLeftMotor.setPower(leftStickY);
       backLeftMotor.setPower(leftStickY);
       frontRightMotor.setPower(rightStickY);
       backRightMotor.setPower(rightStickY);
   }




   public void ArcadeDrive(){
       double drive = rightStickY;
       double turn = rightStickX;
       double left = drive+turn;
       double right = drive-turn;
//send power to motor
       frontLeftMotor.setPower(left);
       frontRightMotor.setPower(right);
       backLeftMotor.setPower(left);
       backRightMotor.setPower(right);
   }




   public void MecanumDrive() {
       double drive = -rightStickY;
       double strafe = -rightStickX;
       double turn = leftStickX;




       double flPower = drive + strafe + turn;
       double blPower = drive - strafe + turn;
       double frPower = drive - strafe - turn;
       double brPower = drive + strafe - turn;




       frontLeftMotor.setPower(flPower);
       backLeftMotor.setPower(blPower);
       frontRightMotor.setPower(frPower);
       backRightMotor.setPower(brPower);
   }
   public void intake(){
       if(gamepad1.right_trigger > .5){
           Flywheel.setPower(.5);
       } else if (gamepad1.left_trigger > .5) {
           Flywheel.setPower(-.5);
       }
       else {
           Flywheel.setPower(0);
       }
   }


   public void spinFeeder(){
       if(gamepad1.square){
           feeder.setPower(0);
       }
       else if(gamepad1.circle){
           feeder.setPower(-.25);
       }
   }


   public void spit(){
       if(gamepad1.share){


       }
   }


   public void driveDist(){
       backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      
       backRightMotor.setTargetPosition((int) distToDrive(20));
       backLeftMotor.setTargetPosition((int) distToDrive(20));
       frontRightMotor.setTargetPosition((int) distToDrive(20));
       frontLeftMotor.setTargetPosition((int) distToDrive(20));
      
       backRightMotor.setPower(1);
       backLeftMotor.setPower(1);
       frontRightMotor.setPower(1);
       frontLeftMotor.setPower(1);
      
       backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      
       stopMotors();
   }
  
  
   public void stopMotors(){
       backRightMotor.setPower(0);
       backLeftMotor.setPower(0);
       frontRightMotor.setPower(0);
       frontLeftMotor.setPower(0);
   }
  
   public double distToDrive(double dist){
       return  (537.7*dist)/(3.07*3.141592);


   }


}

