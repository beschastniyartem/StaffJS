var AddEmployee = Class.extend({
    init: function (div, dep_id) {
        $this = this;
        if (!this.addForm) {
            this.addForm = $('<form/>')
                .addClass('addEmployee')
                .attr("id", "addEmployeeForm")
                .appendTo($(div));
            $('.addEmployee').empty();
            if (dep_id) {
                var inputEmail = $('<input/>')
                    .attr({
                        type: "text",
                        path: "email"
                    })
                    .appendTo(this.addForm);
                var inputSalary = $('<input/>')
                    .attr({
                        type: "text",
                        path: "salary"
                    })
                    .appendTo(this.addForm);
                var inputBirthday = $('<input/>')
                    .attr({
                        type: "date",
                        path: "birthday"
                    })
                    .appendTo(this.addForm);
                var inputSubmit = $('<input/>')
                    .attr({
                        type: "submit",
                        value: "Add employee"
                    })
                    .appendTo(this.addForm);
                this.addForm.submit(function (e) {
                    var email = inputEmail.val();
                    var salary = inputSalary.val();
                    var birthday = inputBirthday.val();
                    var json = { "email": email, "salary": salary, "birthday": birthday, "dep_id": dep_id};
                    $.post('/rest/addEmployee', json, function (response) {
                        var emplList = new EmplListPage();
                        emplList.employeeList(dep_id);
                    });
                    e.preventDefault();
                })
            }
        }
    }
});