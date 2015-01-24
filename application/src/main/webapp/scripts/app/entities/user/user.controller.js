'use strict';

angular.module('llamApp')
    .controller('UserController', function ($scope, User, Course, Role) {
        $scope.users = [];
        $scope.courses = Course.query();
        $scope.roles = Role.query();
        $scope.loadAll = function() {
            User.query(function(result) {
               $scope.users = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            User.save($scope.user,
                function () {
                    $scope.loadAll();
                    $('#saveUserModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.user = User.get({id: id});
            $('#saveUserModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.user = User.get({id: id});
            $('#deleteUserConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            User.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteUserConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.user = {login: null, password: null, firstName: null, lastName: null, email: null, activated: null, langKey: null, activationKey: null, studentNumber: null, id: null};
        };
    });
