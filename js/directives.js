'use strict';

/* Directives */

define(['angular', 'hljs'], function (angular, hljs) {
  angular
    .module('app.directives', [])
    .directive('hljs', function() {
      return function(scope, element, attr) {
        hljs.highlightBlock(element[0]);
      }
    });
});