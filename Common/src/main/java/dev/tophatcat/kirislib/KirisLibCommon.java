/*
 * KirisLib - https://github.com/tophatcats-mods/kiris-lib
 * Copyright (C) 2013-2022 <KiriCattus>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation;
 * Specifically version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 * https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */
package dev.tophatcat.kirislib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Our common class with shared resources for the mod on Forge and Quilt.
 */
public class KirisLibCommon {

    /**
     * The mods ID used to register itself, and it's content.
     */
    public static final String MOD_ID = "kirislib";

    /**
     * The mods readable name used for logging.
     */
    public static final String MOD_NAME = "Kiris Lib";

    /**
     * Our logger for logging the goings-on of the mod.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    /**
     * The entry point for loading common code.
     */
    public KirisLibCommon() {
        CosmeticFeatures.initCosmetics();
    }
}
