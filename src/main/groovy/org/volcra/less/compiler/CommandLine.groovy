package org.volcra.less.compiler

/**
 * <p>Helper class that provides access to the command line options.</p>
 */
class CommandLine {
    /**
     * Command Line Builder.
     */
    private def cli

    /**
     * Default constructor.
     */
    CommandLine() {
        cli = new CliBuilder(usage: 'less [options]', header: "Options")

        cli.h longOpt: "help", "Print Help."
        cli.c longOpt: "compress", "Compile the JavaScript without the top-level function safety wrapper."
        cli.j longOpt: "join", args: 1, argName: "FILE", "Before compiling, concatenate all scripts together in the order they were passed, and write them into the specified file. Useful for building large projects."
        cli.o longOpt: "output", args: 1, argName: "DIR", "Write out all compiled JavaScript files into the specified directory."
        cli.p longOpt: "print", "Instead of writing out the JavaScript as a file, print it directly to stdout."
    }

    /**
     * <p>Show the command usage.</p>
     */
    def usage() {
        cli.usage()
    }

    /**
     * Parsers the arguments and returns the options.
     *
     * @param args the arguments to parse
     * @return the options holding the parse result
     */
    def parse(String... args) {
        cli.parse args
    }
}