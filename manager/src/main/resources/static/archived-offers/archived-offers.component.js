'use strict';

angular.
  module('archivedOffers').
  component('archivedOffers', {
    templateUrl: 'archived-offers/archived-offers.template.html',
    controller: ['Offer',
      function ArchivedOffersController(Offer) {
        this.archivedOffers = Offer.query({ id: 'archived' });
        this.orderProp = 'id';
      }
    ]
  });
