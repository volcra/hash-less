package org.volcra.less.compiler

import org.mozilla.javascript.Context
import org.mozilla.javascript.tools.shell.Global

/**
 * Less compiler.
 */
@Singleton(lazy = true)
class LessCompiler {
    /**
     * Global Scope.
     */
    private static def globalScope
    private static def compileCss

    private static def envRhino =
        getClass().getResource("/org/volcra/less/env.rhino.1.2.js").text

    private static def less =
        getClass().getResource("/org/volcra/less/less-1.3.3.min.js").text

    private static def compiler =
        getClass().getResource("/org/volcra/less/compiler.min.js").text

    /**
     * Default Constructor.
     */
    private LessCompiler() {
        withContext { context ->
            context.optimizationLevel = -1

            def global = new Global()
            global.init context as Context

            globalScope = context.initStandardObjects global
            context.evaluateString globalScope, envRhino, "env.rhino.js", 1, null
            context.evaluateString globalScope, less, "less.js", 1, null

            compileCss = context.compileFunction(globalScope, compiler, "compiler.js", 1, null)
        }
    }

    /**
     * Higher Order function that provides a context to the closure being passed.
     *
     * @param c the function or closure to execute
     */
    def static withContext(Closure c) {
        def context = Context.enter()

        try {
            c context
        } finally {
            Context.exit()
        }
    }

    /**
     * Uses the reader to get the Less code and the writer to store the resulted CSS code.
     *
     * @param reader
     * @param writer
     * @param compress
     * @return the writer with the resulted code after the compilation
     */
    def static compile(Reader reader, Writer writer, Boolean compress = false) {
        withContext { context ->
            def script = compileCss.call(context, globalScope, null, [reader.text, compress] as Object[])

            writer << script
            writer.flush()
        }
    }
}