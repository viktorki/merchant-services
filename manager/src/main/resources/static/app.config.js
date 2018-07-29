'use strict';

angular.
  module('offerManagerApp').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
        when('/', {
          template: '<active-offers></actve-offers>'
        }).
        when('/archive', {
          template: '<archived-offers></archived-offers>'
        }).
        when('/view/:id', {
          template: '<view-offer></view-offer>'
        }).
        when('/add', {
          template: '<add-offer></add-offer>'
        }).
        when('/cancel/:id', {
          template: '<cancel-offer></cancel-offer>'
        });
    }
  ]);
