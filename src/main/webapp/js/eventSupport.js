var EventSupport = Class.extend({
    subscribe: function (event, fx, scope) {
        $(this.getObject()).on(event, $.proxy(fx, scope));
    },

    fire: function (event, data) {
        $(this.getObject()).trigger(event, data);
    },

    getObject: function () {
        return this;
    }
});