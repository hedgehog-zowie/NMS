var MonitorInfoTable = function () {

    var initTable = function () {

        var monitorInfoTable = $('#table_monitorInfo');

        // begin table_monitorInfo
        monitorInfoTable.dataTable({

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
                [2, "desc"]
            ],
        });

        var tableWrapper = jQuery('#table_monitorInfo_wrapper');

        monitorInfoTable.find('.group-checkable').change(function () {
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

        monitorInfoTable.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");
        });

        tableWrapper.find('.dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

        $('#table_monitorInfo_handle').click(function (e) {
            e.preventDefault();
            var ids = "";
            $(".checkboxes", monitorInfoTable).each(function () {
                if ($(this).parents('tr').hasClass("active"))
                    ids += $(this).val() + ",";
                return ids;
            });
            if (ids == undefined || ids == null || ids == "")
                swal("请勾选需要处理的数据！", "", "info");
            else
                handle("/monitorInfo/handle", {ids: ids}, "/monitorInfo?objectId=" + $("#monitorObjectId").val());
        });

        $('#table_monitorInfo_export').click(function (e) {
            e.preventDefault();
            location.href = "/monitorInfo/exportExcel";
        });

        monitorInfoTable.on('click', '.handle', function (e) {
            e.preventDefault();
            handle("/monitorInfo/handle", {ids: $(this).attr("id").substring(7)}, "/monitorInfo?objectId=" + $("#monitorObjectId").val());
        });

    }

    // 处理监控信息
    var handle = function (url, data, callBackUrl) {
        swal({
                title: "确定要处理这些监控信息？",
                type: "warning",
                showCancelButton: true,
                cancelButtonText: "取消",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认，处理！",
                showLoaderOnConfirm: true,
                closeOnConfirm: false
            },
            function () {
                $.post(url, data, function (result) {
                    if (result.code == "0") {
                        swal({
                            title: "已处理！",
                            type: "success",
                        }, function () {
                            location.href = callBackUrl;
                        });
                    }
                    else
                        swal("处理失败！", "失败原因：" + result.msg, "error");
                }, "json");
            });
    }

    return {

        //main function to initiate the monitorInfo
        init: function () {
            //activeMenu('/config/monitorInfo');
            initTable();
        }

    };

}();
