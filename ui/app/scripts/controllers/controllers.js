/// </// <reference path="angularjs/angular.d.ts" />

'user strict';

angular.module('twitterstream.controllers', ['twitterstream.services'])
	.controller('tweetController', ['$scope', 'tweetService', function($scope, tweetService) {
		$scope.tweets = [];
		
		$scope.$watch(
			function() {
				return tweetService.getTweets;
			},
			function(newVal, oldVal) {
				console.log("New value: " + newVal);
				$scope.tweets = newVal;
			}
		);
	}]);