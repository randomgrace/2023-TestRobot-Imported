package com.spartronics4915.frc.commands;

import com.spartronics4915.frc.PhotonCameraWrapper;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

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
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
//        while(true) {
            Pose2d result = cam.getEstimatedGlobalPose().getFirst();

            if(result != null) {
//				System.out.println("Got pose: " + result.toString());
                SmartDashboard.putNumber("Pose2D X", result.getX());
                SmartDashboard.putNumber("Pose2D Y", result.getY());
			var latest = cam.photonCamera.getLatestResult();
			if (latest != null) {
				if (latest.hasTargets()) {
					PhotonTrackedTarget target = latest.getBestTarget();
					int targetId = target.getFiducialId();
					SmartDashboard.putNumber("ID", targetId);
					double poseAmb = target.getPoseAmbiguity();
					SmartDashboard.putNumber("Amb", poseAmb);
				}
			}
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
