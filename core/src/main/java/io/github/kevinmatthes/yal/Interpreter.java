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
import java.util.Iterator;

/**
 * The YAL interpreter.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class Interpreter {
    /**
     * The tokens to be processed. In case of an error, the current stack will
     * be dumped to stderr.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    final private ArrayList<Token> instructionStack;

    /**
     * Create a new instance.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    public Interpreter() {
        this.instructionStack = new ArrayList<>();
    }

    private boolean clearStack() {
        for (Token token : this.instructionStack) {
            System.err.println(token.toString());
        }

        this.instructionStack.clear();

        return false;
    }

    private boolean describeIntegralInstance(final Token token)
            throws NumberFormatException {
        System.out.println(
                new Integral(null, true, Integer.parseInt(token.getLexeme()))
                        .describe());
        this.instructionStack.clear();

        return true;
    }

    private boolean handleDescribeInstruction(final Iterator<Token> token) {
        if (token.hasNext()) {
            Token next = token.next();
            this.instructionStack.add(next);

            switch (next.getType()) {
                case Integral :
                    return this.describeIntegralInstance(next);

                default :
                    return this.clearStack();
            }
        } else
            return this.clearStack();
    }

    private boolean handleInstructionToken(final Iterator<Token> token) {
        if (token.hasNext()) {
            Token next = token.next();
            this.instructionStack.add(next);

            switch (next.getType()) {
                case Describe :
                    return this.handleDescribeInstruction(token);

                default :
                    return this.clearStack();
            }
        } else
            return true;
    }

    /**
     * Execute the given source code.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param code
     *            The source code to execute.
     * @return Whether the entire code could be executed successfully.
     */
    public boolean run(String code) {
        Lexer lexer = new Lexer();
        lexer.lex(code);
        Iterator<Token> token = lexer.getTokens().iterator();
        boolean success = true;

        while (token.hasNext()) {
            Token next = token.next();
            this.instructionStack.add(next);

            switch (next.getType()) {
                case Instruction :
                    success &= this.handleInstructionToken(token);
                    break;

                default :
                    success &= this.clearStack();
                    break;
            }
        }

        return success;
    }
}
