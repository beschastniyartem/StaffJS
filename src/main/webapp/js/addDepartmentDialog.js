AddDepartmentDialog = EventSupport.extend({
    init:function(container){
        this.$input = $('<input/>')
            .attr({
                type: "text",
                path: "name"
            });
        this.$button = $('<input/>')
            .attr({
                type: "submit",
                value: "Add department"
            }).click($.proxy(this.onSubmit,this));

        this.div = $('<div/>')
            .append(this.$input)
            .append(this.$button)
            .appendTo(container.div);
    },
    onSubmit:function(){
        this.fire('addDepartment',{text:this.$input.val()});
    }
});
