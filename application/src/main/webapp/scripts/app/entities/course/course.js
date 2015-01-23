'use strict';

angular.module('llamApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('course', {
                parent: 'entity',
                url: '/course',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/course/courses.html',
                        controller: 'CourseController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('course');
                        return $translate.refresh();
                    }]
                }
            })
            .state('courseDetail', {
                parent: 'entity',
                url: '/course/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/course/course-detail.html',
                        controller: 'CourseDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('course');
                        return $translate.refresh();
                    }]
                }
            });
    });
