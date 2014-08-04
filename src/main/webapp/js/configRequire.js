/**
 * Created by artemb on 04.08.14.
 */
require.config({
    paths: {
        jquery: 'lib/bower_components/jquery/dist/jquery',
        domReady: 'lib/bower_components/requirejs-domready/domReady'
    }
});


require(['require', 'jquery', 'SimpleInheritance',
        'eventSupport','dataSource','ajaxDataSource',
        'addDepartmentDialog','updateDepartmentDialog','addEmployeeDialog','updateEmployeeDialog',
        'employeeTable','table',
        'utils',
        'domReady'],
        function (require) {
        require(['domReady!'], function () {
            var department = new DepListPage();
        });
});