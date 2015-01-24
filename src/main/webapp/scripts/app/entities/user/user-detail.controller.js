'use strict';

angular.module('App')
    .controller('UserDetailController', function ($scope, $stateParams, User, Role, Authority, PersistentToken) {
        $scope.user = {};
        $scope.load = function (id) {
            User.get({id: id}, function(result) {
              $scope.user = result;
            });
        };
        $scope.load($stateParams.id);
    });
