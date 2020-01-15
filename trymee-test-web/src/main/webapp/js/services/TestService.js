(function() {
  'use strict';

    angular.module('tm').factory('TestService', function ($resource) {
    	
    	const context = 'trymee';
    	const api = 'api';

    	var pathArray = location.href.split( '/' );
    	var protocol = pathArray[0];
    	var host = pathArray[2].split(':')[0];

    	var completeHost = protocol + '//' + host + '/' + context + '/' + api;
//    	var completeHost = protocol + '//' + host + ':8080/' + context + '/' + api;

        return $resource(completeHost + '/links/:token', null);

    });

})();