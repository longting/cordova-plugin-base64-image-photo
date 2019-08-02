# cordova-base64-image-photo
将base64图片保存到手机相册

### 使用
1. 安装  
`cordova plugin add  https://github.com/longting/cordova-plugin-base64-image-photo.git`  
2. 使用  
```js
let imgBase64Str = "xxxx";
Base64Image.save(imgBase64Str,function(rs){
    console.log('保存成功！'+rs.url);
 }, function(error){
    console.warn('保存文件失败失败！'+error);
 });
```