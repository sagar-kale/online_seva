

var app = angular.module('demoApp', ['ui.router']);
app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider
        .state("login", {
            url: "/login",
            templateUrl: "pagerouting/login",
            controller : "logincontroller"
        })
          .state("header", {
            url: "/header",
            templateUrl: "pagerouting/header",
            controller : "headerContrl"
        })
        .state("header.home", {
            url: "/home",
            templateUrl: "pagerouting/home",
            controller : "homeController"
        })
        .state("header.jobs", {
            url: "/jobdetails",
            templateUrl: "pagerouting/jobDetails",
            controller : "jobsController"
        })
         .state("header.admin", {
            url: "/admin",
             templateUrl: "pagerouting/admin",
            controller : "adminContrl"
             
        })
            .state("header.cources", {
            url: "/cources",
            templateUrl: "pagerouting/cources",
            controller : "courcesCntrl"
        })

});











    

    