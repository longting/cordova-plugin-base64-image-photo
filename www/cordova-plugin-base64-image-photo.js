//一个exports.XXX代表插件的一个方法，exports后面和exec方法的参数4都是方法名称
var exec = require('cordova/exec');

exports.save = function (base64Str, success, error) {
    //参数1和参数2分别是调用插件成功和失败的回调方法（js）
    //参数3是插件名
    //参数4是方法名
    //参数5是js的传参
	exec(success, error, 'Base64Image' , 'save', [base64Str]);
};
