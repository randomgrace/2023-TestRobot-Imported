package com.spartronics4915.frc.commands;

import com.spartronics4915.frc.PhotonCameraWrapper;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
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
		cam.photonCamera.setDriverMode(true);
		var initdmode = cam.photonCamera.getDriverMode();
		if (initdmode) {
			SmartDashboard.putString("InitDmode: ", "on");
		} else {
			SmartDashboard.putString("InitDmode: ", "off");
		}
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
//        while(true) {
			
			cam.photonCamera.setDriverMode(true);

            Pose2d result = cam.getEstimatedGlobalPose().getFirst();
			if (result != null) {
				SmartDashboard.putString("Pose: ", result.toString());
			}
			var currPipe = cam.photonCamera.getPipelineIndex();
			SmartDashboard.putNumber("CurrPipe ", currPipe);
//			var nickName = cam.photonCamera.getPipelineNickname(currPipe);
//			SmartDashboard.putString("NickName: ", nickName);
			var dmode = cam.photonCamera.getDriverMode();
			if (dmode) {
				SmartDashboard.putString("Dmode: ", "on");
			} else {
				SmartDashboard.putString("Dmode: ", "off");
			}
			var latestr = cam.photonCamera.getLatestResult();
			SmartDashboard.putString("Pipe: ", latestr.toString());

            if(result != null) {
//				System.out.println("Got pose: " + result.toString());
                SmartDashboard.putNumber("Pose2D X", result.getX());
                SmartDashboard.putNumber("Pose2D Y", result.getY());
				var latest = cam.photonCamera.getLatestResult();
				SmartDashboard.putString("Result: ", latest.toString());

			if (latest != null) {
				if (latest.hasTargets()) {
					PhotonTrackedTarget target = latest.getBestTarget();
					int targetId = target.getFiducialId();
					SmartDashboard.putNumber("ID", targetId);
					double poseAmb = target.getPoseAmbiguity();
					SmartDashboard.putNumber("Amb", poseAmb);
				}
			}
			try {

				Thread.sleep(10);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			cam.photonCamera.setDriverMode(true);
        }
//        }
        
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
