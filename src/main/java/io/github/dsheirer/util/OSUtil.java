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

package io.github.dsheirer.util;

import java.util.Locale;

/**
 * Operating System utilities
 */
public class OSUtil
{
    public enum OSType {WINDOWS_32, WINDOWS_64, OSX_64, OSX_32, LINUX_32, LINUX_64, UNKNOWN}

    public static OSType getOSType()
    {
        String os = System.getProperty("os.name", "unknown").toLowerCase(Locale.ENGLISH);
        String arch = System.getProperty("os.arch");

        if(os.contains("win"))
        {
            if(arch.contains("64"))
            {
                return OSType.WINDOWS_64;
            }
            else
            {
                return OSType.WINDOWS_32;
            }
        }

        if(os.contains("mac") || os.contains("darwin"))
        {
            if(arch.contains("64"))
            {
                return OSType.OSX_64;
            }
            else
            {
                return OSType.OSX_32;
            }
        }

        if(os.contains("nux") || os.contains("nix") || os.contains("aix"))
        {
            if(arch.contains("64"))
            {
                return OSType.LINUX_64;
            }
            else
            {
                return OSType.LINUX_32;
            }
        }

        return OSType.UNKNOWN;
    }
}
