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

import io.github.dsheirer.jmbe.github.GitHub;
import io.github.dsheirer.jmbe.github.Release;
import io.github.dsheirer.log.ApplicationLog;
import io.github.dsheirer.preference.UserPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A runtime compiler to download the JMBE source code and compile into a library.
 */
public class JMBERuntimeCompiler
{
    private final static Logger mLog = LoggerFactory.getLogger(JMBERuntimeCompiler.class);
    private final static String GITHUB_JMBE_RELEASES_URL = "https://api.github.com/repos/dsheirer/jmbe/releases";
    private final static String TEMPORARY_DIRECTORY = "/home/denny/temp";

    public static void main(String[] args)
    {
        ApplicationLog mApplicationLog = new ApplicationLog(new UserPreferences());
        mApplicationLog.start();

        Release latest = GitHub.getLatestRelease(GITHUB_JMBE_RELEASES_URL);

        if(latest != null)
        {
            mLog.debug("Latest Release: " + latest.toString());
            mLog.debug(latest.getJsonObject().toString());
            Path temporaryDirectory = Paths.get(TEMPORARY_DIRECTORY);
            Path download = GitHub.downloadReleaseSourceCode(latest, temporaryDirectory);

            if(download != null)
            {
                mLog.debug("Downloaded to: " + download.toString());
            }
        }
        mLog.info("Complete");
    }
}
