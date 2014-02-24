/**
 *
 *  Base64 encode / decode
 *  http://www.webtoolkit.info/
 *
 **/

var Base64 = {

    // private property
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

    // public method for encoding
    encode: function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;

        input = Base64._utf8_encode(input);

        while (i < input.length) {

            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
                this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

        }

        return output;
    },

    // public method for decoding
    decode: function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;

        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        while (i < input.length) {

            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

        }

        output = Base64._utf8_decode(output);

        return output;

    },

    // private method for UTF-8 encoding
    _utf8_encode: function (string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";

        for (var n = 0; n < string.length; n++) {

            var c = string.charCodeAt(n);

            if (c < 128) {
                utftext += String.fromCharCode(c);
            }
            else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            }
            else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }

        return utftext;
    },

    // private method for UTF-8 decoding
    _utf8_decode: function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;

        while (i < utftext.length) {

            c = utftext.charCodeAt(i);

            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            }
            else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }
            else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }

        }

        return string;
    }

}

function selectText(element) {
    var doc = document,
        text = doc.getElementById(element),
        range,
        selection;

    if (doc.body.createTextRange) {
        range = document.body.createTextRange();
        range.moveToElementText(text);
        range.select();
    } else if (window.getSelection) {
        selection = window.getSelection();
        range = document.createRange();
        range.selectNodeContents(text);
        selection.removeAllRanges();
        selection.addRange(range);
    }
}
var wechatnavtitle = $(".nav [href='/admin/groups']")
var originalWeChatGroupTitle = wechatnavtitle.text();

function updateWeChatGroupStatus(callback) {
    $.get("/api/group/qrcodes").done(function (data) {
        if (data > 999) {
            wechatnavtitle.text(originalWeChatGroupTitle + "(999+)");
        } else {
            wechatnavtitle.text(originalWeChatGroupTitle + "(" + data + ")");
        }
        if (callback) {
            callback(data);
        }
    });
}

if (!window.btoa) {
    window.btoa = function (str) {
        return Base64.encode(str);
    }
}

if (!window.atob) {
    window.atob = function (str) {
        return Base64.decode(str);
    }
}

(function ($) {

    $(".nav-collapse").css("display", "block");


    var DataModel = function () {
        var self = this;
        var currentLoginCookieName = "currentLogin";
        self.username = ko.observable("");
        self.isLogin = ko.observable(false);

        self.navhomeclass = ko.observable("");
        self.navtaskclass = ko.observable("");
        self.navcrewclass = ko.observable("");
        self.navathleteclass = ko.observable("");
        self.navgroupclass = ko.observable("");
        self.navstatclass = ko.observable("");

        self.init = function () {
            var currentLogin = $.cookie(currentLoginCookieName);
            if (currentLogin) {
                self.isLogin(true);
                var credentialObj = JSON && JSON.parse(currentLogin) || $.parseJSON(currentLogin);
                ;
                self.username(credentialObj.userName);
                self.setupAjaxAuthHeader(credentialObj.credential);
                if (window.location.pathname == "/admin") {
                    window.location.pathname = "/admin/tasks";
                }

                updateWeChatGroupStatus();

            }
            var currentPath = window.location.pathname;

            if (currentPath.match("^\/admin\/tasks")) {
                self.navtaskclass("active");
            } else if (currentPath.match("^\/admin\/crews")) {
                self.navcrewclass("active");
            } else if (currentPath.match("^\/admin\/athlete")) {
                self.navathleteclass("active");
            } else if (currentPath.match("^\/admin\/groups")) {
                self.navgroupclass("active");
            } else if (currentPath.match("^\/admin\/stat")) {
                self.navstatclass("active");
            } else {
                self.navhomeclass("active");
            }
        }
        self.login = function () {
            var username = $("#idEmail").val();
            var psw = $("#idPassword").val();
            $.post("/security", { username: username, password: psw })
                .done(function (data) {
                    self.username(eval(data).userName);
                    self.isLogin(true);
                    var credentialObj = self.encodeCredential(username, psw);
                    $.cookie(currentLoginCookieName, JSON.stringify(credentialObj), { expires: 1 });
                    self.setupAjaxAuthHeader(credentialObj.credential);
                    window.location.pathname = "/admin/tasks";
                }).fail(function () {
                });
        };

        self.encodeCredential = function (username, psw) {
            return {
                userName: username,
                credential: 'Basic ' + btoa(username + ":" + psw)
            };

        };
        self.setupAjaxAuthHeader = function (credential) {
            $.ajaxSetup({
                headers: { 'Authorization': credential }
            });
        }
        self.logout = function () {
            if (self.isLogin()) {
                self.isLogin(false);
            }
            $.cookie(currentLoginCookieName, null);
            $.removeCookie(currentLoginCookieName, {path: '/'});

            window.location.pathname = "/admin";

        }

    }
    var dataModel = new DataModel();
    dataModel.init();


    ko.applyBindings(dataModel, document.getElementById("idHeader"));


})($ || jQuery);
