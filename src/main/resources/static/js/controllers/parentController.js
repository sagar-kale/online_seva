app.controller('mainCotroller',['$scope','userService','$state' ,function($scope,userService,$state) {
	console.log("inside mainCotroller");
		$scope.currentUser = userService.getUser();
	// if(!$scope.currentUser.username){
	// 	$state.go('login');
	// }
	// else{
	// 	alert('sajdkasd');
	// }
}]);