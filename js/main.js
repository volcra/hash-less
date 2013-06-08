require.config({
  shim: {
    'angular': {
      exports: 'angular'
    }
  },
  paths: {
    angular: 'http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min',
    jquery: 'http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min',
    highlightjs: 'http://yandex.st/highlightjs/7.3/highlight.min'
  }
});

require([
  'angular',
  'jquery',
  'highlightjs',
  'bootstrap.min',
  'app.min',
  'controllers.min'
  ], function (angular, $) {
    angular.bootstrap(document, ['app']);
    $(document).on('click.menu', 'div.nav-collapse.in.collapse a', function (e) {
      $(e.target).parents('div.nav-collapse').collapse('toggle');
    });
});