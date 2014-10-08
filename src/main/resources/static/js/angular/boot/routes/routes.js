'use strict';

boot.config(
	function($routeProvider, $locationProvider) {
	    $locationProvider.html5Mode(true);

	    $routeProvider.
		    when('/', {
			templateUrl: '/partials/_index.html',
			controller: 'IndexCtrl'
		    }).
		    when('/bikes', {
			templateUrl: '/partials/_bikes.html',
			controller: 'BikesCtrl'
		    }).
		    when('/milages', {
			templateUrl: '/partials/_milages.html',
			controller: 'MilagesCtrl'
		    }).
		    when('/gallery', {
			templateUrl: '/partials/_gallery.html',
			controller: 'GalleryCtrl'
		    }).
		    when('/tracks', {
			templateUrl: '/partials/_tracks.html',
			controller: 'TracksCtrl'
		    }).
		    when('/tracks/:id', {
			templateUrl: '/partials/_track.html',
			controller: 'TrackCtrl'
		    }).
		    when('/location', {
			templateUrl: '/partials/_location.html',
			controller: 'LocationCtrl'
		    }).
		    when('/about', {
			templateUrl: '/partials/_about.html',
			controller: 'AboutCtrl'
		    }).
		    otherwise({
			redirectTo: '/'
		    });
	}
);