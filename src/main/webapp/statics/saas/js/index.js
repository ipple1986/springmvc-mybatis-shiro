$(function() {
	$(window).resize(function(){
		var vh = $(window).height();
		$("iframe[name='content']").css("min-height", (vh - 80) + "px");
		$(".index_nav").height(vh - 175);
		$(".main_cont").css("min-height", (vh - 20) + "px");
	});
	$(window).trigger("resize");
	//
	$(".index_main_nav p").each(function(i, e) {
		var $e = $(e);
		$e.bind("close", function() {
			$e.next("ul").addClass("hide");
			$e.find("a").removeClass("a_on").data("open", false);
		});
		$e.data("open", false);
		$e.find("a").click(function(evt) {
			var open = !$(this).data("open");
			if (open) {
				$e.siblings("p").trigger("close");
				$e.next("ul").removeClass("hide");
				$(this).addClass("a_on").data("open", true);
				
				if ($(this).attr("onclick") && $(this).attr("onclick") != null && $(this).attr("onclick") != "") {
					$(this).data("open", false);
					
					$(".index_main_nav .sub_nav li").each(function(i, e) {
						$(this).removeClass("li_on");
					});
				}
			} else {
				if (!$(this).attr("onclick") || $(this).attr("onclick") == null || $(this).attr("onclick") == "") {
					$e.trigger("close");
				}
			}
			evt.preventDefault();
		});
	});
	$(".index_main_nav .sub_nav li").each(function(i, e) {
		var $e = $(e);
		$e.find("a").click(function(evt) {
			$(".index_main_nav .sub_nav li").each(function(i, e) {
				$(this).removeClass("li_on");
			});
			
			$e.addClass("li_on");
			evt.preventDefault();
		});
	});
});
