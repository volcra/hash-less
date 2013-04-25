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