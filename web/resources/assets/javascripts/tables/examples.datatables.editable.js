/*
 Name: 			Tables / Editable - Examples
 Written by: 	Okler Themes - (http://www.okler.net)
 Theme Version: 	1.3.0
 */

/* global myJSONObject */

(function ($) {

    'use strict';
    var EditableTable = {
        options: {
            addButton: '#addToTable',
            table: '#datatable-editable',
            dialog: {
                wrapper: '#dialog',
                cancelButton: '#dialogCancel',
                confirmButton: '#dialogConfirm',
            }
        },
        initialize: function () {
            this
                    .setVars()
                    .build()
                    .events();
        },
        setVars: function () {
            this.$table = $(this.options.table);
            this.$addButton = $(this.options.addButton);
            // dialog
            this.dialog = {};
            this.dialog.$wrapper = $(this.options.dialog.wrapper);
            this.dialog.$cancel = $(this.options.dialog.cancelButton);
            this.dialog.$confirm = $(this.options.dialog.confirmButton);
            return this;
        },
        build: function () {
            this.datatable = this.$table.DataTable({
                aoColumns: [
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    {"bSortable": false}
                ]
            });
            window.dt = this.datatable;
            return this;
        },
        events: function () {
            var _self = this;
            this.$table
                    .on('click', 'a.save-row', function (e) {
                        e.preventDefault();
                        _self.rowSave($(this).closest('tr'));


                    })
                    .on('click', 'a.cancel-row', function (e) {
                        e.preventDefault();
                        _self.rowCancel($(this).closest('tr'));
                    })
                    .on('click', 'a.edit-row', function (e) {
                        e.preventDefault();
                        _self.rowEdit($(this).closest('tr'));



                    })
                    .on('click', 'a.remove-row', function (e) {
                        e.preventDefault();
                        var $row = $(this).closest('tr');
                        $.magnificPopup.open({
                            items: {
                                src: '#dialog',
                                type: 'inline'
                            },
                            preloader: false,
                            modal: true,
                            callbacks: {
                                change: function () {
                                    _self.dialog.$confirm.on('click', function (e) {
                                        e.preventDefault();
                                        _self.rowRemove($row);
                                        $.magnificPopup.close();
                                    });
                                },
                                close: function () {
                                    _self.dialog.$confirm.off('click');
                                }
                            }
                        });
                    });
            this.$addButton.on('click', function (e) {
                e.preventDefault();
                _self.rowAdd();
            });
            this.dialog.$cancel.on('click', function (e) {
                e.preventDefault();
                $.magnificPopup.close();
            });
            return this;
        },
        // ==========================================================================================
        // ROW FUNCTIONS
        // ==========================================================================================
        rowAdd: function () {
            this.$addButton.attr({'disabled': 'disabled'});
            var actions,
                    data,
                    $row;
            actions = [
                '<a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>',
                '<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>',
                '<a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>',
                '<a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>'
            ].join(' ');
            data = this.datatable.row.add(['', '', '', '', '', '', '', '', actions]);
            $row = this.datatable.row(data[0]).nodes().to$();
            $row
                    .addClass('adding')
                    .find('td:last')
                    .addClass('actions');
            this.rowEdit($row);
            this.datatable.order([0, 'asc']).draw(); // always show fields
        },
        rowCancel: function ($row) {
            var _self = this,
                    $actions,
                    i,
                    data;
            if ($row.hasClass('adding')) {
                this.rowRemove($row);
            } else {

                data = this.datatable.row($row.get(0)).data();
                this.datatable.row($row.get(0)).data(data);
                $actions = $row.find('td.actions');
                if ($actions.get(0)) {
                    this.rowSetActionsDefault($row);
                }

                this.datatable.draw();
            }
        },
        rowEdit: function ($row) {
            var _self = this,
                    data;
            data = this.datatable.row($row.get(0)).data();
            $row.find('td:nth-child(1)').html('<select data-plugin-selectTwo class="form-control populate"><c:forEach var="matr" items="#{personnelController.all}" ><option value="${matr.matricule}">${matr.matricule}</option></c:forEach></select>');
            $row.find('td:nth-child(2)').html('<input type="text" class="form-control input-block" value="' + data[1] + '"/>');
            $row.find('td:nth-child(3)').html('<input type="text" class="form-control input-block" value="' + data[2] + '"/>');
            $row.find('td:nth-child(4)').html('<input type="text" id="date" data-plugin-datepicker class="form-control" value="' + data[3] + '"/>');
            $row.find('td:nth-child(5)').html('<input type="text" class="form-control input-block" value="' + data[4] + '"/>');
            $row.find('td:nth-child(6)').html('<input type="text" class="form-control input-block" value="' + data[5] + '"/>');
            $row.find('td:nth-child(7)').html('<select data-plugin-selectTwo class="form-control populate"><option value="Voiture personnelle">Voiture personnelle</option><option value="Voiture de service.">Voiture de service</option></select>');
            $row.find('td:nth-child(8)').html('<input type="text" class="form-control input-block" value="' + data[7] + '"/>');
            _self.rowSetActionsEditing($row);



//            $row.children('td').each(function (i) {
//                var $this = $(this);
//
//                if ($this.hasClass('actions')) {
//                    _self.rowSetActionsEditing($row);
//                } else {
//                    $this.html('<input type="text" class="form-control input-block" value="' + data[i] + '"/>');
//                }
//            });
        },
        rowSave: function ($row) {
            var attr = document.getElementById("addToTable").getAttribute("disabled");

            var id = $row.find('a#edit').attr('ordre');



            var _self = this,
                    $actions,
                    values = [];
            if ($row.hasClass('adding')) {
                this.$addButton.removeAttr('disabled');
                $row.removeClass('adding');
            }

            values = $row.find('td').map(function () {
                var $this = $(this);
                if ($this.hasClass('actions')) {
                    _self.rowSetActionsDefault($row);
                    return _self.datatable.cell(this).data();
                } else {
                    if ($this.find('select').attr('class')==='form-control populate') {
                        return $.trim($this.find('select').val());
                    }
                    else {
                        return $.trim($this.find('input').val());
                    }
                }
            });

            this.datatable.row($row.get(0)).data(values);

            $actions = $row.find('td.actions');

            if ($actions.get(0)) {
                this.rowSetActionsDefault($row);
            }

            this.datatable.draw();

            var json = JSON.stringify(values);



            $.ajax
                    (
                            {
                                url: '/AppTI1.0/GetOrdre',
                                data: {json: json, attr: attr, id: id},
                                type: 'post',
                                cache: false,
                                success: function (data) {
                                    alert(data);
                                },
                                error: function () {
                                    alert('error');
                                }
                            }
                    );





        },
        rowRemove: function ($row) {
            if ($row.hasClass('adding')) {
                this.$addButton.removeAttr('disabled');
            }

            var action = "remove";

            var id = $row.find('a#edit').attr('ordre');

            this.datatable.row($row.get(0)).remove().draw();


            $.ajax
                    (
                            {
                                url: '/AppTI1.0/GetOrdre',
                                data: {id: id, action: action},
                                type: 'post',
                                cache: false,
                                success: function (data) {
                                    alert(data);
                                },
                                error: function () {
                                    alert('error');
                                }
                            }
                    );





        },
        rowSetActionsEditing: function ($row) {
            $row.find('.on-editing').removeClass('hidden');
            $row.find('.on-default').addClass('hidden');
        },
        rowSetActionsDefault: function ($row) {
            $row.find('.on-editing').addClass('hidden');
            $row.find('.on-default').removeClass('hidden');
        }

    };
    $(function () {
        EditableTable.initialize();
    });
}).apply(this, [jQuery]);