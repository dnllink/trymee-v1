(function() {
  'use strict';

    angular.module('tm').factory('CandidateService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/candidates/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();