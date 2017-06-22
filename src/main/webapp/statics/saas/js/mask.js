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