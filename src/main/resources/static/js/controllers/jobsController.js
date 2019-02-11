app.controller('jobsController',['$scope', '$state','apiLink','APIService', function($scope, $state,apiLink,APIService) {
	console.log("inside homeController");
			$scope.gotoJobs = function(){
				$state.go("header.jobs");
			}
	
			$scope.loadData = function(){
			       var url = apiLink.getAllJobs;
                    APIService.httpGet(url).then(
                  function(res){
                  $scope.jobdata = res.data;
                   console.log("jkhaksdkja", $scope.jobdata);
                
                  
                  },
                  function(error) {
                    console.log(error);
                  });
		};
		$scope.loadData();
		$scope.isposterClciked = false;

		
	
}]);