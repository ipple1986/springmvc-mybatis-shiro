/**
 * var c = new ExpandTree($e);
 * c.open = function(open){
 *		if(open){
 *			$child.removeClass("hide");
 *		}else{
 *			$child.addClass("hide");
 *		}
 * };
 * c.child = function(){
 *		return this.$e.siblings("._child");
 * };
 * $e.find("a").click(return c.click(););
 * 
 * @param {type} $e
 * @param {type} opt
 * @returns {ExpandTree}
 */
function ExpandTree($e, opt) {
	this.$e = $e;
	$e.data("Contraction", this);
	$e.data("ExpandTree", this);
	$e.data("open", false);
	this.open = opt.open;
	this.child = opt.child;
	var _this = this;
	$e.bind("open", function(evt, data) {
		_this.open(data.open);
		if (!data.open) {
			_this.child().trigger("open", data);
		}
		_this.$e.data("open", data.open);
	});
}
ExpandTree.prototype.click = function() {
	var _this = this;
	return function() {
		var open = _this.$e.data("open");
		open = !open;
		_this.$e.trigger("open", {"open": open});
	};
};
ExpandTree.prototype.doClick = function() {
	var open = this.$e.data("open");
	open = !open;
	this.$e.trigger("open", {"open": open});
};