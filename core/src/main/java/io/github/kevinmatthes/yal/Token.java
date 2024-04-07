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
 * The data structure to describe a token.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class Token {
    /**
     * The recognised lexeme.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    private StringBuilder lexeme;

    /**
     * Preview what was read, so far.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return What was read, so far.
     */
    public String getLexeme() {
        return this.lexeme.toString();
    }

    /**
     * This token's type.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    private TokenType type;

    /**
     * Retrieve this token's type.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return This token's type.
     */
    public TokenType getType() {
        return this.type;
    }

    /**
     * Alter this token's type.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param type
     *            The new type of this token.
     */
    public void setType(final TokenType type) {
        this.type = type;
    }

    /**
     * The line this token was found in.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    final private int line;

    /**
     * The column this token starts at.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    final private int startColumn;

    /**
     * Create a new instance.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param line
     *            The line this token was found in.
     * @param startColumn
     *            The column this token starts at.
     */
    public Token(final int line, final int startColumn) {
        this.type = null;
        this.lexeme = new StringBuilder();
        this.line = line;
        this.startColumn = startColumn;
    }

    /**
     * Whether this token does not already have a type, yet.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return Whether this token does not already have a type, yet.
     */
    public boolean hasNoTypeYet() {
        return this.type == null;
    }

    /**
     * Add another character to this token's lexeme.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param character
     *            The character to append to this token's lexeme.
     */
    public void push(final char character) {
        this.lexeme.append(character);
    }

    /**
     * Convert this token to a String.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return Debugging information regarding this token.
     */
    public String toString() {
        return "[" + this.type + " " + this.lexeme + "]@" + this.line + ":"
                + this.startColumn;
    }
}
