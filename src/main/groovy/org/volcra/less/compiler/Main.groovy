/*
 * Copyright 2013 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.volcra.less.compiler

/**
 * <p>Main class.</p>
 * <p>Parses the command line arguments and runs the Less compiler.</p>
 */
class Main {
    /**
     * Command Line.
     */
    private static final CLI = new CommandLine()

    /**
     * Less Compiler.
     */
    private static final COMPILER = LessCompiler.instance

    /**
     * Parsers the command line arguments and runs the Less compiler.
     */
    static void main(String... args) {
        def options = CLI.parse args

        if (options.arguments().isEmpty() || options.h) {
            CLI.usage()
        } else {
            def file = new File(options.arguments()[0])

            //Output folder
            def outDir = new File(options.o ?: ".")
            if (!outDir.exists() || !outDir.isDirectory()) {
                outDir.mkdir()
            }

            if (options.j) {
                //When print-only, create a temp file where all files will be joined, otherwise create a file
                file = options.p ? File.createTempFile("hash-less-tmp", null) : new File(outDir, options.j as String)

                options.arguments().each {
                    file.append new File(it).text, 'UTF-8'
                }
            }

            def writer = new StringWriter()
            COMPILER.compile file, writer, options.c

            if (options.p) {
                println writer
            } else {
                def fileName = file.name.replace '.less', '.css'
                new File(outDir, fileName).withWriter('UTF-8') {
                    it.writeLine writer.toString()
                }
            }
        }

    }
}
