var AlarmPersonTable = function () {

    var initTable = function () {

        var alarmPersonTable = $('#table_alarmPerson');

        // begin table_alarmPerson
        alarmPersonTable.dataTable({

            // Internationalisation. For more info refer to http://datatables.net/manual/i18n
            "language": {
                "aria": {
                    "sortAscending": ": 升序排列",
                    "sortDescending": ": 降序排列"
                },
                "emptyTable": "无数据",
                "info": "显示 _START_ 到 _END_ 条记录，共 _TOTAL_ 条记录",
                "infoEmpty": "未找到相应记录",
                "infoFiltered": "(从 _MAX_ 条记录中过滤)",
                "lengthMenu": "每页显示： _MENU_ ",
                "search": "查找：",
                "zeroRecords": "无匹配的记录",
                "paginate": {
                    "previous": "上一页",
                    "next": "下一页",
                    "last": "末页",
                    "first": "首页"
                }
            },

            "bProcessing": true,
            "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

            "lengthMenu": [
                [5, 15, 30, -1],
                [5, 15, 30, "全部"] // change per page values here
            ],
            // set the initial value
            "pageLength": 15,
            "pagingType": "bootstrap_full_number",
            "columnDefs": [{  // set default column settings
                'orderable': false,
                'targets': [0]
            }, {
                'width': '5%',
                'targets': [0]
            }, {
                'width': '20%',
                'targets': [1, 2, 3, 4]
            }, {
                'width': '15%',
                'targets': [5]
            }, {
                "searchable": false,
                "targets": [0]
            }],
            "order": [
                [4, "desc"]
            ],
        });

        var tableWrapper = jQuery('#table_alarmPerson_wrapper');

        alarmPersonTable.find('.group-checkable').change(function () {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            jQuery(set).each(function () {
                if (checked) {
                    //$(this).attr("checked", true);
                    $(this).prop("checked", true);
                    $(this).parents('tr').addClass("active");
                } else {
                    //$(this).attr("checked", false);
                    $(this).removeAttr("checked");
                    $(this).parents('tr').removeClass("active");
                }
            });
            jQuery.uniform.update(set);
        });

        alarmPersonTable.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");
        });

        tableWrapper.find('.dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

        $('#table_alarmPerson_new').click(function (e) {
            e.preventDefault();
            location.href = "/config/alarmPerson/add";
        });

        $('#table_alarmPerson_delete').click(function (e) {
            e.preventDefault();
            var ids = "";
            $(".checkboxes", alarmPersonTable).each(function () {
                if ($(this).parents('tr').hasClass("active"))
                    ids += $(this).val() + ",";
                return ids;
            });
            if (ids == undefined || ids == null || ids == "")
                swal("请勾选需要删除的数据！", "", "info");
            else
                deleteData("/config/alarmPerson/delete", {ids: ids}, "/config/alarmPerson");
        });

        $('#table_alarmPerson_export').click(function (e) {
            e.preventDefault();
            location.href = "/config/alarmPerson/exportExcel";
        });

        alarmPersonTable.on('click', '.edit', function (e) {
            e.preventDefault();
            location.href = "/config/alarmPerson/edit?id=" + $(this).attr("id").substring(5);
        });

        alarmPersonTable.on('click', '.delete', function (e) {
            e.preventDefault();
            deleteData("/config/alarmPerson/delete", {ids: $(this).attr("id").substring(7)}, "/config/alarmPerson");
        });

        alarmPersonTable.on('click', '.enable', function (e) {
            e.preventDefault();
            enableData("/config/alarmPerson/enable", {ids: $(this).attr("id").substring(7)}, "/config/alarmPerson");
        });

        alarmPersonTable.on('click', '.disable', function (e) {
            e.preventDefault();
            disableData("/config/alarmPerson/disable", {ids: $(this).attr("id").substring(8)}, "/config/alarmPerson");
        });

    }

    return {

        //main function to initiate the alarmPerson
        init: function () {
            activeMenu('/config/alarmPerson');
            initTable();
        }

    };

}();

var AlarmPersonFormValidation = function () {

    // validation
    var handleValidation = function () {
        // for more info visit the official plugin documentation:
        // http://docs.jquery.?/Plugins/Validation

        var alarmPersonForm = $('#form_alarmPerson');
        var alarmPersonError = $('.alert-danger', alarmPersonForm);
        var alarmPersonSuccess = $('.alert-success', alarmPersonForm);

        alarmPersonForm.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "", // validate all fields including form hidden input
            rules: {
                name: {
                    required: true,
                },
                mobile: {
                    required: true,
                    isMobile: true,
                },
                email: {
                    required: true,
                    email: true,
                }
            },

            messages: { // custom messages for radio buttons and checkboxes
                name: {
                    required: "必须字段",
                },
                mobile: {
                    required: "必须字段",
                },
                email: {
                    required: "必须字段",
                    email: "邮件地址不合法",
                }
            },

            errorPlacement: function (error, element) { // render error placement for each input type
                if (element.parent(".input-group").size() > 0) {
                    error.insertAfter(element.parent(".input-group"));
                } else if (element.attr("data-error-container")) {
                    error.appendTo(element.attr("data-error-container"));
                } else if (element.parents('.radio-list').size() > 0) {
                    error.appendTo(element.parents('.radio-list').attr("data-error-container"));
                } else if (element.parents('.radio-inline').size() > 0) {
                    error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
                } else if (element.parents('.checkbox-list').size() > 0) {
                    error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
                } else if (element.parents('.checkbox-inline').size() > 0) {
                    error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
                } else {
                    error.insertAfter(element); // for other inputs, just perform default behavior
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                alarmPersonSuccess.hide();
                alarmPersonError.show();
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label
                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
            },

            submitHandler: function (form) {
                alarmPersonSuccess.show();
                alarmPersonError.hide();
                saveData("/config/alarmPerson/save", alarmPersonForm.serialize(), "/config/alarmPerson");
            }
        });

        //initialize datepicker
        $('.date-picker').datepicker({
            autoclose: true,
        });
        $('.date-picker .form-control').change(function () {
            regenerateUrl();
            alarmPersonForm.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
        });

        $('#cancel_form_alarmPerson').click(function (e) {
            location.href = "/config/alarmPerson";
        });

    }

    return {
        //main function to initiate the alarmPerson
        init: function () {
            activeMenu('/config/alarmPerson');
            handleValidation();
        }

    };

}();