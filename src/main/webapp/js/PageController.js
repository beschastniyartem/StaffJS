define(['./eventSupport','require'], function(EventSupport,require) {
    return EventSupport.extend({
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
                require(['./EmplListPage'],function(EmplListPage){
                    controller = window['controllers'][name] = new EmplListPage();
                });

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
});