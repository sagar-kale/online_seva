    app.service('apiConfigService',function (constantservice) {
        //var baseurl = Constants.BASE_URL;
        var baseurl=constantservice.BASE_URL+"/";        
        this.login = {
            url: baseurl + "api link",
            method: 'POST'
        }
    });