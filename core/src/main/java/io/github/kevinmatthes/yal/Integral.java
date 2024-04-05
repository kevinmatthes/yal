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
 * The default integer type.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class Integral {
    /**
     * This integer's identifier.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    final private String identifier;

    /**
     * Retrieve this integer's identifier.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return This integer's identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Whether this integer can be altered after instantiation.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    final private boolean isEternal;

    /**
     * Determine whether this integer can be altered after being created.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return
     */
    public boolean isEternal() {
        return isEternal;
    }

    /**
     * This integer's value.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    private int value;

    /**
     * Retrieve this integer's value.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return This integer's value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Attempt to modify this integer's value.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param value
     *            The new value to set, if possible.
     * @throws IllegalAccessException
     *             if this instance is eternal.
     */
    public void setValue(final int value) throws IllegalAccessException {
        if (this.isEternal)
            throw new IllegalAccessException(
                    "eternal instances cannot be modified");

        this.value = value;
    }

    /**
     * Instantiate a new YAL integer.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param identifier
     *            This integer's identifier.
     * @param isEternal
     *            Whether this integer is mutable.
     * @param value
     *            This integer's value.
     * @throws IllegalArgumentException
     *             in case the user attempts to instantiate a mutable literal.
     */
    public Integral(final String identifier, final boolean isEternal,
            final int value) throws IllegalArgumentException {
        if (identifier == null && isEternal == false)
            throw new IllegalArgumentException("literals cannot be mutable");

        this.identifier = identifier;
        this.isEternal = isEternal;
        this.value = value;
    }

    /**
     * Generate a summary of this instance.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return A summary of this instance.
     */
    public String describe() {
        StringBuilder result = new StringBuilder();

        if (this.identifier != null)
            result.append("'" + this.identifier + "' ");
        else
            result.append("literal ");

        result.append("(" + this.getClass().getSimpleName() + "): ");

        if (this.isEternal)
            result.append("eternal ");

        result.append(this.value);

        return result.toString();
    }

    /**
     * Attempt to redefine this instance.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param value
     *            The new value to set, if possible.
     * @throws IllegalAccessException
     *             if this instance is eternal.
     */
    public void redefine(final int value) throws IllegalAccessException {
        this.setValue(value);
    }
}
