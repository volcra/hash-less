'use strict';

/* Controllers */

less.
controller('ViewCtrl', ['$scope', function ($scope) {
}]).
controller('MenuCtrl', ['$scope', '$location', function ($scope, $location) {
    $scope.menu = {
        items: [{
                text: 'Home', url: '/home'
            }, {
                text: 'Usage', url: '/usage'
            }, {
                text: 'API', url: '/api'
            }
        ]
    }

    $scope.getMenuItemClassStyle = function (item) {
        return $location.url().search(item.url) == 0 ? 'active' : '';
    }
}]);