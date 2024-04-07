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

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The unit tests for YAL's lexer.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class TestLexer {
    private Lexer instantiateLexer() {
        Lexer lexer = new Lexer();
        lexer.lex("- describe\n  42");
        return lexer;
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction1() {
        Lexer lexer = new Lexer();
        lexer.lex("·");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction2() {
        Lexer lexer = new Lexer();
        lexer.lex("d0");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction3() {
        Lexer lexer = new Lexer();
        lexer.lex("0d");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction4() {
        Lexer lexer = new Lexer();
        lexer.lex("0e");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction5() {
        Lexer lexer = new Lexer();
        lexer.lex("0s");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction6() {
        Lexer lexer = new Lexer();
        lexer.lex("0c");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction7() {
        Lexer lexer = new Lexer();
        lexer.lex("0r");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction8() {
        Lexer lexer = new Lexer();
        lexer.lex("0i");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer can also produce error tokens.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testErrorTokenConstruction9() {
        Lexer lexer = new Lexer();
        lexer.lex("0b");
        Assert.assertEquals(lexer.getTokens().get(0).getType(),
                TokenType.Error);
    }

    /**
     * Ensure that a lexer's tokens can be retrieved.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testGetTokens() {
        ArrayList<Token> test = instantiateLexer().getTokens();
        ArrayList<Token> tokens = new ArrayList<>();
        Token describe = new Token(1, 3);
        Token fourtyTwo = new Token(2, 3);
        Token instruction = new Token(1, 1);

        describe.push('d');
        describe.push('e');
        describe.push('s');
        describe.push('c');
        describe.push('r');
        describe.push('i');
        describe.push('b');
        describe.push('e');
        describe.setType(TokenType.Describe);

        fourtyTwo.push('4');
        fourtyTwo.push('2');
        fourtyTwo.setType(TokenType.Integral);

        instruction.push('-');
        instruction.setType(TokenType.Instruction);

        tokens.add(instruction);
        tokens.add(describe);
        tokens.add(fourtyTwo);

        for (int i = 0; i < test.size() && i < tokens.size(); i++)
            Assert.assertEquals(test.get(i).toString(),
                    tokens.get(i).toString());
    }

    /**
     * Ensure that a lexer's tokens can be converted to a String.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testLex() {
        Assert.assertEquals(instantiateLexer().toString(),
                "[Instruction -]@1:1\n[Describe describe]@1:3\n[Integral 42]@2:3\n");
    }
}
