(function() {
  'use strict';

    angular.module('tm').factory('CategoryService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/categories/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();