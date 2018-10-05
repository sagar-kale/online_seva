app.controller('adminContrl',['$scope','$state','apiLink','APIService', function($scope, $state,apiLink,APIService) {
	
	/*if($scope.currentUser.roles[0].role != "admin"){
         swal("","You are not authorised to access admin page", "error");
         //$scope.logoutApp(); 
		 $state.go("header.home");
	}*/

	$scope.activeTab = "show1";
	$scope.toggleTabs = function(tab){
		$scope.activeTab = tab;
		}
	
		$scope.showSection2 = false;
		$scope.addmoreDetails = function(){
			$scope.showSection2 = true;
		}
		$(function() {

    function toggleChevron(e) {
        $(e.target)
                .prev('.panel-heading')
                .find("i")
                .toggleClass('rotate-icon');
        $('.panel-body.animated').toggleClass('zoomIn zoomOut');
    }
    
    $('#accordion').on('hide.bs.collapse', toggleChevron);
    $('#accordion').on('show.bs.collapse', toggleChevron);
});
		angular.element(document).ready(function() {  
    dTable = $('#user_table')  
    dTable.DataTable();  
});  
$scope.clickedNext = function(){
	var target = $("#headingTwo");
	$("#headingTwo").click();
}

$scope.readOnlyFirst = false;
$scope.saveFirstSection = function(){
	$scope.readOnlyFirst = true;	
	$scope.clickedNext();
	

}
$scope.editFirstSection = function(){
	$scope.readOnlyFirst = false;
}


		$scope.getAllUserList = function(){
			       var url = apiLink.getAllUsers;
                    APIService.httpGet(url).then(
                  function(res){
                   $scope.userList = res.data;
                   console.log("jkhaksdkja", $scope.userList);
                
                  
                  },
                  function(error) {
                    console.log(error);
                  });
		}
		$scope.getAllUserList(); 
				$scope.saveJobDetails = function(){

					var jobdata ={
						
									"lastDate": $scope.basicsJob.LastDate,
									"qualification":$scope.basicsJob.Qualification,
									"sector": $scope.basicsJob.Sector,
									"totalPosts": $scope.basicsJob.TotalPosts,
									"title": $scope.basicsJob.title,
									"startDate": $scope.basicsJob.StartDate,

									
							"jobSubDetails":{
									"aboutJob": $scope.subDetails.aboutJob,	
									"postName": $scope.subDetails.postName,
									"totalVacancies": $scope.subDetails.TotalVacancies,
									"salaryScale":$scope.subDetails.SalaryScale,
									"jobLocation":$scope.subDetails.JobLocation,
									"educationalQualifiction":$scope.subDetails.EducationalQualifiction,
									"selectionProcess":$scope.subDetails.SelectionProcess,
									"ageLimit":$scope.subDetails.AgeLimit,
									"applicationFee":$scope.subDetails.applicationFee,
									"howToApplay":$scope.subDetails.Applay,
									"youTubeLink": $scope.subDetails.YouTubeLink				
			
									}

								};

						var url = apiLink.saveJobDetails;
        //alert(url);

        APIService.httpPostJson(url, jobdata).then(
            function (res) {

                if (res.data.msgType == 'error') {
                    swal("", res.data.message, res.data.msgType);
                }
                else {
                	  $scope.firstsection.$setPristine();
						  $scope.secondsection.$setPristine();
						  $scope.thirdsection.$setPristine();
                    console.log("saved", res.data);
                    swal("", res.data.message, res.data.msgType);
                    	
                }

            },
            function (error) {
                console.log(error);
            });
						


		}

}]);