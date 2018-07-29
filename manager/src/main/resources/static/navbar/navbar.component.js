'use strict';

angular.
  module('navbar').
  component('navbar', {
    templateUrl: 'navbar/navbar.template.html',
    controller: ['$scope', '$location',
      function NavbarController($scope, $location) {
    	$scope.isActive = function (viewLocation) { 
          return viewLocation === $location.path();
        };
      }
    ]
  });
