package org.volcra.less.compiler

class MainTest extends GroovyTestCase {
    void testMain() {
        Main.main "-p", "src\\test\\resources\\test.less"
        Main.main()
    }

    void testCompress() {
        Main.main "-pc", "src\\test\\resources\\test.less"
    }

    void testHelp() {
        Main.main "-h"
    }
}