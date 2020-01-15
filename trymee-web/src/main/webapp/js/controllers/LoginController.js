(function() {
    'use strict';

    angular.module('tm').controller('LoginController', function($scope, AuthService, $location){

    	$scope.data = {};

    	$scope.login = function() {
    		AuthService.login($scope.data.username, $scope.data.password).then(function(authenticated) {
    			$scope.setCurrentUsername($scope.data.username);
    			console.info('Redirecionando para home.');
    			Materialize.toast('Bem vindo ao Trymee!', 6000, 'rounded');
    			$location.path('/');
    	    }, function(err) {
    	    	console.log(err);
    	    	Materialize.toast(err, 6000, 'rounded');
    	    });
    	};
    });
})();