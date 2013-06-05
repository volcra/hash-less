'use strict';

/* App Module */

var app = angular.module('app', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/home', {templateUrl: 'partials/home.html', controller: 'ViewCtrl'}).
      when('/usage', {templateUrl: 'partials/usage.html', controller: 'ViewCtrl'}).
      otherwise({redirectTo: '/home'});
}]);