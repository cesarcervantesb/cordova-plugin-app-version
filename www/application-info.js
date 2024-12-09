var exec = require('cordova/exec');

const PLUGIN_NAME = "PluginApplicationInfo";

exports.getApplicationInfo = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, PLUGIN_NAME, 'getApplicationInfo', []);
};
