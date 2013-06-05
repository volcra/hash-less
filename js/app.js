'use strict';

/* App Module */

var less = angular.module('less', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/home', {templateUrl: 'partials/home.html', controller: 'ViewCtrl'}).
      when('/usage', {templateUrl: 'partials/usage.html', controller: 'ViewCtrl'}).
      otherwise({redirectTo: '/home'});
}]);