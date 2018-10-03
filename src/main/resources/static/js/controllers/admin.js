app.controller('adminContrl',['$scope','$state','apiLink','APIService', function($scope, $state,apiLink,APIService) {
	
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
})
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

}]);