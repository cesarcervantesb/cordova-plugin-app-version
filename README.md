# cordova-plugin-application-info

[![npm](https://img.shields.io/github/package-json/v/cesarcervantesb/cordova-plugin-application-info
)](https://github.com/cesarcervantesb/cordova-plugin-application-info/)
![License](https://img.shields.io/github/license/cesarcervantesb/cordova-plugin-application-info?color=orange)

This plugin allows get the application information from the app.

## Installation

Install plugin from npm:

```
npm i @ccervantesb/cordova-plugin-application-info
```

Or install the latest master version from GitHub:

```
cordova plugin add https://github.com/cesarcervantesb/cordova-plugin-application-info
```

## Supported Platforms

- Android
- iOS

## Usage

The plugin creates the object cordova.plugins.mockLocation and is accessible after the deviceready event has been fired.

```js
document.addEventListener('deviceready', function () {
    // cordova.plugins.mockLocation is now available
}, false);
```

## Available methods

- `checkMockLocation` - check if current location is mock.
```js
mockLocation.checkMockLocation()
```
