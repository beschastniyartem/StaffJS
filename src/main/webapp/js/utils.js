PageController = EventSupport.extend({
    init: function () {
        if (!window['controllers']) {
            window['controllers'] = {};
        }
        window['controllers'][this._getName()] = this;

        this.div = $('<div/>')
            .addClass("container")
            .appendTo($('body'));
    },

    show: function () {
        this.div.show();
    },

    hide: function () {
        this.div.hide();
    },

    _getName: function () {

    },

    getController: function (name) {
        var controller = window['controllers'][name];
        if (!controller) {
            controller = window['controllers'][name] = new window[name]();
        }
        return controller;
    },

    setPageTitle: function (name) {
        $('<h1/>')
            .html(name)
            .addClass('pageTitle')
            .appendTo(this.div);
    }
});
DepListPage = PageController.extend({
    init: function () {
        $this = this;
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
                $this.departmentUpdateDialog(e, data);
            }, $this);
            $thisTable = this.table;
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
        var name = data.text;
        if (name) {
            var json = { "dep_id": "0", "name": name};
            $.post('/rest/add', json, function (response) {
                if (!response) {
                    $this.departmentList();
                } else {
                    $this.exceptionMessage(response, $this.addDialog.div);
                }
            });
            e.preventDefault();
        } else {
            this.exceptionMessage('Not Empty', this.addDialog.div);
            e.preventDefault();
        }
    },

    onDUpCl: function (e, data) {
        $this = this;
        $('.exception').empty();
        var name = data.text;
        var json = { "dep_id": data.dep_id, "name": name};
        if (name) {
            $.post('/rest/edit', json, function (response) {
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
EmplListPage = PageController.extend({
    initialized: false,

    _getName: function () {
        return 'EmplListPage';
    },

    show: function (id) {
        if (!this.initialized) {
            this.onGoToDepartmentListPageSubmit();
        }
        this.employeeAddDialog(id);
        this.initialize(id);
        this.initialized = true;
        this._super();
    },

    initialize: function (id) {
        if (this.employeeList) {
            this.employeeList(id);
        }
    },

    employeeList: function (dep_id) {
        var $this = this;
        var ajax = new AjaxDataSource();
        ajax.refresh('/rest/employee/' + dep_id, function success(data) {
            var header = ["Employee ID", "Email", "Salary", "Birthday"];
            this.employeeTable = new EmployeeTable($this, header, data);
            this.employeeTable.subscribe('deleteEmployee', function (e, data) {
                $this.onEDCl(e, data);
            }, this);
            this.employeeTable.subscribe('updateEmployeeDialog', function (e, data) {
                $this.employeeUpdateDialog(e, data);
            }, this);
        });
    },

    employeeAddDialog: function (dep_id,e) {
     /*   $.('.addEmployeeDialog').empty();*/
        this.addEmployeeDialog = new AddEmployeeDialog(this.div, dep_id);
        this.addEmployeeDialog.subscribe('addEmployee', function (e, data) {
            this.onEAdCl(e, data);
        }, this);
    },

    employeeUpdateDialog:function(e,data) {
        $this = this;

        this.updateEmployeeDialog = new UpdateEmployeeDialog(this.div, data);
        this.updateEmployeeDialog.subscribe('updateEmployee', function (e, data) {
            this.onEUpCl(e, data);
        }, this);
    },

    onGoToDepartmentListPageSubmit: function () {
        this.divGoToDepartmentList = $('<div/>')
            .html('<button>back to department list</button>')
            .appendTo($(this.div));
        this.divGoToDepartmentList.on('click', $.proxy(this.onDCl, this));
    },

    onDCl: function () {
        this.hide();
        var controller = this.getController('DepListPage');
        controller.show();
    },

    onEDCl: function (e, data) {
        $this = this;
        $.get('/rest/deleteEmployee', data, function () {
            $this.employeeList(data.dep_id);
        });
    },

    onEAdCl: function (e, data) {
        $this = this;
        var json = { "email": data.email, "salary": data.salary, "birthday": data.birthday, "dep_id": data.dep_id};
        $.post('/rest/addEmployee', json, function (response) {
            $this.employeeList(data.dep_id);
        });

        e.preventDefault();
    },

    onEUpCl:function(e, data) {
        $this = this;
        $.post('/rest/editEmployee', data, function (response) {
            $this.employeeList(data.dep_id);
            $('.updateEmployeeDialog').empty();
                    /*$('.updateEmployeeForm').hide();*/
        });

        e.preventDefault();
    }
});