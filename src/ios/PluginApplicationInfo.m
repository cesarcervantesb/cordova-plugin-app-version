#import "AppVersion.h"
#import <Cordova/CDVPluginResult.h>

@implementation PluginApplicationInfo

- (void)getApplicationInfo:(CDVInvokedUrlCommand *)command
{
    NSString* callbackId = command.callbackId;
    NSDictionary *result = [[NSDictionary alloc] init];
    NSString* appName = [[[NSBundle mainBundle] localizedInfoDictionary] objectForKey:@"CFBundleDisplayName"];
    NSString* packageName = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleIdentifier"];
    NSString* versionName = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"];
    NSString* versionCode = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleVersion"];

    result = @{@"appName": appName, @"packageName": packageName, @"versionName": versionName, @"versionCode": versionCode};

    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
}

@end