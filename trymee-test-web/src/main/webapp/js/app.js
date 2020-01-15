(function() {
  'use strict';

    angular.module('tm', ['ngRoute', 'ngResource']).config(function($routeProvider){

        $routeProvider.
        
        when('/pre-test/:token', {
            templateUrl : 'partials/test/pre-test.html',
            controller  : 'TestController'
        }).
		
        when('/answer-test/:token', {
            templateUrl : 'partials/test/answer-test.html',
            controller  : 'TestController'
        }).
		
        when('/post-test/:token', {
            templateUrl : 'partials/test/post-test.html',
            controller  : 'TestController'
        }).
		
        when('/error', {
            templateUrl : 'partials/error/error.html',
            controller  : 'TestController'
        }).

        otherwise('/error');

    });  

})();