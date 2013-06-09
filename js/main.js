require.config({
  shim: {
    'angular': {
      exports: 'angular'
    },
    'bootstrap': {
      deps: ['jquery']
    },
    'hljs': {
      exports: 'hljs'
    }
  },
  paths: {
    angular: 'http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min',
    jquery: 'http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min',
    hljs: 'highlight.pack',
    bootstrap: 'bootstrap.min'
  }
});

require([
  'angular',
  'jquery',
  'hljs',
  'bootstrap',
  'app',
  'controllers',
  'directives'
  ], function (angular, $) {
    angular.bootstrap(document, ['app']);
    $(document).on('click.menu', 'div.nav-collapse.in.collapse a', function (e) {
      $(e.target).parents('div.nav-collapse').collapse('toggle');
    });
});