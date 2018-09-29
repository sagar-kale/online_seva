var app = angular.module('demoApp',['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('login');
    $stateProvider
        .state("login", {
            url: "/login",
            templateUrl: "partials/login",
            controller : "logincontroller"
        })
          .state("header", {
            url: "/header",
            templateUrl: "partials/header",
            controller : "headerContrl"
        })
        .state("header.home", {
            url: "/",
            templateUrl: "partials/home",

            controller : "homeController"
        })
        .state("header.jobs", {
            url: "/jobdetails",
            templateUrl: "partials/jobDeatials",
            controller : "jobsController"
        })
         .state("header.admin", {
            url: "/admin",
            templateUrl: "partials/admin",
            controller : "adminContrl"
        })
            .state("header.cources", {
            url: "/cources",
            templateUrl: "partials/cources",
            controller : "courcesCntrl"
        })

});

/*
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('login', {
                url: '/login',
                templateUrl: 'partials/list',
                controller:'UserController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, UserService) {
                        console.log('Load all users');
                        var deferred = $q.defer();
                        UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
            .state('home', {
                            url: '/',
                            templateUrl: 'partials/login',
                            controllerAs:'ctrl',
                        });
        $urlRouterProvider.otherwise('/');
    }]);
*/

