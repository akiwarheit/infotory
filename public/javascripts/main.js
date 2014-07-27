var dateHelper = {
	"initialize" : function(selector) {
		$('input[type="date"]').attr('type', 'text').addClass(
				selector.replace('.', ''));
		$(selector).datepicker({
			"format" : 'yyyy-mm-dd'
		});
	}
}

var products = {
	"initialize" : function() {
		$('#products-list').dataTable({
			"sDom" : "<f><rt><p>",
			"aoColumns" : [ {
				sWidth : '20%'
			}, {
				sWidth : '30%'
			}, {
				sWidth : '30%'
			}, {
				sWidth : '20%'
			} ],
			"aoColumnDefs" : [ {
				'bSortable' : false,
				'aTargets' : [ 3 ]
			}, {
				'bSearchable' : false,
				'aTargets' : [ 3 ]
			} ],
			"iDisplayLength" : 50,
			"aLengthMenu" : [ [ 5, 10, 25, 50, -1 ], [ 5, 10, 25, 50, "All" ] ]
		});
	}
};

$(function() {

	$('#menu').metisMenu({
		toggle : false
	});

	$('select.type-select').select2();

	$('#sidebar-toggle').click(function() {
		toggleSidebar();
	});

	dateHelper.initialize('.datetimepicker');
	products.initialize();
});

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};