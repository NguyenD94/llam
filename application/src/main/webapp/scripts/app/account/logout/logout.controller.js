'use strict';

angular.module('llamApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
