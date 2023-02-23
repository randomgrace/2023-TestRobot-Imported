package com.spartronics4915.frc.commands;

import com.spartronics4915.frc.PhotonCameraWrapper;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.io.*;
import java.lang.Thread;

/** An example command that uses an example subsystem. */
public class PrintPos extends CommandBase {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private PhotonCameraWrapper cam;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public PrintPos(PhotonCameraWrapper cameraWrapper) {
        cam = cameraWrapper;

        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
		System.out.println("**** PrintPos init");
        var camNam = cam.photonCamera.getName();
		NetworkTableInstance netInst = NetworkTableInstance.getDefault();
		NetworkTable photTab = netInst.getTable("photonvision");
		NetworkTable camSub = photTab.getSubTable(cam.photonCamera.getName());
//		cam.photonCamera.setDriverMode(true);
		System.out.println("Cam sub" + camSub);

        BooleanPublisher drivReq = camSub.getBooleanTopic("driverModeRequest").publish();
		BooleanPublisher drivMod = camSub.getBooleanTopic("driverMode").publish();
		BooleanSubscriber currReq = camSub.getBooleanTopic("driverModeRequest").subscribe(false);
		BooleanSubscriber currMod = camSub.getBooleanTopic("driverMode").subscribe(false);
		System.out.println("Dreq: " + currReq.get());
    	System.out.println("DMod: " + currMod.get());
		drivMod.set(true);
		drivReq.set(true);
		System.out.println("Dreq2: " + currReq.get());
		System.out.println("DMod2: " + currMod.get());
////		cam.photonCamera.driverModeRequest(true);
////		cam.photonCamera.setPipelineIndex(-1);
		var dPipe = cam.photonCamera.getPipelineIndex();
		System.out.println("Dpipe: " + dPipe);
		var initdmode = cam.photonCamera.getDriverMode();
		if (initdmode) {
			SmartDashboard.putString("InitDmode: ", "on");
		} else {
			SmartDashboard.putString("InitDmode: ", "off");
		}
//		for (int i = 0; i < 20; i++) {
//			System.out.println("Dreq3: " + currReq.get());
//			System.out.println("DMod3: " + currMod.get());
//		}


    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
			
//			cam.photonCamera.setDriverMode(true);
			var currPipe = cam.photonCamera.getPipelineIndex();
			SmartDashboard.putNumber("CurrPipe ", currPipe);
			var dmode = cam.photonCamera.getDriverMode();
			if (dmode) {
				SmartDashboard.putString("Dmode: ", "on");
			} else {
				SmartDashboard.putString("Dmode: ", "off");
			}
        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
		NetworkTableInstance netInst = NetworkTableInstance.getDefault();
		NetworkTable photTab = netInst.getTable("photonvision");
		NetworkTable camSub = photTab.getSubTable(cam.photonCamera.getName());
//		cam.photonCamera.setDriverMode(true);
		System.out.println("Cam sub" + camSub);

        BooleanPublisher drivReq = camSub.getBooleanTopic("driverModeRequest").publish();
		BooleanPublisher drivMod = camSub.getBooleanTopic("driverMode").publish();
		drivMod.set(false);
		drivReq.set(false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
