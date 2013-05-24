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
    private static globalScope

    /**
     * Compile Function.
     */
    private static compileFn

    private static final ENV_RHINO_JS =
        getClass().getResource('/org/volcra/less/env.rhino.1.2.js').text

    private static final LESS_JS =
        getClass().getResource('/org/volcra/less/less-1.3.3.min.js').text

    private static final COMPILER_JS =
        getClass().getResource('/org/volcra/less/compiler.min.js').text

    private static final IMPORT_PATTERN = ~/^\s*@import\s+[\"|'](.+)[\"|'];.*$/

    /**
     * Default Constructor.
     */
    private LessCompiler() {
        withContext {
            it.optimizationLevel = -1

            def global = new Global()
            global.init it as Context

            globalScope = it.initStandardObjects global
            it.evaluateString globalScope, ENV_RHINO_JS, 'env.rhino.js', 1, null
            it.evaluateString globalScope, LESS_JS, 'less.js', 1, null
            it.evaluateString globalScope, COMPILER_JS, 'compiler.js', 1, null

            compileFn = globalScope.get 'compile', globalScope
        }
    }

    /**
     * Uses the reader to get the Less code and the writer to store the resulted CSS code.
     *
     * @param reader
     * @param writer
     * @param compress
     */
    void compile(File source, Writer writer, Boolean compress = false) {
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
    private withContext(Closure c) {
        def context = Context.enter()

        try {
            c context
        } finally {
            Context.exit()
        }
    }

    private final transformImportToText = { File parentFile, String line ->
        if (line ==~ IMPORT_PATTERN) new File(parentFile, (line =~ IMPORT_PATTERN)[0][1] as String).text
        else line
    }
}