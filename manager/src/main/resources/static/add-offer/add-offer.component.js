'use strict';

angular.
  module('addOffer').
  component('addOffer', {
    templateUrl: 'add-offer/add-offer.template.html',
    controller: ['$location', 'Offer',
      function AddOfferController($location, Offer) {
        this.offer = new Offer();
        this.saveOffer = function() {
		  this.offer.$save();
		  $location.path('/');
		};
      }
    ]
  });
