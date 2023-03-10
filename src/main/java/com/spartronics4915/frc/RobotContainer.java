// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.spartronics4915.frc;

import com.spartronics4915.frc.commands.ExampleCommands;
import com.spartronics4915.frc.subsystems.ExampleSubsystem;
import com.spartronics4915.frc.commands.PrintPos;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import static com.spartronics4915.frc.Constants.OIConstants.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final Joystick mJoystick;

    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem mExampleSubsystem;
    private final ExampleCommands mExampleCommands;

    private final Command mAutoCommand;
    private Command mTeleopCommand;
	private Command mTestingCommand;

	private PhotonCameraWrapper cameraWrapper = new PhotonCameraWrapper();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        mJoystick = new Joystick(kJoystickID);

        mExampleSubsystem = new ExampleSubsystem();
        mExampleCommands = new ExampleCommands(mExampleSubsystem, mJoystick);

        mAutoCommand = null;
        mTeleopCommand = mExampleCommands.new ExampleCommand();

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {}

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return mAutoCommand;
    }

    public Command getTeleopCommand() {
		System.out.println("***** Get teleop command");
		mTeleopCommand = new PrintPos(cameraWrapper);
        return mTeleopCommand;
    }

	public Command getTestingCommand() {
		// System.out.println("***** Get command");
		// mTestingCommand = new PrintPos(cameraWrapper);
		// return mTestingCommand;
		return mTestingCommand;
	}

	public void initRobot() {
		return;
	}
}
