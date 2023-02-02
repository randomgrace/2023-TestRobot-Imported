package com.spartronics4915.frc.commands;

import com.spartronics4915.frc.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static com.spartronics4915.frc.Constants.ExampleSubsystem.*;
import static com.spartronics4915.frc.Constants.OIConstants.*;

public class ExampleCommands {
    private ExampleSubsystem mExampleSubsystem;

    private Joystick mJoystick;

    public ExampleCommands(ExampleSubsystem exampleSubsystem, Joystick joystick) {
        mExampleSubsystem = exampleSubsystem;
        mJoystick = joystick;
    }

    public class ExampleCommand extends CommandBase {
        /**
         * Creates a new ExampleCommand.
         */
        public ExampleCommand() {
            // Use addRequirements() here to declare subsystem dependencies.
            addRequirements(mExampleSubsystem);
        }

        // Called when the command is initially scheduled.
        @Override
        public void initialize() {}

        // Called every time the scheduler runs while the command is scheduled.
        @Override
        public void execute() {
            double y = mJoystick.getY();
            y = -y; // Convention is to invert y because "down" on the joystick provides positive values

            y *= kMotorIsInverted ? -1 : 1;

            y = applyDeadzone(y);
            y = applyResponseCurve(y);

            y *= kOutputMultiplier; // Output needs to be slowed down for test rig

            if (mJoystick.getRawButton(kJoysitckSlowModeButton)) {
                y *= kSlowModeMultiplier;
            }

            mExampleSubsystem.setMotor(y);
        }

        // Called once the command ends or is interrupted.
        @Override
        public void end(boolean interrupted) {}

        // Returns true when the command should end.
        @Override
        public boolean isFinished() {
            return false;
        }
    }

    private double applyDeadzone(double x) {
        return Math.abs(x) > kDeadzone ? x : 0;
    }

    private double applyResponseCurve(double x) {
        return Math.signum(x) * Math.pow(Math.abs(x), kResponseCurveExponent);
    }
}
