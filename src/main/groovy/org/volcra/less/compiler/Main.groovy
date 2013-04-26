/*
 * Copyright 2013 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
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
    private static final def cli = new CommandLine()

    /**
     * Less Compiler.
     */
    private static final def compiler = LessCompiler.instance

    /**
     * Parsers the command line arguments and runs the Less compiler.
     */
    static void main(String... args) {
        def start = System.currentTimeMillis()
        def options = cli.parse args

        if (options.arguments().isEmpty() || options.h) {
            cli.usage()
        } else {
            def writer = new StringWriter()
            compiler.compile new File(options.arguments()[0]), writer, options.c

            if (options.p) {
                println writer
            } else {
                def fileName = options.arguments()[0].replace ".less", ".css"

                new File(fileName).withWriter("UTF-8") {
                    it.writeLine writer.toString()
                }
            }
        }
        println System.currentTimeMillis() - start
    }
}