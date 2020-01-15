(function() {
  'use strict';

    angular.module('tm').factory('TestService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/tests/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();