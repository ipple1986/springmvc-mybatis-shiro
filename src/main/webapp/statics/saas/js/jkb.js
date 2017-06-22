$(function () {
    /*弃用*/
    if ($('body').attr('nc') == 'yes') {
        //menuScroll();
        $('html').attr('style', 'background:url(../images/bg_main_left.png) repeat-y #f5f5f5;');
    }
});

/*页面滚动调整*/
function menuScroll() {
    var height = $('html').height();
    var pIframe = top.window.parentDom.find('.jkb-menu-bodyIframe');
    pIframe.height(height);
}




/*下拉框*/
function SlidingLayer($e, $layer) {
    this.$e = $e;
    this.$layer = $layer;
    this.timer = null;
    this.opt = {};
    if (arguments.length > 2) {
        this.opt = arguments[2];
    }
    this.init();
}
SlidingLayer.prototype.hide = function (_this) {
    _this.$layer.hide();
    if (_this.opt.clazz) {
        _this.$e.removeClass(_this.opt.clazz);
    }
};
SlidingLayer.prototype.init = function () {
    var _this = this;
    _this.$e.bind("click.SlidingLayer", function () {
        clearTimeout(_this.timer);
        if (_this.opt.clazz) {
            _this.$e.addClass(_this.opt.clazz);
        }
        _this.$layer.show();
    });
    _this.$e.bind("mouseleave.SlidingLayer", function () {
        _this.timer = setTimeout(function () {
            _this.hide(_this);
        }, 300);
    });
    _this.$layer.bind("click.SlidingLayer", function () {
        event.stopPropagation();
        _this.timer = setTimeout(function () {
            _this.hide(_this);
        }, 300);
    });
    _this.$layer.bind("mouseenter.SlidingLayer", function () {
        clearTimeout(_this.timer);
    });
    _this.$layer.bind("mouseleave.SlidingLayer", function () {
        _this.timer = setTimeout(function () {
            _this.hide(_this);
        }, 300);
    });
};

/*loading画面*/
$.extend({
    loadShow: function (text) {
        if (text == undefined) {
            text = "努力加载中";
        }
        var menuDom;
        if (typeof (parentDom) == "undefined") {
            menuDom = top.window.parentDom;
        } else {
            menuDom = parentDom;
        }

        if (menuDom.find('.loadMask').length != 0) {
            return;
        } else {
            var html = '<table class="loadMask">' +
                '<tr>' +
                '<td>' +
                '<div class="loadMess">' +
                '<img src="images/5-121204193R0-50.gif" /><br>' +
                '<span>' + text + '</span>' +
                '</div>' +
                '</td>' +
                '</tr>' +
                '</table>';
            menuDom.find('html').css('overflow', 'hidden');
            menuDom.find('html').append(html);
        }
    },
});
$.extend({

    loadHide: function () {
        var menuDom;
        if (typeof (parentDom) == "undefined") {
            menuDom = top.window.parentDom;
        } else {
            menuDom = parentDom;
        }
        if (menuDom.find('.loadMask').length != 0) {
            menuDom.find('.loadMask').remove();
        }
    }
});

/*让元素永远居中*/
;
(function ($, window, document, undefined) {
    var Center = function (ele) {
        this.$element = ele;
    }

    Center.prototype = {
        center: function () {
            return this.$element.each(function () {
                var $this = $(this);
                var screenWidth = $(window).width(),
                    screenHeight = $(window).height(); //当前浏览器窗口的 宽高 
                var scrolltop = $(document).scrollTop(); //获取当前窗口距离页面顶部高度 
                var objLeft = (screenWidth - $this.width()) / 2;
                var objTop = (screenHeight - $this.height()) / 2 + scrolltop;
                console.log(scrolltop + "," + objTop)
                $this.css({
                    left: objLeft + 'px',
                    top: objTop + 'px',
                    'display': 'blcok'
                });
                //浏览器窗口大小改变时 
                $(window).resize(function () {
                    if (!$this.is(":hidden")) {
                        screenWidth = $(window).width();
                        screenHeight = $(window).height();
                        scrolltop = $(document).scrollTop();
                        objLeft = (screenWidth - $this.width()) / 2;
                        objTop = (screenHeight - $this.height()) / 2 + scrolltop;
                        $this.css({
                            left: objLeft + 'px',
                            top: objTop + 'px',
                            'display': 'block'
                        });
                    }
                });
                //浏览器有滚动条时的操作、 
                $(window).scroll(function () {
                    if (!$this.is(":hidden")) {
                        screenWidth = $(window).width();
                        screenHeight = $(window).height();
                        scrolltop = $(document).scrollTop();
                        objLeft = (screenWidth - $this.width()) / 2;
                        objTop = (screenHeight - $this.height()) / 2 + scrolltop;
                        $this.css({
                            left: objLeft + 'px',
                            top: objTop + 'px',
                            'display': 'block'
                        });
                    }
                });
            });
        }
    }

    $.fn.myCenter = function () {
        var center = new Center(this);

        return center.center();
    }
})(jQuery, window, document);

