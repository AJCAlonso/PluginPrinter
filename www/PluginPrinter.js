var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'PluginPrinter', 'coolMethod', [arg0]);
};

exports.imprimir = function (arg0, success, error) {
    exec(success, error, 'PluginPrinter', 'imprimir', [arg0]);
};
