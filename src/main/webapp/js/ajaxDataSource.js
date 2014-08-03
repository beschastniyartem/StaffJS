var AjaxDataSource = DataSource.extend({
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