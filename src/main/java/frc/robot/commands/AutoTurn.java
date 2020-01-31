package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class AutoTurn extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final TankDrive tankDriveSubsystem;
  private double angle;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public AutoTurn(TankDrive subsystem, double pAngle) {
        tankDriveSubsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
        angle = pAngle;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        RobotContainer.tankDriveSubsystem.ahrs.reset();
        tankDriveSubsystem.turnSetpoint(angle);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        tankDriveSubsystem.updateTurn();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    tankDriveSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.tankDriveSubsystem.gyroPid.atSetpoint();
  }
}