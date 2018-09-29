//Services
app.factory('CacheService',function(){
    var fvrCache={};
    var _getCacheData=function(key){
        return fvrCache[key];
    };
    var _setCacheData=function(key,data){
        fvrCache[key]=data;
    };
    var _resetCacheData=function(key){
        delete fvrCache[key];
    };
    var _resetAllCache=function(){
        fvrCache={};
    };
    return{
        getCacheData:_getCacheData,
        setCacheData:_setCacheData,
        resetCacheData:_resetCacheData,
        resetAllCache:_resetAllCache
    };
});













