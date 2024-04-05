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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main class to start the interpreter.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class Yal {
    /**
     * The main method.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param args
     *            The command line arguments.
     *
     */
    public static void main(String[] args) throws IOException {
        repl();
    }

    private static void repl() throws IOException {
        InputStreamReader i = new InputStreamReader(System.in);
        BufferedReader r = new BufferedReader(i);

        for (;;) {
            System.out.print("> ");
            final String l = r.readLine();

            if (l == null || l.equals(":quit"))
                break;
            else
                run(l);
        }
    }

    private static void run(String code) {
        System.out.println(code);
    }
}
