// APPS
var mainFashionApp = angular.module('mainFashionApp', ['ngRoute']);

// ROUTES
mainFashionApp.config(function ($routeProvider, $locationProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'views/home',
        controller: 'mainController'
    })
    .when('/brand', {
        templateUrl: 'views/brand',
        controller: 'mainController'
    })
    .when('/contact', {
        templateUrl: 'views/contact',
        controller: 'contactController'
    })
    .when('/designer', {
        templateUrl: 'views/designer',
        controller: 'mainController'
    })
    .when('/graduate', {
        templateUrl: 'views/graduate',
        controller: 'graduateController'
    })
    .when('/editorials', {
        templateUrl: 'views/editorials',
        controller: 'editorialsController'
    })
    .otherwise({
        redirectTo: '/'
    });
    
    // enable html5Mode for pushstate ('#'-less URLs)
    $locationProvider.html5Mode(true);
});

// CONTROLLERS
mainFashionApp.controller('mainController', ['$scope', 'galleryService', '$rootScope', function($scope, galleryService, $rootScope) {
    galleryService.hideGallery(true, true);
    
    $rootScope.privacyPolicy = {
    	show : false
    };
    $scope.privacyPolicy = $rootScope.privacyPolicy;
}]);

mainFashionApp.controller('contactController', 
		['$scope', '$log', 'galleryService', '$http', '$rootScope', function($scope, $log, galleryService, $http, $rootScope) {
    // set view elements
	galleryService.hideGallery(true, true);
    $scope.header = '| For all enquiries, please use the form below |';
    $rootScope.privacyPolicy = {
    	show : true
	};
    $scope.privacyPolicy = $rootScope.privacyPolicy;

    // handling for contact request
    $scope.contactForm = {};
    $scope.contactError = false;
    $scope.contactSuccess = false;
    $scope.sendMessage = function(isValid) {
    	if (isValid) {
	    	$log.info('Request is ' + JSON.stringify($scope.contactRequest));
	    	
	    	var promise = $http({
	            method : 'POST',
	            url : '/processContactMessage',
	            data : $scope.contactRequest
	        }).then(function (response) {
	        	$log.info('Response is ' + JSON.stringify(response.data));
	        	$scope.contactSuccess = response.data.success;
	        	if (!response.data.success) {
	        		$scope.contactError = true;
	        	} 
	        });  
    	}
	};
    
}]);

mainFashionApp.controller('graduateController', ['$scope', 'galleryService', '$rootScope', function($scope, galleryService, $rootScope) {
    galleryService.hideGallery(false, true);

    $scope.header = '| MA Graduate Collection |';
    $scope.photographer = 'Michela Nale';
    
    $rootScope.privacyPolicy = {
    	show : false
    };
    $scope.privacyPolicy = $rootScope.privacyPolicy;
}]);

mainFashionApp.controller('editorialsController', ['$scope', 'galleryService', '$rootScope', function($scope, galleryService, $rootScope) {
    galleryService.hideGallery(true, false);

    $scope.header = '| PETRIe |';
    $scope.photographer = 'Sophia Weston';
    
    $rootScope.privacyPolicy = {
    	show : false
    };
    $scope.privacyPolicy = $rootScope.privacyPolicy;
}]);

// SERVICES
mainFashionApp.service('galleryService', function() {
    this.hideGrad = true;
    this.hideEdit = true;

    this.hideGallery = function(hideGrad, hideEdit) {
        if (hideGrad) {
            $("#graduate-gallery").hide();
        } else {
            $("#graduate-gallery").show();
        }
        if (hideEdit) {
            $("#editorials-gallery").hide();
        } else {
            $("#editorials-gallery").show();
        }
    }
});

// DIRECTIVES
mainFashionApp.directive("headingLeftDirective", function() {
    return {
        template : "<p class='headingleft' ng-model='header'>{{ header }}</p>"
    };
});

mainFashionApp.directive("editorialsTextLeftDirective", function() {
    return {
        template: "<p class='textleft'>Photographer: {{ photographer }} <br /><br /> Stylist: Hannah Trüb <br /><br /> Hair & Makeup: Ana Ambrasaite <br /><br /> Talent: <br />Valeriane at Wilhelmina London &<br />Alice at Models 1 <br /><br /> Photographic Assistants: <br/>Christina Mitrea &<br />Alek Kofta<br /><br /> PETRIe: <a target='_blank' href='http://www.petrieinventory.com/a-chance-encounter'>A Chance Encounter</a></p>"
    };
});

mainFashionApp.directive("graduateTextLeftDirective", function() {
    return {
        template: "<p class='textleft'>Photographer: {{ photographer }} <br /><br /> Hair & Makeup: Tanya McGeever <br /><br /> Model: Hannah Verrier</p>"
    };
});

mainFashionApp.directive("homeDirective", function() {
    return {
        template: "<div><img src='http://imageshack.com/a/img922/1034/utlBWv.png' class='background'></div>"
    }
});

mainFashionApp.directive("privacyPolicy", function() {
    return {
        template: "<a href=\"//www.iubenda.com/privacy-policy/8005440\" class=\"iubenda-black iubenda-embed\" title=\"Privacy Policy\">Privacy Policy</a>"  
    }
});
