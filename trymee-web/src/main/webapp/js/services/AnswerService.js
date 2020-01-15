(function() {
  'use strict';

    angular.module('tm').factory('AnswerService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/answers/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();