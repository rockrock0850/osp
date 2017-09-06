var PERIPHERAL_MANAGER_INVOKE_URL = "http://localhost:8088/PeripheralManager/invoke";

function doOpenBrowser( browserType, url, urlDecodeFlag, singleFlag, alwaysOpen, linkId ) {
	if(browserType.toLowerCase()=="chrome") {
		browserType = "Chrome";
	}else{
		browserType = "IE";
	}
	//URL encode
	if(urlDecodeFlag == undefined) {
		urlDecodeFlag = "false";
	}
	if(urlDecodeFlag=="true") {
		url = encodeURIComponent(url);
	}
	//固定開一個視窗
	if(singleFlag == undefined) {
		singleFlag = "false";
	}

	//永遠開啟
	if(alwaysOpen == undefined) {
		alwaysOpen = "N";
	}
	//連結代碼
	if(linkId == undefined) {
		linkId = Math.floor((1 + Math.random()) * 0x10000);
	}
	var data = {
			"DeviceType": "DEVICETYPE_9",
			"Action": browserType,
			"URL": url,
			"isTesting": "true",
			"urlDecodeFlag": urlDecodeFlag,
			"singleFlag" : singleFlag,
			"alwaysOpen" : alwaysOpen,
			"linkId" : linkId
	};
	//OS is Windows and browser is Chrome allow use 
	if(detectOS() == "Windows" && chromeDetect()) {
		browserCallDM(data);
	} else {
		return false;
	}
}
function browserCallDM(data) {
	console.info("Start browserCallDM...");
	$.ajax({
        type:  'GET',
		url:  PERIPHERAL_MANAGER_INVOKE_URL, //from scripts\portal\common.js
		crossDomain: true,
		timeout: 5000,
		data: data,
        dataType: 'jsonp',
		jsonp: 'callback',
        jsonpCallback: 'onReceive',//jsonp callback function
		cache: false
    }).fail(function(jqXHR, textStatus){
		if(textStatus === 'timeout') {
			alert( PERIPHERAL_MANAGER_INVOKE_URL + ' is timeout. Please check PMC and Chrome settings.');
			console.error( PERIPHERAL_MANAGER_INVOKE_URL + ' is timeout. Please check PMC and Chrome settings.');
		}
    });
}

