var compile = function (source, compress) {
    var result;
    var parser = new less.Parser();

    parser.parse(source, function(e, tree) {
        if (e instanceof Object) {
            throw e;
        }

        result = tree.toCSS({ compress: compress });
    });

    return result;
};