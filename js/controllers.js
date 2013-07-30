'use strict';

/* Controllers */

define(['app'], function (app) {
  app.controller('MenuCtrl', ['$scope', '$location', function ($scope, $location) {
    $scope.menu = {
      items: [{
          text: 'Download', url: '#/download', target: '_self'
        }, {
          text: 'Usage', url: '#/usage', target: '_self'
        }, {
          text: 'API', url: 'api', target: '_blank'
        }
      ]
    }

    $scope.getMenuItemClassStyle = function (item) {
      return $location.url().search(item.url.replace('#', '')) == 0 ? 'active' : '';
    }
  }]);
});
