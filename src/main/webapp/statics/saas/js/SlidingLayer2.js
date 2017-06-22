function SlidingLayer($e, $layer) {
	this.$e = $e;
	this.$layer = $layer;
	this.timer = null;
	this.opt = {timeout: 200};
	if (arguments.length > 2) {
		for (var k in arguments[2]) {
			if (this.opt[k] !== undefined) {
				this.opt[k] = arguments[2];
			}
		}
	}
	this.init();
}
SlidingLayer.prototype.hide = function() {
	this.$layer.addClass("hide");
	if (this.opt.clazz) {
		this.$e.removeClass(this.opt.clazz);
	}
};
SlidingLayer.prototype.init = function() {
	var _this = this;
	_this.$e.bind("mouseenter.SlidingLayer", function() {
		clearTimeout(_this.timer);
		if (_this.opt.clazz) {
			_this.$e.addClass(_this.opt.clazz);
		}
		_this.$layer.removeClass("hide");
	});
	_this.$e.bind("mouseleave.SlidingLayer", function() {
		_this.timer = setTimeout(function() {
			_this.hide();
		}, _this.opt.timeout);
	});
	_this.$layer.bind("mouseenter.SlidingLayer", function() {
		clearTimeout(_this.timer);
	});
	_this.$layer.bind("mouseleave.SlidingLayer", function() {
		_this.timer = setTimeout(function() {
			_this.hide();
		}, _this.opt.timeout);
	});
};
