define(['./eventSupport'], function(EventSupport) {
    return EventSupport.extend({
        init:function(container,data){
            this.$label = $('<label/>')
                .html('Name');
            this.$input = $('<input/>')
                .attr({
                    type: "text",
                    path: "name",
                    id:data.dep_id,
                    value: data.name
                });
            this.$button = $('<input/>')
                .attr({
                    type: "submit",
                    value: "Update department"
                }).click($.proxy(this.onSubmit,this));

            this.div = $('<div/>')
                .addClass('updateDepartmentDialog')
                .append(this.$label)
                .append(this.$input)
                .append(this.$button)
                .appendTo(container);
        },
        onSubmit:function(){
            this.fire('updateDepartment',{name:this.$input.val(),dep_id:this.$input.attr('id')});
        }
    });
});
