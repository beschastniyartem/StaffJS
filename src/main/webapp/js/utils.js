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
    },

    exceptionMessage:function(message,container){

    }
});
DepListPage = PageController.extend({
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
EmplListPage = PageController.extend({
    initialized: false,
    exceptionEmailInitialized: false,

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
        $('.addEmployeeDialog').empty();
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
        if(data.birthday){
        $('.exceptionBirthday').remove();
        $.post('/rest/addEmployee', data, function (response) {
            if(!response.email && !response.salary){
                $('.employeeTable').remove();
                $('.exceptionEmail').remove();
                $('.exceptionSalary').remove();
                $this.employeeList(data.dep_id);
            }else{
                $this.exceptionMessage(response,$this.addEmployeeDialog);
            }
        });
        }else{
            data.birthday = 'Birthday not valid';
            this.exceptionWithOutRequest(data,this.addEmployeeDialog)
        }

        e.preventDefault();
    },

    onEUpCl:function(e, data) {
        $this = this;
        if(data.birthday){
        $('.exceptionBirthday').remove();
        $.post('/rest/editEmployee', data, function (response) {
            if(!response.email && !response.salary){
                $('.employeeTable').remove();
                $('.updateEmployeeDialog').remove();
                $this.employeeList(data.dep_id);
            }
            else{
                $this.exceptionMessage(response,$this.updateEmployeeDialog);
            }
        });
        }else{
            data.birthday = 'Birthday not valid';
            this.exceptionWithOutRequest(data,this.updateEmployeeDialog)
        }
        /*e.preventDefault();*/
    },

    exceptionMessage: function (message, exceptionContainer) {
        $this = this;
        $('.exceptionEmail').remove();
        if(message.email && !$this.exceptionEmailInitialized){
                this.exceptionEmailForm = $('<label/>')
                .addClass('exceptionEmail')
                .html(message.email)
                .insertAfter($(exceptionContainer.$inputEmail));
        }
        $('.exceptionSalary').remove();
        if(message.salary ){
            this.exceptionSalaryForm = $('<label/>')
                .addClass('exceptionSalary')
                .html(message.salary)
                .insertAfter($(exceptionContainer.$inputSalary));
        }
    },

    exceptionWithOutRequest:function(message, exceptionContainer){
        $('.exceptionBirthday').remove();
        if(message.birthday) {
            this.exceptionBirthdayForm = $('<label/>')
                .addClass('exceptionBirthday')
                .html(message.birthday)
                .insertAfter($(exceptionContainer.$inputBirthday));
        }

    }
});