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
 * The unit tests for YAL's default integer type.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class TestIntegral {
    private Integral instantiateEternal() {
        return new Integral("foo", true, 123);
    }

    private Integral instantiateLiteral() {
        return new Integral(null, true, 42);
    }

    private Integral instantiateMutable() {
        return new Integral("bar", false, 23);
    }

    /**
     * Ensure that eternal instances can be described.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testEternalDescription() {
        Assert.assertEquals(instantiateEternal().describe(),
                "'foo' (Integral): eternal 123");
    }

    /**
     * Ensure that an eternal instance's identifier can be retrieved.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testEternalIdentifier() {
        Assert.assertEquals(instantiateEternal().getIdentifier(), "foo");
    }

    /**
     * Ensure eternal instances being eternal.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testEternalMutability() {
        Assert.assertEquals(instantiateEternal().isEternal(), true);
    }

    /**
     * Ensure that eternal instances cannot be altered.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @throws IllegalAccessException
     *             if the code is correct.
     */
    @Test(expectedExceptions = {IllegalAccessException.class})
    void testEternalSanityCheckRedefinition() throws IllegalAccessException {
        instantiateEternal().redefine(0);
    }

    /**
     * Ensure that eternal instances cannot be altered.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @throws IllegalAccessException
     *             if the code is correct.
     */
    @Test(expectedExceptions = {IllegalAccessException.class})
    void testEternalSanityCheckValueSetter() throws IllegalAccessException {
        instantiateEternal().setValue(0);
    }

    /**
     * Ensure that eternal instances have a value.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testEternalValue() {
        Assert.assertEquals(instantiateEternal().getValue(), 123);
    }

    /**
     * Ensure that literals can be described.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testLiteralDescription() {
        Assert.assertEquals(instantiateLiteral().describe(),
                "literal (Integral): eternal 42");
    }

    /**
     * Ensure that literals do not have any identifier.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testLiteralIdentifier() {
        Assert.assertEquals(instantiateLiteral().getIdentifier(), null);
    }

    /**
     * Ensure that literals are always eternal.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testLiteralMutability() {
        Assert.assertEquals(instantiateLiteral().isEternal(), true);
    }

    /**
     * Ensure that literals cannot be mutable.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @throws IllegalAccessException
     *             if the code is correct.
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    void testLiteralSanityCheckMutability() throws IllegalArgumentException {
        new Integral(null, false, 0);
    }

    /**
     * Ensure that literals cannot be redefined.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @throws IllegalAccessException
     *             if the code is correct.
     */
    @Test(expectedExceptions = {IllegalAccessException.class})
    void testLiteralSanityCheckRedefinition() throws IllegalAccessException {
        instantiateLiteral().redefine(0);
    }

    /**
     * Ensure that literals cannot be redefined.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @throws IllegalAccessException
     *             if the code is correct.
     */
    @Test(expectedExceptions = {IllegalAccessException.class})
    void testLiteralSanityCheckValueSetter() throws IllegalAccessException {
        instantiateLiteral().setValue(0);
    }

    /**
     * Ensure that literals have a value.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testLiteralValue() {
        Assert.assertEquals(instantiateLiteral().getValue(), 42);
    }

    /**
     * Ensure that mutable instances can be described.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testMutableDescription() {
        Assert.assertEquals(instantiateMutable().describe(),
                "'bar' (Integral): 23");
    }

    /**
     * Ensure that a mutable instance's identifier can be retrieved.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testMutableIdentifier() {
        Assert.assertEquals(instantiateMutable().getIdentifier(), "bar");
    }

    /**
     * Ensure that mutable instances are not eternal.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testMutableMutability() {
        Assert.assertEquals(instantiateMutable().isEternal(), false);
    }

    /**
     * Ensure that mutable instances can be redefined.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @throws IllegalAccessException
     *             if the source code should contain bugs.
     */
    @Test
    void testMutableRedefinition() throws IllegalAccessException {
        Integral i = instantiateMutable();
        Assert.assertEquals(i.getValue(), 23);
        i.redefine(0);
        Assert.assertEquals(i.getValue(), 0);
    }

    /**
     * Ensure that a mutable instance's value can be retrieved.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    void testMutableValue() {
        Assert.assertEquals(instantiateMutable().getValue(), 23);
    }

    /**
     * Ensure that mutable instances can be redefined.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @throws IllegalAccessException
     *             if the source code should contain bugs.
     */
    @Test
    void testMutableValueSetter() throws IllegalAccessException {
        Integral i = instantiateMutable();
        Assert.assertEquals(i.getValue(), 23);
        i.redefine(0);
        Assert.assertEquals(i.getValue(), 0);
    }
}