/*弹窗方法*/
var JkbAlert = function (id, $doc, url, opt) {
    this.ele = $doc;
    this.url = url;
    this.opt = opt;
    this.id = id;
}

JkbAlert.prototype = {
        show: function () {
            var initUrl = this.url;
            var initOpt = this.opt
            var id = this.id;
            return this.ele.each(function () {
                var defaultCss = {
                    'border-radius': '5px',
                    'overflow-x': 'hidden'
                }
                var css = $.extend(defaultCss, initOpt);
                //遮罩
                if ($('.jkb-maskTable').length == 0) {
                    var mask = '<table id="'+ id +'" class="jkb-maskTable"><tr><td></td></tr></table>';
                    $(this).find('body').append(mask);
                }
                //弹框
                var mess = '<iframe name="jkb-messIframe" class="jkb-messIframe" src="' + initUrl + '" frameborder="0"></iframe>';
                $(this).find('.jkb-maskTable tr td').append(mess);
                $(this).find('#' + id).find('iframe').css(css);
                $(this).find('html').css('overflow', 'hidden');
            });
            return this;
        }
    }
/*使用方法*/
/*var mess = new JkbAlert(弹出框ID, $(window.parent.parentDom), '弹框地址url', {'height': '220','width': '422'}).show();;*/

/*提示信息弹框 
option一个对象,属性有title,text,type,contentStatus
title -- 弹窗的标题，
text -- 当弹窗内容只是一段文字时的文本信息
type -- type=01 单按钮  type=02 双单按钮,
contentStatus -- 判断弹窗内容为文本还是一段html
--------具体用法  属性之间顺序没有规定---------
{title:'输入城市名称',type:'02',contentStatus:'html'}
*/

function JkbAlertTips01(option) {
    var messTitle = option.title || '提示';
    var messText = option.text || '默认弹出框提示文字';
    var messType = option.type || '01';
    var messcontentStatus = option.contentStatus || 'text';
    var html = '<div class="jkb-textInput03 jkb-mess-tips" style="width:180px;">'+
                    '<input class="textInputRight" type="text" style="width:100%;">'+
                '</div>';
    var $obj=$(window.parent.parentDom);
    if ($('.jkb-maskTable').length == 0) {
        var button='<button class="jkb-btn08">确定</button>';
        if(messType=='01'){
            button='<button class="jkb-btn08">确定</button>';
        }else if(messType=="02"){
           button='<button class="jkb-btn08">确定</button><button onclick="JkbAlertTips01Close()" class="jkb-btn12">取消</button>';
        }
        var mask = '<table class="jkb-maskTable">'+
                       '<tr>'+
                           '<td>'+
                                '<div class="jkb-mess" style="width:422px;margin:auto;">'+
                                    '<div class="jkb-mess-title">'+
                                        '<div class="jkb-mess-titleLeft">'+
                                            '<div class="jkb-mess-titleLeft-text">'+messTitle+'</div>'+
                                        '</div>'+
                                        '<div class="jkb-mess-titleRight">'+
                                            '<img class="jkb-mess-cancelImg" src="../images/cancel.png" onclick="JkbAlertTips01Close()" alt="">'+
                                        '</div>'+
                                    '</div>'+   
                                    '<div class="jkb-mess-context">'+
                                        (messcontentStatus == 'html' ? html : '<p class="jkb-mess-tips">'+messText+'</p>' )+
                                    '</div>'+
                                    '<div class="jkb-mess-btnRow">'+button+'</div>'
                                '</div>'+
                           '</td>'+
                       '</tr>'+
                   '</table>';
        $obj.find('body').append(mask);
    }
    
}
/*提示信息弹框关闭方法*/
function JkbAlertTips01Close(){
    $('html').css('overflow', 'visible');
    $('.jkb-maskTable').remove();
}

/*关闭弹窗方法*/
function jkbCloseMess() {
    $(window.parent.parentDom).find('html').css('overflow', 'visible');
    $(window.parent.parentDom).find('.jkb-maskTable').remove();
}

/*提交弹窗方法*/
function jkbSubMitMess(callback) {
    callback();
    $(window.parent.parentDom).find('html').css('overflow', 'visible');
    $(window.parent.parentDom).find('.jkb-maskTable').remove();
}
