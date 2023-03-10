// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.spartronics4915.frc;

import java.util.function.Function;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class OIConstants {
        public static final int kJoystickID = 0;

        public static final int kJoysitckSlowModeButton = 6;
    }

    public static final class ExampleSubsystem {
        public static final int kMotorID = 1;

        public static final boolean kMotorIsInverted = true;
        public static final double kOutputMultiplier = 0.3;
        public static final double kSlowModeMultiplier = 0.3;

        public static final double kDeadzone = 0.05;
        public static final double kResponseCurveExponent = 5.0 / 3.0;
    }
    public static final class FieldConstants {
        static final double length = Units.feetToMeters(54);
        static final double width = Units.feetToMeters(27);
    }

    public static final class VisionConstants {
        static final Transform3d robotToCam =
                new Transform3d(
                        new Translation3d(0.5, 0.0, 0.5),
                        new Rotation3d(
                                0, 0,
                                0)); // Cam mounted facing forward, half a meter forward of center, half a meter up
        // from center.
        // Limelight
		// static final String cameraName = "OV5647";
        // static final String cameraName = "HD_Pro_Webcam_C920";
        static final String cameraName = "Arducam_OV9281_USB_Camera";
    }
}
