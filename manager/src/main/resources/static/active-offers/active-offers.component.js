'use strict';

angular.
  module('activeOffers').
  component('activeOffers', {
    templateUrl: 'active-offers/active-offers.template.html',
    controller: ['Offer',
      function ActiveOffersController(Offer) {
        this.activeOffers = Offer.query();
        this.orderProp = 'id';
      }
    ]
  });
