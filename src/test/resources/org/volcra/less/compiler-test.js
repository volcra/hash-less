/*!
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
var compile = function (source, compress) {
    var result;
    //var parser = new(less.Parser);

    var parser = new(less.Parser)({
		paths: ['.'], // Specify search paths for @import directives
		filename: 'style.less' // Specify a filename, for better error messages
	});

    parser.parse(source, function(e, tree) {
        if (e) {
        	for (i in e) java.lang.System.out.println(i + ':' + e[i]);
            throw e;
        }

        result = tree.toCSS({ compress: compress });
    });

    return result;
};