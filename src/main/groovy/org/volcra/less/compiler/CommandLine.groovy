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
 * <p>Helper class that provides access to the command line options.</p>
 */
class CommandLine {
    /**
     * Command Line Builder.
     */
    private final cli = new CliBuilder(usage: 'less [options]', header: 'Options')

    /**
     * Default constructor.
     */
    CommandLine() {
        cli.with {
            h longOpt: 'help', 'Print Help.'
            c longOpt: 'compress', 'Compile the Less compressing the content.'
            j longOpt: 'join', args: 1,
                argName: 'FILE', 'Before compiling, concatenate all scripts together in the order they were \
                                  passed, and write them into the specified file. Useful for building large projects.'
            o longOpt: 'output', args: 1,
                argName: 'DIR', 'Write out all compiled Less files into the specified directory.'
            p longOpt: 'print', 'Instead of writing out the Less as a file, print it directly to stdout.'
        }
    }

    /**
     * <p>Show the command usage.</p>
     */
    void usage() {
        cli.usage()
    }

    /**
     * Parsers the arguments and returns the options.
     *
     * @param args the arguments to parse
     * @return the options holding the parse result
     */
    OptionAccessor parse(String... args) {
        cli.parse args
    }
}