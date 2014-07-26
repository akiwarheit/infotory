var dateHelper = {
    "initialize" : function(selector) {
        $('input[type="date"]').attr('type', 'text').addClass(selector.replace('.',''));
        $(selector).datepicker({
            "format" : 'yyyy-mm-dd'
        });
    }
}

$(function() {

    $('#menu').metisMenu({
        toggle : false
    });

    $('select.type-select').select2();

    $('#sidebar-toggle').click(function() {
        toggleSidebar();
    });

    dateHelper.initialize('.datetimepicker');
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