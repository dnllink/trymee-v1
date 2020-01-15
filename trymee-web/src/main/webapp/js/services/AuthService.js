(function() {
  'use strict';

    angular.module('tm').factory('AuthService', function ($q, $http, $location, $rootScope, LocationService, AUTH_EVENTS) {
    	
    	var LOCAL_TOKEN_KEY = 'localTokenTM';
    	var LOCAL_USER_NAME = 'localUserNameTM';
    	var username = '';
    	var isAuthenticated = false;
    	var authToken;
    	 
    	function loadUserCredentials() {
    		console.info('Procurando token previamente salvo.');
    		var token = window.sessionStorage.getItem(LOCAL_TOKEN_KEY);
//    		username = window.sessionStorage.getItem(LOCAL_USER_NAME);
    	    if (token) {
    	    	useCredentials(token);
    	    } else {
    	    	console.info('Token não encontrado. Redirecionando para login.');
    	    	$location.path('login');
    	    }
    	};
    	 
    	function storeUserCredentials(token) {
    		console.info('Gravando token para utilização.');
    	    window.sessionStorage.setItem(LOCAL_TOKEN_KEY, token);
//    	    window.sessionStorage.setItem(LOCAL_USER_NAME, username);
    	    useCredentials(token);
    	}
    	 
    	function useCredentials(token) {
    		console.info('Token encontrado, usando credenciais.');
//    		username = token.split(' . ')[0];
    	    isAuthenticated = true;
    	    authToken = token;    	 
    	    $http.defaults.headers.common['authorization'] = token;
    	}
    	 
    	function destroyUserCredentials() {
    		console.info('Apagando token e finalizando a sessão.');
    	    authToken = undefined;
    	    username = '';
    	    isAuthenticated = false;
    	    $http.defaults.headers.common['authorization'] = undefined;
    	    window.sessionStorage.removeItem(LOCAL_TOKEN_KEY);
//    	    window.sessionStorage.removeItem(LOCAL_USER_NAME);
    	    $rootScope.$broadcast(AUTH_EVENTS.authenticated, null);
    	}
    	 
    	var login = function(name, pw) {
    		console.info('Tentando efetuar login.');
    	    return $q(function(resolve, reject) {
    	    	$http.post(LocationService.getHost() + '/tokens',{user:name, pass:pw}).then(function (data) {
    	    		console.info('Login bem sucedido.');
      	    	  	var tk = data.data;
      	    	  	username = name;
      	    	  	storeUserCredentials(tk);
      	    	  	resolve('Login success.');
        			$rootScope.$broadcast(AUTH_EVENTS.authenticated, null);
    	    	}, function (err) {
    	    		console.error('Ocorreu um erro no login', err);
    	    		reject(err.data);
    	    	});
    	    });
    	};
    	 
    	var logout = function() {
    		destroyUserCredentials();
    	};

    	loadUserCredentials();

    	return {
    	    login: login,
    	    logout: logout,
    	    isAuthenticated: function() {return isAuthenticated;},
    	    username: function() {return username;},
    	    role: function() {return role;}
    	};
    }).factory('AuthInterceptor', function ($rootScope, $q, AUTH_EVENTS) {
    	return {
    		responseError: function (response) {
    			console.error('Tentativa de acesso não autenticado.');
    			$rootScope.$broadcast({
    		        401: AUTH_EVENTS.notAuthenticated    		        
    		      }[response.status], response);
    		      return $q.reject(response);
    		    }
    		  };
    		}).config(function ($httpProvider) {
    			$httpProvider.interceptors.push('AuthInterceptor');
    		}).constant('AUTH_EVENTS', {
    			notAuthenticated: 'auth-not-authenticated',
    			notAuthorized: 'auth-not-authorized',
    			authenticated: 'auth-successful'
    		});
})();