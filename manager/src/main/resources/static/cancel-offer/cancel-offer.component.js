'use strict';

angular.
  module('cancelOffer').
  component('cancelOffer', {
    templateUrl: 'cancel-offer/cancel-offer.template.html',
    controller: ['$routeParams', '$location', 'Offer',
      function CancelOfferController($routeParams, $location, Offer) {
        this.cancelOffer = function() {
		  Offer.delete({ id: $routeParams.id }, function() {
		    $location.path('/');
		  });
		};
      }
    ]
  });
