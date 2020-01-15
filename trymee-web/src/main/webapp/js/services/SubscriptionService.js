(function() {
  'use strict';

    angular.module('tm').factory('SubscriptionService', function ($resource, LocationService) {

        return $resource(LocationService.getHost() + '/subscriptions/:id', null, {
            'update': {
                method: 'PUT'
            }
        });

    });

})();