function onReceive(res) {
    var rtnObj = getReturnObject(res);
    if(rtnObj.deviceType == 'certificate') {
    	if(rtnObj.status == "S-1") {
			doPassCertificate(res);
        } else {
            // return result is error
        	$('#div_dialog_downloadCertificate').modal({ backdrop : 'static', keyboard: false });
        }
    } else if(rtnObj.deviceType == 'browser') {
    	if(rtnObj.status == "S-1") {
            console.info("browser OK!");
         } else {
         	alert(rtnObj.errtMsg);
         }
     } else {
     	if(rtnObj.status == "S-1") {
     		console.info("other OK!");
         } else {
         	alert(rtnObj.errtMsg);
         }
     }

}
function getReturnObject(resultData) {
	var data = {
		status:'',
		errtCode:'',
		errtMsg:'',
		deviceType:''
	};
	data.status = resultData.split("<status>")[1];
	data.status = data.status.split("</status>")[0];
	data.errtCode = resultData.split("<errtCode>")[1];
	data.errtCode = data.errtCode.split("</errtCode>")[0];
	data.errtMsg = resultData.split("<errtMsg>")[1];
	data.errtMsg = data.errtMsg.split("</errtMsg>")[0];
	data.deviceType = resultData.split("<deviceType>")[1];
	if(data.deviceType==undefined){
		data.deviceType = '';
	} else {
		data.deviceType = data.deviceType.split("</deviceType>")[0];
	}
	return data;
}
function chromeDetect() {
	var ua = navigator.userAgent;
	var match = /(Chrome)[ \/]([\w.]+)/.exec( ua ) || /(Trident)[ \/]([\w.]+)/.exec( ua ) ||
	/(webkit)[ \/]([\w.]+)/.exec( ua ) ||
	/(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
	/(msie) ([\w.]+)/.exec( ua ) ||
	ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
	[];
	var isChrome = false;
	if(match[1] != undefined) {
		if(match[1].toLowerCase()=="chrome") {
			isChrome = true;
		}
	}
	return isChrome;
}
function browserDetect(browserType) {
	var ua = navigator.userAgent;
	var match = /(Chrome)[ \/]([\w.]+)/.exec( ua ) || /(Trident)[ \/]([\w.]+)/.exec( ua ) ||
	/(webkit)[ \/]([\w.]+)/.exec( ua ) ||
	/(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
	/(msie) ([\w.]+)/.exec( ua ) ||
	ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
	[];
	var isChrome = false;
	if(match[1] != undefined) {
		if(match[1].toLowerCase()=="chrome") {
			isChrome = true;
		}
	}
	var isSame = false;
	if(isChrome) {
		if(browserType.toLowerCase()=="chrome") {
			isSame = true;
		}
	} else {
		if(!(browserType.toLowerCase()=="chrome")) {
			isSame = true;
		}
	}
	return isSame;
}

function detectOS() {
    var os, ua = navigator.userAgent;
    if (ua.match(/Win(dows )?NT 6\.1/)) {
    	os = "Windows";			// Windows 7
    }
    else if (ua.match(/Win(dows )?NT 6\.0/)) {
    	os = "Windows";			// Windows Vista
    }
    else if (ua.match(/Win(dows )?NT 5\.2/)) {
    	os = "Windows";		// Windows Server 2003
    }
    else if (ua.match(/Win(dows )?(NT 5\.1|XP)/)) {
    	os = "Windows";		// Windows XP
    }
    else if (ua.match(/Win(dows )? (9x 4\.90|ME)/)) {
    	os = "Windows";		// Windows ME
    }
    else if (ua.match(/Win(dows )?(NT 5\.0|2000)/)) {
    	os = "Windows";		// Windows 2000
    }
    else if (ua.match(/Win(dows )?98/)) {
    	os = "Windows";			// Windows 98
    }
    else if (ua.match(/Win(dows )?NT( 4\.0)?/)) {
    	os = "Windows";				// Windows NT 4.0
    }
    else if (ua.match(/Win(dows )?95/)) {
    	os = "Windows";			// Windows 95
    }
    else if (ua.match(/Mac|PPC/)) {
    	os = "Mac OS";					// Macintosh
    }
    else if (ua.match(/Linux/)) {
    	os = "Linux";					// Linux
    }
    else if (ua.match(/(Free|Net|Open)BSD/)) {
    	os = RegExp.$1 + "BSD";				// BSD
    }
    else if (ua.match(/SunOS/)) {
    	os = "Solaris";					// Solaris
    }
    else {
    	os = "Unknown";					// Other OS
    }
    return os;
}
// 2016-0411 Wilson 判斷是否開個單一IE
// openMultileIE 開啟多個IE
function getSingleFlag( openMultileIE ) {
	var singleFlag = "true";

	if( openMultileIE == undefined ) {
		return "false";
	}

	if( openMultileIE.toLowerCase() == "true" ) {
		singleFlag = "false";
	}

	return singleFlag;
}

function fBrowserRedirect() {
	console.log("---[MobileCounter]--- navigator.userAgent="+navigator.userAgent.toLowerCase());
    var mUserAgent = navigator.userAgent.toLowerCase();
    var mIsIpad = mUserAgent.match(/ipad/i) == "ipad";
    var mIsIphoneOs = mUserAgent.match(/iphone os/i) == "iphone os";
    var mIsAndroid = mUserAgent.match(/android/i) == "android";
    var mIsWM = mUserAgent.match(/windows mobile/i) == "windows mobile";
    if(mIsIpad) {
        // ipad
    	console.log("---[MobileCounter]--- is iPad");
		alert("this is the Ipad!!");
        return true;
    } else if(mIsIphoneOs || mIsAndroid || mIsWM) {
        // iphone, android
    	console.log("---[MobileCounter]--- is SmartPhone");
		alert("this is the SmartPhone");
    	return true;
    } else {
    	console.log("---[MobileCounter]--- is PC");
		alert("this is the pc!!");
    	return false;
    }
    
}