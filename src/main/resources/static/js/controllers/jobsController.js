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
	// /	console.log("inside home controller");
	// 	$scope.jobdata =[{
	// 		"jobBasicDetails":{
	// 					"lastDate": "15-08-2018",
	// 					"qualification": "MBBS",
	// 					"sector": "Medical Services Recruitment Board Tamil Nadu (MRBTN)",
	// 					"totalPosts": "2324",
	// 					"title": "Assistant Surgeon",
	// 					"startDate": "1-08-2018"

	// 	},
	// 	"jobSubDetails":{
	// 		"aboutJob": "Medical Services Recruitment Board Tamil Nadu (MRBTN) has released a notification for the recruitment of 1,884 Assistant Surgeons. Interested candidates may check the vacancy details and apply online from 25-09-2018 to 15-10-2018.",	
	// 		"postName": "Ass. Manager",
	// 		"totalVacancies": "2324",
	// 		"jobLocation":"Pune",
	// 		"educationalQualifiction": " Candidates should have passed MBBS and must be registered practitioner within the meaning of Madras Medical Registration Act, 1914 and 12 months working experience as House Surgeon (CRRI).",
	// 		"selectionProcess":"Selection of candidates will be made on the basis of Written Examination.",
	// 		"ageLimit":"Maximum 35 years",
	// 		"applicationFee":"General/Other State candidates have to pay Rs. 750 and SC/SCA/ST candidates have to pay Rs. 375 through online mode using Net-banking/Credit or Debit Card.",
	// 		"howToApplay":" Interested and eligible candidates may apply online through VYAPAM website - http://peb.mp.gov.in/ - from 28-09-2018 to 12-10-2018.",
	// 		"youTubeLink": "http://peb.mp.gov.in/",
	// 		"salaryScale":" 5000000 - 10000000"
			
	// 			}

	// 		}]
}]);