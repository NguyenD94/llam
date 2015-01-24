'use strict';

angular.module('llamApp')
    .controller('UserDetailController', function ($scope, $stateParams, User, Course, Role) {
        $scope.user = {};
        $scope.load = function (id) {
            User.get({id: id}, function(result) {
              $scope.user = result;
            });
        };
        $scope.load($stateParams.id);
    });
