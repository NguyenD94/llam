'use strict';

angular.module('llamApp')
    .controller('RoleController', function ($scope, Role, Course, User) {
        $scope.roles = [];
        $scope.courses = Course.query();
        $scope.users = User.query();
        $scope.loadAll = function() {
            Role.query(function(result) {
               $scope.roles = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Role.save($scope.role,
                function () {
                    $scope.loadAll();
                    $('#saveRoleModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.role = Role.get({id: id});
            $('#saveRoleModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.role = Role.get({id: id});
            $('#deleteRoleConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Role.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteRoleConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.role = {name: null, canCreate: null, canDiscuss: null, canRate: null, canDelete: null, canGrantAccess: null, canEdit: null, canAdministrate: null, id: null};
        };
    });
