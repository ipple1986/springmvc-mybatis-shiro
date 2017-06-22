//鼠标进入弹出下拉框
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
	_this.$e.bind("mouseenter.SlidingLayer", function () {
		clearTimeout(_this.timer);
		if (_this.opt.clazz) {
			_this.$e.addClass(_this.opt.clazz);
		}
		_this.$layer.show();
	});
	_this.$e.bind("mouseleave.SlidingLayer", function () {
		_this.timer = setTimeout(function () {
			_this.hide(_this);
		}, 200);
	});
	_this.$layer.bind("mouseenter.SlidingLayer", function () {
		clearTimeout(_this.timer);
	});
	_this.$layer.bind("mouseleave.SlidingLayer", function () {
		_this.timer = setTimeout(function () {
			_this.hide(_this);
		}, 200);
	});
};


//点击弹出下拉框
function SlidingLayerClick($e, $layer) {
	this.$e = $e;
	this.$layer = $layer;
	this.timer = null;
	this.opt = {};
	if (arguments.length > 2) {
		this.opt = arguments[2];
	}
	this.init();
}
SlidingLayerClick.prototype.hide = function (_this) {
	_this.$layer.hide();
	if (_this.opt.clazz) {
		_this.$e.removeClass(_this.opt.clazz);
	}
};
SlidingLayerClick.prototype.init = function () {
	var _this = this;
	_this.$e.bind("click.SlidingLayerClick", function () {
		clearTimeout(_this.timer);
		if (_this.opt.clazz) {
			_this.$e.addClass(_this.opt.clazz);
		}
		_this.$layer.show();
	});
	_this.$e.bind("mouseleave.SlidingLayerClick", function () {
		_this.timer = setTimeout(function () {
			_this.hide(_this);
		}, 200);
	});
	_this.$layer.bind("mouseenter.SlidingLayerClick", function () {
		clearTimeout(_this.timer);
	});
	_this.$layer.bind("mouseleave.SlidingLayerClick", function () {
		_this.timer = setTimeout(function () {
			_this.hide(_this);
		}, 200);
	});
};

/*
 timer: null,
 hide: function () {
 var _this = $("a.inner_nav_ul_select");
 $(_this).siblings("p").hide();
 $(_this).removeClass("inner_nav_ul_select_hover");
 },
 init: function () {
 $("a.inner_nav_ul_select").mouseover(function () {
 clearTimeout(inner_nav_ul_select.timer);
 $(this).addClass("inner_nav_ul_select_hover");
 $(this).siblings("p").show();
 });
 $("a.inner_nav_ul_select").mouseout(function () {
 inner_nav_ul_select.timer = setTimeout(inner_nav_ul_select.hide, 200);
 });
 $("a.inner_nav_ul_select").siblings("p").mouseover(function () {
 clearTimeout(inner_nav_ul_select.timer);
 });
 $("a.inner_nav_ul_select").siblings("p").mouseout(function () {
 inner_nav_ul_select.timer = setTimeout(inner_nav_ul_select.hide, 200);
 });
 }
 */