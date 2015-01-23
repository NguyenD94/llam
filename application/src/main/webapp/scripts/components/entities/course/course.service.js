'use strict';

angular.module('llamApp')
    .factory('Course', function ($resource) {
        return $resource('api/courses/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
