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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main class to start the interpreter.
 *
 * @author Kevin Matthes
 * @version 0.1.0
 */
public class Yal {
    private static void interpret(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            Interpreter interpreter = new Interpreter();
            String line = null;
            StringBuilder code = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                code.append("\n" + line);
            }

            reader.close();

            if (!interpreter.run(code.toString()))
                System.err.println("There were errors during the execution of "
                        + path + ".");
        } catch (IOException e) {
            System.err.println(path + " does not seem to exist.");
        }
    }

    /**
     * The main method.
     *
     * @author Kevin Matthes
     * @version 0.1.0
     * @param args
     *            The command line arguments.
     * @throws IOException
     *             if the input could not be read.
     */
    public static void main(final String[] args) throws IOException {
        if (args.length == 0)
            repl();
        else
            for (String arg : args)
                interpret(arg);
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

    private static void run(final String code) {
        Interpreter interpreter = new Interpreter();
        interpreter.run(code);
    }
}
