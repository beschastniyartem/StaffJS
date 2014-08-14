define(['./PageController','./ajaxDataSource','./table','./addDepartmentDialog','./updateDepartmentDialog'],
    function(PageController,AjaxDataSource,Table,AddDepartmentDialog,UpdateDepartmentDialog) {
    return PageController.extend({
        init: function () {
            this._super();
            this.setPageTitle("Departmetn List");
            this.departmentList();
            this.departmentAddDialog();

        },

        departmentList: function () {
            var $this = this;
            var ajax = new AjaxDataSource();
            ajax.refresh('/rest/department', function success(data) {
                var header = ["Department ID", "Name"];
                $('.table').empty();
                this.table = new Table($this, header, data);
                this.table.subscribe('deleteDepartment', function (e, data) {
                    $this.onDDCl(e, data);
                }, this);
                this.table.subscribe('showEmployee', function (e, data) {
                    $this.onGoToEmployerListPage(e, data);
                }, this);
                this.table.subscribe('updateDepartmentDialog', function (e, data) {
                    this.departmentUpdateDialog(e, data);
                }, $this);
            });
        },

        departmentAddDialog: function () {
            this.addDialog = new AddDepartmentDialog(this);
            this.addDialog.subscribe('addDepartment', function (e, data) {
                this.onDAdCl(e, data);
            }, this);
        },

        departmentUpdateDialog: function (e, data) {

            $('.updateDepartmentDialog').empty();
            this.updateDialog = new UpdateDepartmentDialog(this.div, data);
            this.updateDialog.subscribe('updateDepartment', function (e, data) {
                this.onDUpCl(e, data);
            }, this);
        },

        onDAdCl: function (e, data) {
            $this = this;
            $('.exception').empty();
            if (data.name) {
                $.post('/rest/add', data, function (response) {
                    if (!response) {
                        $this.departmentList();
                    } else {
                        $this.exceptionMessage(response, $this.addDialog.div);
                    }
                });
                /*  e.preventDefault();*/
            } else {
                this.exceptionMessage('Not Empty', this.addDialog.div);
                /*  e.preventDefault();*/
            }
        },

        onDUpCl: function (e, data) {
            $this = this;
            $('.exception').empty();
            if (data.name) {
                $.post('/rest/edit', data, function (response) {
                    if (!response) {
                        $this.updateDialog.div.hide();
                        $this.departmentList();
                    }
                    else {
                        $this.exceptionMessage(response, $this.updateDialog.div);
                    }
                });
                e.preventDefault();
            } else {
                $this.exceptionMessage('Not Empty', $this.updateDialog.div);
                e.preventDefault();
            }
        },

        onDDCl: function (e, data) {
            $this = this;
            $.get('/rest/delete', data, function () {
                $this.departmentList();
            });
        },

        onGoToEmployerListPage: function (e, data) {
            this.hide();
            var controller = this.getController('EmplListPage');
            controller.show(data.dep_id);
        },

        exceptionMessage: function (message, exceptionContainer) {
            this.exceptionMessageForm = $('<label/>')
                .addClass('exception')
                .html(message)
                .insertAfter($(exceptionContainer));
        },

        _getName: function () {
            return 'DepListPage';
        }
    });
});