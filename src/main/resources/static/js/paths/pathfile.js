// create the module and name it digistoreapp
var app = angular.module('demoApp', ['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider
        .state("login", {
            url: "/login",
            templateUrl: "views/login",
            controller : "logincontroller"
        })
          .state("header", {
            url: "/header",
            templateUrl: "views/header",
            controller : "headerContrl"
        })
        .state("header.home", {
            url: "/home",
            templateUrl: "views/home",
            controller : "homeController"
        })
        .state("header.jobs", {
            url: "/jobdetails",
            templateUrl: "views/jobDeatials",
            controller : "jobsController"
        })
         .state("header.admin", {
            url: "/admin",
            templateUrl: "views/admin",
            controller : "adminContrl"
        })
            .state("header.cources", {
            url: "/cources",
            templateUrl: "views/cources",
            controller : "courcesCntrl"
        })

});










    

    