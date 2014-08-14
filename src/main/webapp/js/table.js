define(['./eventSupport'], function(EventSupport) {
    return EventSupport.extend({
        init: function (departmentPage, headers, dataSource, dialog) {
            var $this = this;
            this.table = $('<table/>')
                .addClass('table')
                .appendTo($(departmentPage.div));

            if (headers) {
                var tr = $('<tr/>').appendTo(this.table);

                headers.forEach(function (header) {
                    $('<th/>')
                        .html(header)
                        .appendTo(tr);
                }, this);
            }
            if (dataSource) {
                //show list of employee
                this.table.on('click', 'td.list', function(e) {
                    var td = $(e.target);
                    var tr = td.parents('tr');
                    var data = tr.data('rowData');
                    $this.showEmployee(data);
                    e.preventDefault();
                });
                //delete department
                this.table.on('click', 'td.delete', function(e) {
                    var td = $(e.target);
                    var tr = td.parents('tr');
                    var data = tr.data('rowData');
                    $this.onDelete(data);
                });
                //update department
                this.table.on('click', 'td.update', function(e) {
                    var td = $(e.target);
                    var tr = td.parents('tr');
                    var data = tr.data('rowData');
                    $this.onUpdate(data);
                });

                dataSource.forEach(function (data) {
                    var tr = $('<tr/>')
                        .data('rowData', data)
                        .appendTo(this.table);
                    $('<td/>')
                        .html(data.dep_id)
                        .appendTo(tr);
                    $('<td/>')
                        .html(data.name)
                        .appendTo(tr);
                    $('<td/>')
                        .html('<button>Show List</button>')
                        .addClass('list')
                        .appendTo(tr);
                    $('<td/>')
                        .html('<button>Delete</button>')
                        .addClass('delete')
                        .appendTo(tr);
                    $('<td/>')
                        .html('<button>Update</button>')
                        .addClass('update')
                        .appendTo(tr);
                }, this);
            }
            this.dataSource = dataSource;
        },
        showEmployee: function (data) {
            this.fire('showEmployee',data);
        },
        onDelete:function(data){
            this.fire('deleteDepartment',data);
        },
        onUpdate:function(data){
            this.fire('updateDepartmentDialog',data);
        }
    });
});