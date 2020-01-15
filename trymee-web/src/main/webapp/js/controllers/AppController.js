(function() {
    'use strict';

    angular.module('tm').controller('AppController', function($scope, $location, AuthService, AUTH_EVENTS){
        
    	$scope.username = AuthService.username();
    	$scope.authenticated = AuthService.isAuthenticated();
    	 
    	$scope.$on(AUTH_EVENTS.notAuthenticated, function(event) {
    	    AuthService.logout();
    		$scope.username = AuthService.username();
    		$scope.authenticated = AuthService.isAuthenticated();
    	    console.error('Usuario não autenticado!');
    	    $location.path('login');
    	});
   	 
    	$scope.$on(AUTH_EVENTS.authenticated, function(event) {
    		$scope.username = AuthService.username();
    		$scope.authenticated = AuthService.isAuthenticated();
    	});
    	 
    	$scope.setCurrentUsername = function(name) {
    	    $scope.username = name;
    	};
    	
    	$scope.logout = function () {
    		AuthService.logout();
    		$location.path('login');
    	};

  });

})();