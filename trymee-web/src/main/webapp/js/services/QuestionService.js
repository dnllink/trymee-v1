(function() {
  'use strict';

    angular.module('tm').factory('QuestionService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/questions/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();