package com.kelvin101.engine.gui;

import com.kelvin101.engine.files.Global;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Displays a starting GUI for the game where the user chooses the location of a configuration
 * file. If no file is chosen, a default one is used. {@link Global#configFileLocation} is set to the absolute
 * file path of the selected configuration file.
 */
public class ConfigGUI extends JFileChooser
{
    public void display()
    {
        FileFilter filter = new FileNameExtensionFilter("Config File (*.cfg)", "cfg");
        this.addChoosableFileFilter(filter);
        this.setFileFilter(filter);
        this.setDialogTitle("Choose Configuration File for Engine...");

        int returnVal = this.showOpenDialog(this.getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File f = this.getSelectedFile();
            Global.configFileLocation = f.getAbsolutePath();
        }
        else {
            Global.configFileLocation = Global.defaultConfigFileLocation;
        }
    }
}
