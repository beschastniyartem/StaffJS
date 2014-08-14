define(['./eventSupport'], function(EventSupport) {
    return EventSupport.extend({
        init:function(container,data){
            this.$dep_id = data.dep_id;
            this.$inputEmail = $('<input/>')
                .attr({
                    type: "text",
                    path: "email",
                    value: data.email
                });
            this.$inputSalary = $('<input/>')
                .attr({
                    type: "text",
                    path: "salary",
                    value:data.salary
                });
            this.$inputBirthday = $('<input/>')
                .attr({
                    type: "date",
                    path: "birthday",
                    value:data.birthday
                });
            this.$button = $('<input/>')
                .attr({
                    type: "submit",
                    value: "Edit employee"
                })
                .click($.proxy(this.onSubmit,this));

            this.div = $('<div/>')
                .attr({
                    id:data.empl_id
                })
                .addClass('updateEmployeeDialog')
                .append(this.$inputEmail)
                .append(this.$inputSalary)
                .append(this.$inputBirthday)
                .append(this.$button)
                .appendTo(container);
        },

        onSubmit:function(){
            this.fire('updateEmployee',{empl_id:this.div.attr('id'),
                email:this.$inputEmail.val(),
                salary:this.$inputSalary.val(),
                birthday:this.$inputBirthday.val(),
                dep_id:this.$dep_id});
        }
    });
});
