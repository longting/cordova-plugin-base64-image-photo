//
//  Base64Image.m
//  cordovaTest
//
//  Created by longting on 2019/8/2.
//

#import "Base64Image.h"

@implementation Base64Image

-(void) save:(CDVInvokedUrlCommand *)command{
    if(command.arguments.count>0){
        NSString* base64 = command.arguments[0];
        
        NSArray *imageArray = [base64 componentsSeparatedByString:@","];
        
        NSData *imageData = [[NSData alloc] initWithBase64EncodedString:imageArray[1] options:NSDataBase64DecodingIgnoreUnknownCharacters];
        
        UIImage *image = [UIImage imageWithData:imageData];
        
        UIImageWriteToSavedPhotosAlbum(image, nil, nil, nil);
       
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"保存图片"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }else{
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"没有参数"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
    
}

@end
