'use strict';

angular.module('llamApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('role', {
                parent: 'entity',
                url: '/role',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/role/roles.html',
                        controller: 'RoleController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('role');
                        return $translate.refresh();
                    }]
                }
            })
            .state('roleDetail', {
                parent: 'entity',
                url: '/role/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/role/role-detail.html',
                        controller: 'RoleDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('role');
                        return $translate.refresh();
                    }]
                }
            });
    });
