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

import io.github.dsheirer.jmbe.github.Asset;
import io.github.dsheirer.jmbe.github.GitHub;
import io.github.dsheirer.jmbe.github.Release;
import io.github.dsheirer.log.ApplicationLog;
import io.github.dsheirer.preference.UserPreferences;
import io.github.dsheirer.util.OSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility to check for the latest version of JMBE and download the JMBE Creator application from the JMBE
 * releases to compile the latest library.
 */
public class JmbeUpdater
{
    private final static Logger mLog = LoggerFactory.getLogger(JmbeUpdater.class);
    public final static String GITHUB_JMBE_RELEASES_URL = "https://api.github.com/repos/dsheirer/jmbe/releases";

    public static Asset getJMBECreatorAsset()
    {
        Release latest = GitHub.getLatestRelease(GITHUB_JMBE_RELEASES_URL);
        OSUtil.OSType osType = OSUtil.getOSType();

        if(latest != null)
        {
            mLog.debug("Latest Release: " + latest.toString());
            mLog.debug(latest.getJsonObject().toString());

            for(Asset asset: latest.getAssets())
            {
                if(isCorrectAsset(asset, osType))
                {
                    return asset;
                }
            }
        }

        return null;
    }

    /**
     * Indicates if the asset is correct for the host operating system and architecture
     * @param asset to check
     * @param osType for the current host (OS & architecture)
     * @return true if the asset is correct for this host.
     */
    public static boolean isCorrectAsset(Asset asset, OSUtil.OSType osType)
    {
        if(isJMBECreator(asset))
        {
            String name = asset.getName();

            switch(osType)
            {
                case LINUX_32:
                    return name.contains("linux") && name.contains("_32");
                case LINUX_64:
                    return name.contains("linux") && name.contains("_64");
                case OSX_64:
                    return name.contains("osx") && name.contains("_64");
                case WINDOWS_32:
                    return name.contains("windows") && name.contains("_32");
                case WINDOWS_64:
                    return name.contains("windows") && name.contains("_64");
                case UNKNOWN:
                case OSX_32:
                default:
                    return false;
            }
        }

        return false;
    }

    /**
     * Indicates if the GitHub asset has a non-null asset name and is a JMBE Creator asset
     */
    private static boolean isJMBECreator(Asset asset)
    {
        return asset.getName() != null && asset.getName().startsWith("jmbe-creator");
    }

    public static void main(String[] args)
    {
        ApplicationLog mApplicationLog = new ApplicationLog(new UserPreferences());
        mApplicationLog.start();


        mLog.info("Complete");
    }
}
