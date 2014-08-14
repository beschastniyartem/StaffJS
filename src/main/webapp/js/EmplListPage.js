define(['./PageController','./ajaxDataSource','./employeeTable','./addEmployeeDialog','./updateEmployeeDialog'],
    function(PageController,AjaxDataSource,EmployeeTable,AddEmployeeDialog,UpdateEmployeeDialog) {
        return PageController.extend({
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
});