/**
 * AngularJS Tutorial 1
 * @author Nick Kaye <nick.c.kaye@gmail.com>
 */

/**
 * Main AngularJS Web Application
 */
var app = angular.module('tutorialWebApp', [
  'ngRoute'
]);

app.directive('jqdatepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
         link: function (scope, element, attrs,PageCtrl ) {
            element.datepicker({
                dateFormat: 'mm/dd/yy',
                minDate: 0,
                onSelect: function (date) {
                    scope.date = date;
                    console.log(date);
                    scope.$apply(function () {
                    	PageCtrl.$setViewValue(date);
                    });
                }
            });

            
        }
    };
});

app.directive('jqtimepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
         link: function (scope, element, attrs, PageCtrl) {

            element.wickedpicker({
                twentyFour: true,
                 minTime: '07:00',
        maxTime: '19:00',
                onSelect: function (time) {
                    scope.time = time;
                    console.log(time);
                    scope.$apply(function () {
                    	PageCtrl.$setViewValue(time);
                    });
                }
            });
        }
    };
});


app.directive('accordian', function () {
    return {
        restrict: 'A',
       scope: {active: "="},
      controller: 'ConditionController',
      controllerAs: 'ctrl',
         link: function (scope, element, attrs, PageCtrl) {
element.accordion({
      collapsible: true,
      active:(scope.active?parseInt(scope.active):false),
    });
        }
    };
});


/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/home", {templateUrl: "partials/home.html", controller: "PageCtrl"})
    .when("/", {templateUrl: "partials/home.html", controller: "PageCtrl"})
    // Pages
   
      .when("/appointment",{templateUrl: "partials/appointment.html", controller: "ApptmentCtrl"})
       .when("/login",{templateUrl: "partials/login.html"})
      .when("/about",{templateUrl: "partials/about.html"})
      .when("/contact",{templateUrl: "partials/contactUs.html", controller: "EnquiryContactUsCtrl"})
      .when("/testimonials",{templateUrl: "partials/testimonials.html"})
       .when("/patients",{templateUrl: "partials/underConstruction.html"})
         .when("/generalPrac",{templateUrl: "partials/gpReferrals.html"})
          .when("/faq",{templateUrl: "partials/faq.html",controller: "EnquiryContactUsCtrl"})
          .when("/conditions",{templateUrl: "partials/conditions.html"})
     .when("/usefulLinks",{templateUrl: "partials/usefulLinks.html"})
    .otherwise("/404", {templateUrl: "partials/404.html", controller: "PageCtrl"});
}]);

/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function (/* $scope, $location, $http */) {
  console.log("Blog Controller reporting for duty.");
});

app.controller('ConditionController', function ( $scope,$rootScope, $location, $http ) {
$scope.active=$rootScope.activeTab;
});


app.controller('EnquiryContactUsCtrl', function ( $scope, $location, $http ) {
	$scope.enquiry = {};
	 
	  $scope.createEnquiry = function(enquiry){
		  console.log(enquiry);
		  $scope.isEnquirySuccessful=false;
		    $scope.isEnquiryUnSuccessful=false;
			 var req = {
					 method: 'POST',
					 url: 'urology/send-enquiry',
					
					 data: enquiry 
					}

					$http(req).then(function(res){
						console.log(res);
						$scope.appointment ={};
						$scope.isEnquirySuccessful = res.data;
						$scope.isEnquiryUnSuccessful = res.data;
					}, function(){
						$scope.isEnquiryUnSuccessful = true;
					});
	  }
	});

app.controller('ApptmentCtrl', function ( $scope, $location, $http ) {
	 $scope.appointment ={};
	 $scope.bookAppointment =function(appointment){
		    console.log(appointment);
		    $scope.isBookSuccessful=false;
		    $scope.isBookUnSuccessful=false;
			 var req = {
					 method: 'POST',
					 url: 'urology/book-appointment',
					
					 data: appointment 
					}

					$http(req).then(function(res){
						console.log(res);
						$scope.appointment ={};
						$scope.isBookSuccessful = res.data;
						$scope.isBookUnSuccessful = !res.data;
						$scope.appointmentform.$setPristine();
					}, function(){
						$scope.appointment ={};
						$scope.appointmentform.$setPristine();
						$scope.isBookUnSuccessful = true;
					});
		  }
		  
	});

/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function ( $scope,$rootScope,$window,$location, $http ) {
	
	
  console.log("Page Controller reporting for duty.");

  /*$scope.wherAbouts=[];
  $http.get('urology/getdocWhereabouts').then(function(response) {
      $scope.wherAbouts = response.data;
      console.log( $scope.wherAbouts.date);
  });*/
  
  
  $scope.navigate =function(routeName){
    if(routeName.indexOf('conditions')!= -1){
      $rootScope.activeTab = routeName.split('-')[1];
      $window.location.href='#/'+routeName.split('-')[0]+'#'+routeName.split('-')[2];
     // $location.path(routeName.split('-')[0]);
    }else{
      $location.path(routeName);
    }
    
  }

  // Activates the Carousel
  $('.carousel').carousel({
    interval: 7000
  });

  // Activates Tooltips for Social Links
  $('.tooltip-social').tooltip({
    selector: "a[data-toggle=tooltip]"
  })
});