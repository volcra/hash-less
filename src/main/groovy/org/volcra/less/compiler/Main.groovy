package org.volcra.less.compiler

/**
 * <p>Main class.</p>
 * <p>Parses the command line arguments and runs the Less compiler.</p>
 */
class Main {
    /**
     * Command Line.
     */
    private static cli = new CommandLine()

    /**
     * Less Compiler.
     */
    @Lazy
    private static compiler = LessCompiler.instance

    /**
     * Parsers the command line arguments and runs the Less compiler.
     */
    static void main(String... args) {
        def options = cli.parse(args);

        if (options.arguments().isEmpty() || options.h) {
            cli.usage()
        } else {
            def writer = new StringWriter()
            compiler.compile new FileReader(options.arguments()[0]), writer, options.c

            if (options.p) {
                print writer
            } else {
                def fileName = options.arguments()[0].replace ".less", ".css"

                new File(fileName).withWriter { out ->
                    out.writeLine writer.toString()
                }
            }
        }
    }
}