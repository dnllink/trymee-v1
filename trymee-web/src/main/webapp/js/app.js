(function() {
  'use strict';

    angular.module('tm', ['ngRoute', 'ngResource', 'ngAnimate', 'angular-loading-bar', '720kb.datepicker', 'angular-google-analytics', 'angular-intro']).config(function($routeProvider){

        $routeProvider.
        
        when('/', {
            templateUrl : 'partials/process/pesq-process.html',
            controller  : 'PesqProcessController'
        }).
        
        when('/login', {
            templateUrl : 'partials/login/login.html',
            controller  : 'LoginController'
        }).

        // Question
        when('/pesq-question', {
            templateUrl : 'partials/question/pesq-question.html',
            controller  : 'PesqQuestionController'
        }).

        when('/cad-question', {
            templateUrl : 'partials/question/cad-question.html',
            controller  : 'CadQuestionController'
        }).

        when('/edit-question/:id', {
            templateUrl : 'partials/question/cad-question.html',
            controller  : 'CadQuestionController'
        }).

        // Test
        when('/pesq-test/', {
            templateUrl : 'partials/test/pesq-test.html',
            controller  : 'PesqTestController'
        }).

        when('/cad-test', {
            templateUrl : 'partials/test/cad-test.html',
            controller  : 'CadTestController'
        }).

        when('/edit-test/:id', {
            templateUrl : 'partials/test/cad-test.html',
            controller  : 'CadTestController'
        }).

        // Process
        when('/pesq-process', {
            templateUrl : 'partials/process/pesq-process.html',
            controller  : 'PesqProcessController'
        }).
        
        when('/summary-process/:id', {
            templateUrl : 'partials/process/summary-process.html',
            controller  : 'SummaryProcessController'
        }).

        when('/cad-process', {
            templateUrl : 'partials/process/cad-process.html',
            controller  : 'CadProcessController'
        }).

        when('/edit-process/:id', {
            templateUrl : 'partials/process/cad-process.html',
            controller  : 'CadProcessController'
        }).
        
        when('/correct-test/', {
            templateUrl : 'partials/process/correct-test.html',
            controller  : 'CorrectTestController'
        }).
        
        // Candidate
        when('/pesq-candidate', {
            templateUrl : 'partials/candidate/pesq-candidate.html',
            controller  : 'PesqCandidateController'
        }).

        when('/cad-candidate', {
            templateUrl : 'partials/candidate/cad-candidate.html',
            controller  : 'CadCandidateController'
        }).

        when('/edit-candidate/:id', {
            templateUrl : 'partials/candidate/cad-candidate.html',
            controller  : 'CadCandidateController'
        }).
        
        //User
        when('/edit-user', {
            templateUrl : 'partials/user/cad-user.html',
            controller  : 'CadUserController'
        }).
        
        when('/404', {
            templateUrl : 'partials/error/404.html'            
        }).

        otherwise('/404');

    });
    
    angular.module('tm').config(['AnalyticsProvider', function (AnalyticsProvider) {
    	AnalyticsProvider.setAccount('UA-101164232-2');
    }]).run(['Analytics', function(Analytics) {}]);

})();