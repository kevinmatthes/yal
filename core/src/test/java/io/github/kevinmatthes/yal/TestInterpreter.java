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
 * The unit tests for YAL's interpreter.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class TestInterpreter {
    /**
     * Ensure that a description call requires something to describe.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testInvalidInstructionDescribeEof() {
        Assert.assertEquals(new Interpreter().run("- describe"), false);
    }

    /**
     * Ensure that error tokens cannot be described.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testInvalidInstructionDescribeError() {
        Assert.assertEquals(new Interpreter().run("- describe error"), false);
    }

    /**
     * Ensure that instructions consisting of nothing but instances are not
     * permitted.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testInvalidInstructionIntegral() {
        Assert.assertEquals(new Interpreter().run("- 42"), false);
    }

    /**
     * Ensure that instructions need to start with a dash.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testInvalidNoInstructionTokenAtStartOfLine() {
        Assert.assertEquals(new Interpreter().run("describe 42"), false);
    }

    /**
     * Ensure that integral numbers can be described.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testValidInstruction1ValidInstruction() {
        Assert.assertEquals(new Interpreter().run("- describe 42"), true);
    }

    /**
     * Ensure that multiple description statements are permitted.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testValidInstruction2ValidInstructions() {
        Assert.assertEquals(
                new Interpreter().run("- describe 42\n- describe 23"), true);
    }

    /**
     * Ensure that empty instructions are permitted.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     */
    @Test
    public void testValidInstructionEmptyInstruction() {
        Assert.assertEquals(new Interpreter().run("-"), true);
    }
}
