// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.spartronics4915.frc.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.spartronics4915.frc.Constants.ExampleSubsystem.*;

public class ExampleSubsystem extends SubsystemBase {
    private CANSparkMax mMotor;
    
    /** Creates a new ExampleSubsystem. */
    public ExampleSubsystem() {
        mMotor = new CANSparkMax(kMotorID, MotorType.kBrushless);
    }

    /**
     * Sets the speed of the ExampleSubsystem's motor.
     * @param speed the speed to set the motor to.
     */
    public void setMotor(double speed) {
        mMotor.set(speed);
        //Comment test
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
