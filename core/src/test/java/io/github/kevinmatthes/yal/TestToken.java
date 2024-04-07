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

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The unit tests for YAL tokens.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class TestToken {
    private Token instantiateIntegralToken() {
        Token token = new Token(1, 1);

        token.push('4');
        token.push('2');
        token.setType(TokenType.Integral);

        return token;
    }

    /**
     * Ensure that a token's lexeme can be previewed.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testGetLexeme() {
        Assert.assertEquals(instantiateIntegralToken().getLexeme(), "42");
    }

    /**
     * Ensure that a token's type can be retrieved.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testGetType() {
        Assert.assertEquals(instantiateIntegralToken().getType(),
                TokenType.Integral);
    }

    /**
     * Ensure that it can be determined whether a token already has a type.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testHasNoTypeYetFalse() {
        Assert.assertEquals(instantiateIntegralToken().hasNoTypeYet(), false);
    }

    /**
     * Ensure that it can be determined whether a token already has a type.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testHasNoTypeYetTrue() {
        Assert.assertEquals(new Token(0, 0).hasNoTypeYet(), true);
    }

    /**
     * Ensure that tokens can be represented as Strings.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testToString() {
        Assert.assertEquals(instantiateIntegralToken().toString(),
                "[Integral 42]@1:1");
    }
}
