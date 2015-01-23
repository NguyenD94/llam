'use strict';

angular.module('llamApp')
    .controller('CourseDetailController', function ($scope, $stateParams, Course, User, Role) {
        $scope.course = {};
        $scope.load = function (id) {
            Course.get({id: id}, function(result) {
              $scope.course = result;
            });
        };
        $scope.load($stateParams.id);
    });
