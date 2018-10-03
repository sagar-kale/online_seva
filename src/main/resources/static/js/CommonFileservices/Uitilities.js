	app.factory('cacheService', [function () {
	    var cacheObj = localStorage;
	    var _getCacheData = function (key) {
	        return JSON.parse(cacheObj.getItem(key));
	    };
	    var _setCacheData = function (key, jsonResponse) {
	        cacheObj.setItem(key, JSON.stringify(jsonResponse));
	    };
	    var _resetCacheData = function (key) {
	        cacheObj.removeItem(key)
	    };
        var _resetAllCacheData = function (key) {
	        cacheObj.clear();
	    };
	    return {
	        getCacheData: _getCacheData,
	        setCacheData: _setCacheData,
	        resetCacheData: _resetCacheData,
            resetAllCacheData: _resetAllCacheData
	    };
	}]);

		app.factory('userService', [function () {
			var user ={};
			

	    
	    var _getUser = function () {
	        return user;
	    };
	    var _setUser = function (obj) {
	        user = obj;
	    };

	   
	    return {
	        getUser: _getUser,
	        setUser: _setUser
	        
	    	};
	}]);

	