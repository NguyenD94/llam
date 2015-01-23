'use strict';

angular.module('llamApp')
    .controller('RoleDetailController', function ($scope, $stateParams, Role, Course, User) {
        $scope.role = {};
        $scope.load = function (id) {
            Role.get({id: id}, function(result) {
              $scope.role = result;
            });
        };
        $scope.load($stateParams.id);
    });
