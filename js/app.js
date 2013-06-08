'use strict';

/* App Module */

define(['angular'], function (angular) {
  var app = angular.module('app', []).
    config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/home', {templateUrl: 'views/home.html'}).
        when('/usage', {templateUrl: 'views/usage.html'}).
        when('/download', {templateUrl: 'views/download.html'}).
        otherwise({redirectTo: '/home'});
  }]).
    run(function ($rootScope, $location) {
      $rootScope.$on("$routeChangeSuccess", function(current, previous) {
        if (typeof previous.$route != 'undefined')
          ga.push(['_trackPageview', previous.$route.templateUrl]);
      });
  });

  return app;
});
