package com.spartronics4915.frc.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.spartronics4915.frc.PhotonCameraWrapper;

public class DriveModeCommands extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private PhotonCameraWrapper mCam;
	private boolean mNewDriveMode;

	public DriveModeCommands(PhotonCameraWrapper cameraWrapper) {
		mCam = cameraWrapper;

	}

	public class SetDriveMode extends InstantCommand {

		public void SetDriveMode(boolean newDriveMode) {
			mNewDriveMode = newDriveMode;

	        NetworkTableInstance netInst = NetworkTableInstance.getDefault();
			NetworkTable photTab = netInst.getTable("photonvision");
			NetworkTable camSub = photTab.getSubTable(mCam.photonCamera.getName());
			BooleanPublisher drivReq = camSub.getBooleanTopic("driverModeRequest").publish();
			BooleanPublisher drivMod = camSub.getBooleanTopic("driverMode").publish();
			BooleanSubscriber currReq = camSub.getBooleanTopic("driverModeRequest").subscribe(false);
			BooleanSubscriber currMod = camSub.getBooleanTopic("driverMode").subscribe(false);
			System.out.println("Dreq: " + currReq.get());
			System.out.println("DMod: " + currMod.get());
			drivMod.set(mNewDriveMode);
			drivReq.set(mNewDriveMode);
			System.out.println("Dreq2: " + currReq.get());
			System.out.println("DMod2: " + currMod.get());
		}
	}
}
