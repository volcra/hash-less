function compileCss(css, compress) {
    var result;
    var parser = new less.Parser();

    parser.parse(css, function(e, tree) {
        if (e instanceof Object) {
            throw e;
        }

        result = tree.toCSS({ compress: compress });
    });

    return result;
}