import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
    selector: 'result-analysis',
    templateUrl: 'result-analysis.component.html',
    styles: [`
        
    `]
})
export class ResultAnalysisPage implements OnInit {

    

    constructor(
    ) {

    }

    ngOnInit(): void {
        
    }

    ngAfterViewInit() {
        this.initDatatable1();
        this.initDatatable2();
        this.initDatatable3();
        this.initDatatable4();
        this.initDatatable5();
    }		

    initDatatable1() {
        
        let table1mock = 
        [{"1": "C 16M DOM-1.5T CVT ZA7", "2": "100.00", "3": "90.00", "4": "10.00"}
        , {"1": "C 16M DOM-RS CVT ZB7", "2": "80.00", "3": "80.00", "4": "0.00"}
        , {"1": "C 16M DOM-15EL  CVT ZB7", "2": "120.00", "3": "120.00", "4": "0.00"}
        , {"1": "C 16M DOM-1.8S CVT ZA5", "2": "140.00", "3": "150.00", "4": "10.00"}]

        $('#table1').DataTable({
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "pageLength": 10,
            "processing": true,
            "serverSide": false,
            "paging": false,
            "info": false,
            "pagingType": "full_numbers",
            "data": table1mock,
            "columns": [
                { "data": "1" },
                { "data": "2",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    }
                },
                { "data": "3",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    } 
                },
                { "data": "4",
                    "render": function(data, type, row)  {
                        if (parseFloat(data) == 0) {
                            return `<span class="ui green horizontal label" style="float: right;">${data}</span>`;
                        } else {
                            return `<span class="ui red horizontal label" style="float: right;">${data}</span>`;
                        }
                        
                    } 
                },
            ],
            "footerCallback": function ( row, data, start, end, display ) {
                let api = this.api();
               
                api.columns('.sum', { page: 'current'}).every( function () {
                  var sum = this
                    .data()
                    .reduce( function (a, b) {
                        return parseFloat(a) + parseFloat(b);
                    }, 0 );
                    
                    sum += '.00';

                    this.footer().innerHTML = `<span style="float: right;">${sum}</span>`;
                });
            }
        });
    }

    initDatatable2() {
        let table2mock = 
        [{"1": "C 16M DOM-1.5T CVT ZA7", "2": "1500.00", "3": "1500.00", "4": "0.00"}
        , {"1": "C 16M DOM-RS CVT ZB7", "2": "1400.00", "3": "1400.00", "4": "0.00"}
        , {"1": "C 16M DOM-15EL  CVT ZB7", "2": "2300.00", "3": "2300.00", "4": "0.00"}
        , {"1": "C 16M DOM-1.8S CVT ZA5", "2": "1900.00", "3": "1900.00", "4": "0.00"}]

        $('#table2').DataTable({
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "pageLength": 10,
            "processing": true,
            "serverSide": false,
            "paging": false,
            "info": false,
            "pagingType": "full_numbers",
            "data": table2mock,
            "columns": [
                { "data": "1" },
                { "data": "2",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    }
                },
                { "data": "3",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    } 
                },
                { "data": "4",
                    "render": function(data, type, row)  {
                        if (parseFloat(data) == 0) {
                            return `<span class="ui green horizontal label" style="float: right;">${data}</span>`;
                        } else {
                            return `<span class="ui red horizontal label" style="float: right;">${data}</span>`;
                        }
                        
                    } 
                },
            ],
            "footerCallback": function ( row, data, start, end, display ) {
                let api = this.api();
               
                api.columns('.sum', { page: 'current'}).every( function () {
                  var sum = this
                    .data()
                    .reduce( function (a, b) {
                        return parseFloat(a) + parseFloat(b);
                    }, 0 );
                    
                    sum += '.00';
                    
                    this.footer().innerHTML = `<span style="float: right;">${sum}</span>`;
                });
            }
        });
    }

    initDatatable3() {

        let table3mock = 
        [{"1": "C 16M DOM-1.5T CVT ZA7", "2": "100.00", "3": "1500.00", "4": "150000.00"}
        , {"1": "C 16M DOM-RS CVT ZB7", "2": "80.00", "3": "80.00", "4": "6400.00"}
        , {"1": "C 16M DOM-15EL  CVT ZB7", "2": "120.00", "3": "100.00", "4": "12000.00"}
        , {"1": "C 16M DOM-1.8S CVT ZA5", "2": "140.00", "3": "1800.00", "4": "252000.00"}]

        $('#table3').DataTable({
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "pageLength": 10,
            "processing": true,
            "serverSide": false,
            "paging": false,
            "info": false,
            "pagingType": "full_numbers",
            "data": table3mock,
            "columns": [
                { "data": "1" },
                { "data": "2",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    }
                },
                { "data": "3",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    } 
                },
                { "data": "4",
                    "render": function(data, type, row)  {
                        return `<span style="float: right;">${data}</span>`;
                    } 
                },
            ],
            "footerCallback": function ( row, data, start, end, display ) {
                let api = this.api();
               
                api.columns('.sum', { page: 'current'}).every( function () {
                  var sum = this
                    .data()
                    .reduce( function (a, b) {
                        return parseFloat(a) + parseFloat(b);
                    }, 0 );
                    
                    sum += '.00';

                    this.footer().innerHTML = `<span style="float: right;">${sum}</span>`;
                });
            }
        });
    }

    initDatatable4() {
        
        let table4mock = 
        [{"1": "C 16M DOM-1.5T CVT ZA7", "2": "8%", "3": "8%", "4": "0.00"}
        , {"1": "C 16M DOM-RS CVT ZB7", "2": "8%", "3": "8%", "4": "0.00"}
        , {"1": "C 16M DOM-15EL  CVT ZB7", "2": "8%", "3": "8%", "4": "0.00"}
        , {"1": "C 16M DOM-1.8S CVT ZA5", "2": "8%", "3": "8%", "4": "0.00"}]

        $('#table4').DataTable({
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "pageLength": 10,
            "processing": true,
            "serverSide": false,
            "paging": false,
            "info": false,
            "pagingType": "full_numbers",
            "data": table4mock,
            "columns": [
                { "data": "1" },
                { "data": "2",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    }
                },
                { "data": "3",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    } 
                },
                { "data": "4",
                    "render": function(data, type, row)  {
                        if (parseFloat(data) == 0) {
                            return `<span class="ui green horizontal label" style="float: right;">${data}</span>`;
                        } else {
                            return `<span class="ui red horizontal label" style="float: right;">${data}</span>`;
                        }
                        
                    } 
                },
            ],
            "footerCallback": function ( row, data, start, end, display ) {
                let api = this.api();
               
                api.columns('.sum', { page: 'current'}).every( function () {
                  var sum = this
                    .data()
                    .reduce( function (a, b) {
                        return parseFloat(a) + parseFloat(b);
                    }, 0 );
                    
                    sum += '.00';

                    this.footer().innerHTML = `<span style="float: right;">${sum}</span>`;
                });
            }
        });
    }

    initDatatable5() {
        
        let table5mock = 
        [{"1": "C 16M DOM-1.5T CVT ZA7", "2": "12000.00", "3": "9000.00", "4": "3000.00"}
        , {"1": "C 16M DOM-RS CVT ZB7", "2": "23000.00", "3": "19000.00", "4": "4000.00"}
        , {"1": "C 16M DOM-15EL  CVT ZB7", "2": "17000.00", "3": "13000.00", "4": "4000.00"}
        , {"1": "C 16M DOM-1.8S CVT ZA5", "2": "20160.00", "3": "20160.00", "4": "0.00"}]

        $('#table5').DataTable({
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "pageLength": 10,
            "processing": true,
            "serverSide": false,
            "paging": false,
            "info": false,
            "pagingType": "full_numbers",
            "data": table5mock,
            "columns": [
                { "data": "1" },
                { "data": "2",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    }
                },
                { "data": "3",
                    "render": function(data, type, row) {
                        return `<span style="float: right;">${data}</span>`;
                    } 
                },
                { "data": "4",
                    "render": function(data, type, row)  {
                        if (parseFloat(data) == 0) {
                            return `<span class="ui green horizontal label" style="float: right;">${data}</span>`;
                        } else {
                            return `<span class="ui red horizontal label" style="float: right;">${data}</span>`;
                        }
                        
                    } 
                },
            ],
            "footerCallback": function ( row, data, start, end, display ) {
                let api = this.api();
               
                api.columns('.sum', { page: 'current'}).every( function () {
                  var sum = this
                    .data()
                    .reduce( function (a, b) {
                        return parseFloat(a) + parseFloat(b);
                    }, 0 );
                    
                    sum += '.00';

                    this.footer().innerHTML = `<span style="float: right;">${sum}</span>`;
                });
            }
        });
    }
}