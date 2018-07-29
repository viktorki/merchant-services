'use strict';

angular.
  module('core.offer').
  factory('Offer', ['$resource',
    function($resource) {
      return $resource('/offers/:id');
    }
  ]);
