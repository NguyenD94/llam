'use strict';

angular.module('llamApp')
    .factory('Role', function ($resource) {
        return $resource('api/roles/:id', {}, {
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
