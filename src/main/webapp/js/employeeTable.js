define(['./eventSupport'], function(EventSupport) {
    return EventSupport.extend({
        init: function (employeePage, headers, dataSource) {
            var $this = this;
            this.table = $('<table/>')
                .addClass('employeeTable')
                .appendTo($(employeePage.div));
            $('.employeeTable').empty();
            if (headers) {
                var tr = $('<tr/>')
                    .appendTo(this.table);
                headers.forEach(function (header) {
                    $('<th/>')
                        .html(header)
                        .appendTo(tr);
                }, this);
            }
            if (dataSource) {
                //delete employee
                this.table.on('click', 'td.deleteEmployee', function(e) {
                    var td = $(e.target);
                    var tr = td.parents('tr');
                    var data = tr.data('rowData');
                    $this.onDelete(data);
                });
                //update employee
                this.table.on('click', 'td.updateEmployee', function(e) {
                    var td = $(e.target);
                    var tr = td.parents('tr');
                    var data = tr.data('rowData');
                    $this.onUpdate(data);
                    /*new UpdateEmployee(employeePage, data)*/
                });
                dataSource.forEach(function (data) {
                    var tr = $('<tr/>')
                        .data('rowData',data)
                        .appendTo(this.table);
                    $('<td/>')
                        .html(data.empl_id)
                        .appendTo(tr);
                    $('<td/>')
                        .html(data.email)
                        .appendTo(tr);
                    $('<td/>')
                        .html(data.salary)
                        .appendTo(tr);
                    $('<td/>')
                        .html(data.birthday)
                        .appendTo(tr);
                    $('<td/>')
                        .html('<button>Delete</button>')
                        .addClass('deleteEmployee')
                        .appendTo(tr);
                    $('<td/>')
                        .html('<button>Update</button>')
                        .addClass('updateEmployee')
                        .appendTo(tr);
                }, this)
            }
            this.dataSource = dataSource;
        },
        onDelete:function(data){
            this.fire('deleteEmployee',data);
        },
        onUpdate:function(data){
            this.fire('updateEmployeeDialog',data);
        }
    });
});