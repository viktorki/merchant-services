'use strict';

angular.
  module('viewOffer').
  component('viewOffer', {
    templateUrl: 'view-offer/view-offer.template.html',
    controller: ['$routeParams', 'Offer',
      function ViewOfferController($routeParams, Offer) {
        this.offer = Offer.get({ id: $routeParams.id });
      }
    ]
  });
