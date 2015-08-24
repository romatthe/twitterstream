/// <reference path="../../../typings/angularjs/angular.d.ts" />

'user strict';

angular.module('twitterstream.controllers', ['twitterstream.services'])
	.controller('tweetController', ['$scope', 'tweetService', function($scope, tweetService) {
		$scope.tweets = [];
		
		tweetService.getTweets(function(response) {
        	var tweet = JSON.parse(response.data);
			if ($scope.tweets.length == 100) {
				$scope.tweets.pop();
			}
			tweet.created_at_unix = tweet.created_at_ms.slice(0, -3)
          	$scope.tweets.unshift(tweet);
			$scope.$apply();
      	});
	}]);