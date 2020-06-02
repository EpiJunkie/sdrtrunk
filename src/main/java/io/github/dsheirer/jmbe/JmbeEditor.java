/*
 * *****************************************************************************
 *  Copyright (C) 2014-2020 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

package io.github.dsheirer.jmbe;

import io.github.dsheirer.jmbe.github.Release;
import io.github.dsheirer.preference.UserPreferences;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Editor pane for JMBE Library creator
 */
public class JmbeEditor extends BorderPane
{
    private UserPreferences mUserPreferences;
    private Release mCurrentRelease;

    private Label mCurrentVersion;

    /**
     * Constructs an instance
     * @param userPreferences
     */
    public JmbeEditor(UserPreferences userPreferences)
    {
        mUserPreferences = userPreferences;

        setCenter(new Label("Jmbe Updater"));
    }

    /**
     * Processes a request to configure the editor to build the release version specified in the request.
     * @param request to build a specific release version
     */
    public void process(JmbeEditorRequest request)
    {
        if(request != null)
        {
            mCurrentRelease = request.getCurrentRelease();
        }
    }

    private Label getCurrentVersion()
    {
        if(mCurrentVersion == null)
        {
            mCurrentVersion = new Label();
        }

        return mCurrentVersion;
    }

    /**
     * Current release version available from GitHub.
     * @return current release or null if the release hasn't been set.
     */
    private Release getCurrentRelease()
    {
        return mCurrentRelease;
    }

    /**
     * Sets the current release version available from GitHub.
     * @param release version
     */
    public void setCurrentRelease(Release release)
    {
        mCurrentRelease = release;
    }
}
