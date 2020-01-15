(function() {
  'use strict';

    angular.module('tm').factory('LevelService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/levels/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();