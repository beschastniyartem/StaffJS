define(['./eventSupport'], function(EventSupport) {
    return EventSupport.extend({
        refresh: function (url, success) {
            return $.ajax({
                url: url,
                success: success,
                error: function () {
                    console.log("MyError")
                }
            });
        }
    });
});