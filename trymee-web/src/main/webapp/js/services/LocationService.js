(function() {
  'use strict';

  	angular.module('tm').value('LocationService', function () {

    	var context = 'trymee';
    	var api = 'api';
    	
    	var getHost = function () {

        	var pathArray = location.href.split( '/' );
        	var prot = pathArray[0];
        	var host = pathArray[2].split(':')[0];
        	
        	if (host == 'localhost')
        		host = host + ':8080';

        	var completeHost = prot + '//' + host + '/' + context + '/' + api;

        	return completeHost;

    	};

    	var obj = {
    		getHost: getHost
    	};		

    	return obj;

    }());

})();