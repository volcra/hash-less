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

class MainTest extends GroovyTestCase {
    void testMain() {
        Main.main "src\\test\\resources\\test.less"
        Main.main()
    }

    void testCompress() {
        Main.main "-c", "src\\test\\resources\\test.less"
    }

    void testPrint() {
        Main.main "-p", "src\\test\\resources\\test.less"
    }

    void testHelp() {
        Main.main "-h"
    }

    void testBootstrapCompile() {
        Main.main "src\\test\\resources\\bootstrap\\bootstrap.less"
        Main.main "-cp", "src\\test\\resources\\bootstrap\\bootstrap.less"
    }
}