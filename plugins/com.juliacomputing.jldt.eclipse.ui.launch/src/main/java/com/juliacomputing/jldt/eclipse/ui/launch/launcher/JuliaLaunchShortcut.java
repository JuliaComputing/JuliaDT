package com.juliacomputing.jldt.eclipse.ui.launch.launcher;

import com.juliacomputing.jldt.eclipse.core.JuliaNature;
import com.juliacomputing.jldt.eclipse.launch.JuliaLaunchConfigurationConstants;

import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.dltk.internal.debug.ui.launcher.AbstractScriptLaunchShortcut;

public class JuliaLaunchShortcut extends AbstractScriptLaunchShortcut {
  protected ILaunchConfigurationType getConfigurationType() {
    return getLaunchManager().getLaunchConfigurationType(
        JuliaLaunchConfigurationConstants.DEFAULT_CONFIGURATION);
  }

  protected String getNatureId() {
    return JuliaNature.ID;
  }
}
