// create the module and name it digistoreapp
var dsApp = angular.module('digistoreapp', ['ui.router', 'ngAnimate','ui.bootstrap','highcharts-ng','angularUtils.directives.dirPagination','ngSanitize']);

dsApp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/newlogin');
    $stateProvider
        .state("newlogin", {
            url: "/newlogin",
            templateUrl: "../html/login.html",
            controller : "logincontroller"
        })

        .state("ultimatix", {
            url: "/ultimatix",
            templateUrl: "../html/ultimatix.html",
            controller : "ultimatixController"
        })
        
        .state("header", {
            url: "/header",
            templateUrl: "../html/header.html",
            controller : "headercontroller",
            abstract : true
        })
        .state("header.home", {
            url: "/landingpage",
            templateUrl: "../html/landingpage.html",
            controller : "landingpagecontroller",
        })
        .state("header.narrativesPage", {
            url: "/narrativesPage",
            templateUrl: "../html/narrativesdetailspage.html",
            controller : "narrativesDetailsPageConroller",
        })
        .state("header.casestudydetailsPage", {
            url: "/detailsPage",
            templateUrl: "../html/casestudydetalispage.html",
            controller : "casestudyPagecontroller",
        })
        .state("header.ad", {
            url: "/detailsPage",
            templateUrl: "../html/casestudydetalispage.html",
            controller : "adminContrl",
        })
        
});

//Services
dsApp.factory('CacheService',function(){
    var fvrCache={};
    var _getCacheData=function(key){
        return fvrCache[key];
    };
    var _setCacheData=function(key,data){
        fvrCache[key]=data;
    };
    var _resetCacheData=function(key){
        delete fvrCache[key];
    };
    var _resetAllCache=function(){
        fvrCache={};
    };
    return{
        getCacheData:_getCacheData,
        setCacheData:_setCacheData,
        resetCacheData:_resetCacheData,
        resetAllCache:_resetAllCache
    };
});

dsApp.service('productService', function() {
  var productList = [];

  var addProduct = function(newObj) {
      productList.push(newObj);
  };

  var getProducts = function(){
      return productList;
  };

  return {
    addProduct: addProduct,
    getProducts: getProducts
  };

});

dsApp.filter('startFrom', function() {
    return function(input, start) {
        if(input) {
            start = +start; //parse to int
            return input.slice(start);
        }
        return [];
    }
}); 

dsApp.directive('odometer', function () {
  //alert('in odometer');
    return {
      restrict: 'E',
      scope : {
        endValue : '=value'
      },
      link: function(scope, element) {
        // If you want to change the format, you have to add the necessary
        //  parameters. In this case I am going with the defaults.
        var od = new Odometer({
            el : element[0],
            value : 0   // default value
        });
        // update the odometer element when there is a 
        // change in the model value.
        scope.$watch('endValue', function() {
          od.update(scope.endValue);
        });
      }
    }
    }); 









    

    