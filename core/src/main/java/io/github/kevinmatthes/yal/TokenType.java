/*********************** GNU General Public License 3.0 ***********************\
|                                                                              |
|  Copyright (C) 2024 Kevin Matthes                                            |
|                                                                              |
|  This program is free software: you can redistribute it and/or modify        |
|  it under the terms of the GNU General Public License as published by        |
|  the Free Software Foundation, either version 3 of the License, or           |
|  (at your option) any later version.                                         |
|                                                                              |
|  This program is distributed in the hope that it will be useful,             |
|  but WITHOUT ANY WARRANTY; without even the implied warranty of              |
|  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               |
|  GNU General Public License for more details.                                |
|                                                                              |
|  You should have received a copy of the GNU General Public License           |
|  along with this program.  If not, see <https://www.gnu.org/licenses/>.      |
|                                                                              |
\******************************************************************************/

package io.github.kevinmatthes.yal;

/**
 * The syntax elements YAL supports.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public enum TokenType {
    /**
     * Attempt to describe an instance.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    Describe,

    /**
     * The current lexeme could not be resolved into a valid language element.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    Error,

    /**
     * The begin of a new instruction.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    Instruction,

    /**
     * A YAL integer number.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    Integral,
}
