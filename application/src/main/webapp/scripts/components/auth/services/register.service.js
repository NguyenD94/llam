'use strict';

angular.module('llamApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


