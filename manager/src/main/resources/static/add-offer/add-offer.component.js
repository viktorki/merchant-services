'use strict';

angular.
  module('addOffer').
  component('addOffer', {
    templateUrl: 'add-offer/add-offer.template.html',
    controller: ['$location', 'Offer',
      function AddOfferController($location, Offer) {
        this.offer = new Offer();
        this.saveOffer = function() {
		  this.offer.$save(function() {
		    $location.path('/');
		  },
		  function(response) {
		    if (response.data.errors != undefined) {
		      alert(response.data.errors[0].defaultMessage);
		    } else {
		      alert(response.data[0].message);
		    }
		  });
		};
      }
    ]
  });
