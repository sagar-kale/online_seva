
dsApp.factory('apiService',function ( apiConfigService, commonHttpService) {
    //var baseUrl = Constants.BASE_URL;
    var showLoader = true;
    var hideLoder = false;
    /* Project Functions */
    var login = function (data) {
        var configObj = commonHttpService.makeConfigObj(apiConfigService.login, null, data);
        return commonHttpService.apiMediator(configObj, showLoader);
    };
    
    return {
        login:login
    };
});