var $$ = function (id) {
    return document.getElementById(id);
}
var GetColor = function (rR, rG, rB, tR, tG, tB, rgb) {
    this.rR = rR;
    this.rG = rG;
    this.rB = rB;
    this.tR = tR;
    this.tG = tG;
    this.tB = tB;
    this.rgb = rgb;
}
GetColor.prototype = {
    range: function () {
        textSetRange(this.rR, this.rG, this.rB);
        this.rgb = "rgb(" + this.rR + ", " + this.rG + ", " + this.rB + ")";
        return this.rgb;
    },
    text: function () {
        rangeSetText(this.tR, this.tG, this.tB);
        this.rgb = "rgb(" + this.tR + ", " + this.tG + ", " + this.tB + ")";
        return this.rgb;
    }
}
var rangeSetText = function (tR, tG, tB) {
    $$("raeR").value = tR;
    $$("raeG").value = tG;
    $$("raeB").value = tB;
}
var textSetRange = function (rR, rG, rB) {
    $$("txtR").value = rR;
    $$("txtG").value = rG;
    $$("txtB").value = rB;
}
var previewColor = function (color) {
    $$("colorShow").style.backgroundColor = color;
}
var changeColor = function (rName, tName) {
    var i, j, getColor, oRaeColor = document.getElementsByName(rName),
        oTxtColor = document.getElementsByName(tName);
    var re = /^d*$/;
    for (i = 0; i < oRaeColor.length; i++) {
        oRaeColor[i].onchange = function () {
            getColor = new GetColor($$("raeR").value, $$("raeG").value, $$("raeB").value, $$("txtR").value, $$("txtG").value, $$("txtB").value, null);
            previewColor(getColor.range());
        }
    }
    for (j = 0; j < oTxtColor.length; j++) {
        oTxtColor[j].onkeyup = function () {
            if (isNaN($(this).val())) {
                this.value = "";
            } else {
                if ($(this).val() > 255) {
                    $(this).val(255);
                }
                getColor = new GetColor($$("raeR").value, $$("raeG").value, $$("raeB").value, $$("txtR").value, $$("txtG").value, $$("txtB").value, null);
                previewColor(getColor.text());
            }
        }
    }
}
window.onload = function () {
    changeColor("raeColor", "txtColor");
}

/*颜色转换*/
var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
/*十六进制转RGB*/
String.prototype.colorRgb = function () {
    var sColor = this.toLowerCase();
    if (sColor && reg.test(sColor)) {
        if (sColor.length === 4) {
            var sColorNew = "#";
            for (var i = 1; i < 4; i += 1) {
                sColorNew += sColor.slice(i, i + 1).concat(sColor.slice(i, i + 1));
            }
            sColor = sColorNew;
        }
        //处理六位的颜色值  
        var sColorChange = [];
        for (var i = 1; i < 7; i += 2) {
            sColorChange.push(parseInt("0x" + sColor.slice(i, i + 2)));
        }
        return "RGB(" + sColorChange.join(",") + ")";
    } else {
        return sColor;
    }
};

/*RGB转十六进制*/
String.prototype.colorHex = function () {
    var that = this;
    if (/^(rgb|RGB)/.test(that)) {
        var aColor = that.substring(4,that.length-1).split(",");
        var strHex = "#";
        for (var i = 0; i < aColor.length; i++) {
            var hex = Number(aColor[i]).toString(16);
            if (hex === "0") {
                hex += hex;
            }
            strHex += hex;
        }
        if (strHex.length != 7) {
            strHex = that;
        }
        return strHex;
    } else if (reg.test(that)) {
        var aNum = that.replace(/#/, "").split("");
        if (aNum.length === 6) {
            return that;
        } else if (aNum.length === 3) {
            var numHex = "#";
            for (var i = 0; i < aNum.length; i += 1) {
                numHex += (aNum[i] + aNum[i]);
            }
            return numHex;
        }
    } else {
        return that;
    }
};