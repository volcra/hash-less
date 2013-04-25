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

    /**
     * Compile Function.
     */
    private static def compileFn

    private static final def envRhino =
        getClass().getResource("/org/volcra/less/env.rhino.1.2.js").text

    private static final def less =
        getClass().getResource("/org/volcra/less/less-1.3.3.min.js").text

    private static final def compiler =
        getClass().getResource("/org/volcra/less/compiler.min.js").text

    private static final def importPattern = ~/^\s*@import\s+[\"|'](.+)[\"|'];.*$/

    /**
     * Default Constructor.
     */
    private LessCompiler() {
        withContext {
            it.optimizationLevel = -1

            def global = new Global()
            global.init it as Context

            globalScope = it.initStandardObjects global
            it.evaluateString globalScope, envRhino, "env.rhino.js", 1, null
            it.evaluateString globalScope, less, "less.js", 1, null
            it.evaluateString globalScope, compiler, "compiler.js", 1, null

            compileFn = globalScope.get "compile", globalScope
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
    static def compile(File source, Writer writer, Boolean compress = false) {
        def lessCode = new StringWriter()

        source.newReader().transformLine lessCode, transformImportToText.curry(source.parentFile)

        withContext {
            writer << compileFn.call(it, globalScope, null, [lessCode.toString(), compress] as Object[])
            writer.flush()
        }
    }

    /**
     * Higher Order function that provides a context to the closure being passed.
     *
     * @param c the function or closure to execute
     */
    private static def withContext(Closure c) {
        def context = Context.enter()

        try {
            c context
        } finally {
            Context.exit()
        }
    }

    private static def transformImportToText = { parentFile, line ->
        if (line ==~ importPattern) new File(parentFile, (line =~ importPattern)[0][1]).text
        else line
    }
}