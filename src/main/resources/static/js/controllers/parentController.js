app.controller('mainCotroller',['$scope','userService','$state' ,function($scope,userService,$state) {
	console.log("inside mainCotroller");
    $(window).load(function() {
        // executes when complete page is fully loaded, including all frames, objects and images
        $("#parentloader").hide();
    });
}]);