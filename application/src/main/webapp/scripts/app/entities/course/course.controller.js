'use strict';

angular.module('llamApp')
    .controller('CourseController', function ($scope, Course, User, Role) {
        $scope.courses = [];
        $scope.users = User.query();
        $scope.roles = Role.query();
        $scope.loadAll = function() {
            Course.query(function(result) {
               $scope.courses = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Course.save($scope.course,
                function () {
                    $scope.loadAll();
                    $('#saveCourseModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.course = Course.get({id: id});
            $('#saveCourseModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.course = Course.get({id: id});
            $('#deleteCourseConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Course.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteCourseConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.course = {name: null, isPublic: null, accessKey: null, id: null};
        };
    });
