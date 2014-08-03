AddEmployeeDialog = EventSupport.extend({
    init:function(container,dep_id){
        this.$inputEmail = $('<input/>')
            .attr({
                type: "text",
                path: "email"
            });
        this.$inputSalary = $('<input/>')
            .attr({
                type: "text",
                path: "salary"
            });
        this.$inputBirthday = $('<input/>')
            .attr({
                type: "date",
                path: "birthday"
            });
        this.$button = $('<input/>')
            .attr({
                type: "submit",
                value: "Add employee"
            }).click($.proxy(this.onSubmit,this));

        this.div = $('<div/>')
            .attr({
                id:dep_id
            })
            .addClass('addEmployeeDialog')
            .append(this.$inputEmail)
            .append(this.$inputSalary)
            .append(this.$inputBirthday)
            .append(this.$button)
            .appendTo(container);
    },

    onSubmit:function(){
        this.fire('addEmployee',{email:this.$inputEmail.val(),
                                 salary:this.$inputSalary.val(),
                                 birthday:this.$inputBirthday.val(),
                                 dep_id:this.div.attr('id')});
    }
});