(function() {
  'use strict';

    angular.module('tm').factory('ProcessService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/processes/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();