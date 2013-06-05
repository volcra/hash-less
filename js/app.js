'use strict';

/* App Module */

var app = angular.module('app', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/home', {templateUrl: 'partials/home.html', controller: 'ViewCtrl'}).
      when('/usage', {templateUrl: 'partials/usage.html', controller: 'ViewCtrl'}).
      when('/download', {templateUrl: 'partials/download.html', controller: 'ViewCtrl'}).
      otherwise({redirectTo: '/home'});
}]).
  run(function ($rootScope, $location) {
    $rootScope.$on("$routeChangeSuccess", function(current, previous) {
      if (typeof previous.$route != 'undefined')
        ga.push(['_trackPageview', previous.$route.templateUrl]);
    });
});