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
         link: function (scope, element, attrs, ngModelCtrl) {
            element.datepicker({
                dateFormat: 'mm/dd/yy',
                minDate: 0,
                onSelect: function (date) {
                    scope.date = date;
                    scope.$apply();
                }
            });

            
        }
    };
});

app.directive('jqtimepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
         link: function (scope, element, attrs, ngModelCtrl) {

            element.wickedpicker({
                twentyFour: true,
                 minTime: '07:00',
        maxTime: '19:00',
                onSelect: function (date) {
                    scope.time = time;
                    scope.$apply();
                }
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
   
      .when("/appointment",{templateUrl: "partials/appointment.html", controller: "PageCtrl"})
       .when("/login",{templateUrl: "partials/login.html", controller: "PageCtrl"})
      .when("/about",{templateUrl: "partials/about.html", controller: "PageCtrl"})
      .when("/contact",{templateUrl: "partials/contactUs.html", controller: "PageCtrl"})
      .when("/testimonials",{templateUrl: "partials/testimonials.html", controller: "PageCtrl"})
       .when("/patients",{templateUrl: "partials/underConstruction.html", controller: "PageCtrl"})
         .when("/generalPrac",{templateUrl: "partials/underConstruction.html", controller: "PageCtrl"})
          .when("/faq",{templateUrl: "partials/underConstruction.html", controller: "PageCtrl"})
          .when("/conditions",{templateUrl: "partials/conditions.html", controller: "PageCtrl"})
   
    
   
    .otherwise("/404", {templateUrl: "partials/404.html", controller: "PageCtrl"});
}]);

/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function (/* $scope, $location, $http */) {
  console.log("Blog Controller reporting for duty.");
});

/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function ( $scope, $location, $http ) {
  console.log("Page Controller reporting for duty.");

  $scope.appointment ={};
  $scope.bookAppointment =function(appointment){
    console.log(appointment);
  }

  $scope.navigate =function(routeName){
    $location.path(routeName);
  }

  // Activates the Carousel
  $('.carousel').carousel({
    interval: 5000
  });

  // Activates Tooltips for Social Links
  $('.tooltip-social').tooltip({
    selector: "a[data-toggle=tooltip]"
  })
});