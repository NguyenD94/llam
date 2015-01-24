'use strict';

angular.module('App')
    .controller('UserController', function ($scope, User, Role, Authority, PersistentToken) {
        $scope.users = [];
        $scope.roles = Role.query();
        $scope.authoritys = Authority.query();
        $scope.persistentTokens = PersistentToken.query();
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
