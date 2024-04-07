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

import java.util.ArrayList;

/**
 * YAL's default tokeniser.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class Lexer {
    /**
     * The recently detected tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    private ArrayList<Token> tokens;

    /**
     * Retrieve the recently detected tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @return The recently detected tokens.
     */
    public ArrayList<Token> getTokens() {
        return this.tokens;
    }

    /**
     * A work-in-progress token.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    private Token pending;

    /**
     * The current column.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    private int column;

    /**
     * The current line.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    private int line;

    /**
     * Create a new instance.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    public Lexer() {
        this.column = 1;
        this.line = 1;
        this.pending = null;
        this.tokens = new ArrayList<>();
    }

    private void assumeTypeIfNull(final TokenType type) {
        if (this.pending.hasNoTypeYet())
            this.pending.setType(type);
    }

    private void breakLine() {
        this.finishToken();
        this.line++;
        this.column = 1;
    }

    private void consume(final char character) {
        if (this.pending != null)
            this.pending.push(character);

        this.column++;
    }

    private void convertToErrorToken(final char character) {
        this.createTokenIfNull();
        this.pending.setType(TokenType.Error);
        this.consume(character);
    }

    private void createTokenIfNull() {
        if (this.pending == null)
            this.pending = new Token(this.line, this.column);
    }

    private void finishToken() {
        if (this.pending != null) {
            this.tokens.add(this.pending);
            this.pending = null;
        }
    }

    /**
     * Turn the given source code into tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param text
     *            The source code to process.
     */
    public void lex(final String text) {
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            switch (current) {
                case '\n' :
                    this.breakLine();
                    break;

                case ' ' :
                    this.finishToken();
                    this.consume(current);
                    break;

                case '-' :
                    this.finishToken();
                    this.createTokenIfNull();
                    this.assumeTypeIfNull(TokenType.Instruction);
                    this.consume(current);
                    this.finishToken();
                    break;

                case '0' :
                case '1' :
                case '2' :
                case '3' :
                case '4' :
                case '5' :
                case '6' :
                case '7' :
                case '8' :
                case '9' :
                    this.createTokenIfNull();
                    this.assumeTypeIfNull(TokenType.Integral);

                    switch (this.pending.getType()) {
                        case Integral :
                            this.consume(current);
                            break;

                        default :
                            this.convertToErrorToken(current);
                    }
                    break;

                case 'b' :
                    this.createTokenIfNull();

                    if (this.pending.getLexeme().equals("descri"))
                        this.consume(current);
                    else
                        this.convertToErrorToken(current);
                    break;

                case 'c' :
                    this.createTokenIfNull();

                    if (this.pending.getLexeme().equals("des"))
                        this.consume(current);
                    else
                        this.convertToErrorToken(current);
                    break;

                case 'd' :
                    this.createTokenIfNull();

                    if (this.pending.getLexeme().equals("")) {
                        this.assumeTypeIfNull(TokenType.Error);
                        this.consume(current);
                    } else
                        this.convertToErrorToken(current);
                    break;

                case 'e' :
                    this.createTokenIfNull();

                    if (this.pending.getLexeme().equals("d"))
                        this.consume(current);
                    else if (this.pending.getLexeme().equals("describ")) {
                        this.consume(current);
                        this.pending.setType(TokenType.Describe);
                        this.finishToken();
                    } else
                        this.convertToErrorToken(current);
                    break;

                case 'i' :
                    this.createTokenIfNull();

                    if (this.pending.getLexeme().equals("descr"))
                        this.consume(current);
                    else
                        this.convertToErrorToken(current);
                    break;

                case 'r' :
                    this.createTokenIfNull();

                    if (this.pending.getLexeme().equals("desc"))
                        this.consume(current);
                    else
                        this.convertToErrorToken(current);
                    break;

                case 's' :
                    this.createTokenIfNull();

                    if (this.pending.getLexeme().equals("de"))
                        this.consume(current);
                    else
                        this.convertToErrorToken(current);
                    break;

                default :
                    this.convertToErrorToken(current);
                    break;
            }
        }

        this.finishToken();
    }

    /**
     * Report the recently detected tokens as a String.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("");

        for (Token token : this.tokens) {
            result.append(token.toString() + "\n");
        }

        return result.toString();
    }
}
