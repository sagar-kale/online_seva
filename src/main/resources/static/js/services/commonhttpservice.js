dsApp.service("commonHttpService", function ($http, $q, $state,CacheService,ScreenAdjusterService) {
    var callhttp = function (configObj) {
        var deferred = $q.defer();
        $http(configObj).then(function (res) {
            angular.copy(res);
            deferred.resolve(res);
        }, function (err) {
            deferred.reject(err);
        });
        return deferred.promise;
    }

    var makeConfigObj = function (urlObj, urlAppendString, _data, fileId,additionalConfig) {
        var _url = urlObj.url;
        var _method = urlObj.method;
        if (urlAppendString) {
        _url = _url + "/" + urlAppendString;
        }
        if(fileId){
            _url = _url + fileId
        }
        var configObj = {
            url: _url,
            method: _method,
            headers: {
                'Content-Type': 'application/json'
                }
        };
        //check if token present
        //configObj.headers.Authorizarion=cacheService.getcahchedata("TOKENKEY").token;
        var tokenManagementData=CacheService.getCacheData("tokenManagementData")
        if(tokenManagementData && tokenManagementData.token){
            //alert("inside cacheService");
            console.log("token string",tokenManagementData.token);        
            configObj.headers['X-AUTH-TOKEN']=tokenManagementData.token;

        }
        if (_data) {
            configObj.data = _data;
        }
        if (additionalConfig===1) {
            configObj.transformRequest = angular.identity;
            configObj.headers['Content-Type'] = undefined;
        }
       /* if(additionalConfig===2){
        	 configObj.headers['Content-Type'] = undefined;
        }*/
        return configObj;
    }

    var apiMediator = function (configObj, showloader) {
        if (showloader) {
            $("#loaderDiv").show(); 
        }
        var deferred = $q.defer();
        callhttp(configObj).then(function (res) {
            if (showloader) {
               	 $("#loaderDiv").hide(); 
            }
            ScreenAdjusterService.footeradjuster();                                
            deferred.resolve(res);
        }, function (err) {
            if (showloader) {
                console.log("hide loader....");
             	 $("#loaderDiv").hide(); 
            }     
            deferred.reject(err);
        });
        return deferred.promise;
    };
    return {
        makeConfigObj: makeConfigObj,
        apiMediator: apiMediator
    }
    });