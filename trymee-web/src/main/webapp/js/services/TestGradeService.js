(function() {
  'use strict';

    angular.module('tm').factory('TestGradeService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/testGrades/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();