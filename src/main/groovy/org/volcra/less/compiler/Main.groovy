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