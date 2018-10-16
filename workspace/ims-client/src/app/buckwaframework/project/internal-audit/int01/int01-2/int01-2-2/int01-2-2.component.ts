import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";

import { DecimalFormat } from "helpers/index";
declare var $: any;
@Component({
    selector: "int01-2-2",
    templateUrl: "./int01-2-2.component.html",
    styleUrls: ["./int01-2-2.component.css"]
})
export class Int0122Component implements OnInit {
    private showData: boolean = false;
    offCode: any;
    startDate: any;
    endDate: any;
    dataTableList: any[];
    lic: Lic;
    datatable: any;
    breadcrumb: { label: string; route: string; }[];
    constructor(private router: Router,
        private ajax: AjaxService,
        private route: ActivatedRoute,
        private messageBarService: MessageBarService) {
        this.dataTableList = [];
        this.offCode = this.route.snapshot.queryParams["ofCode"];
        this.startDate = this.route.snapshot.queryParams["startDate"];
        this.endDate = this.route.snapshot.queryParams["endDate"];
        this.breadcrumb = [
            { label: "ตรวจสอบภายใน", route: "#" },
            { label: "ตรวจสอบรายได้", route: "#" },
            { label: "ตรวจสอบใบอนุญาต", route: "#" },
            { label: "ค้นหาและตรวจสอบใบอนุญาต", route: "#" }
        ];

    }

    ngOnInit() {
        this.lic = new Lic();
        $(".ui.dropdown").dropdown();
        $(".ui.dropdown.ai").css("width", "100%");
        $("#idint").hide();
        $("#id").hide();
        console.log('ofCode', this.offCode);
        this.ajax.post("ia/int012/searchLicense", {
            offCode: this.offCode, startDate: this.startDate, endDate: this.endDate
        }, res => {
            this.dataTableList = res.json();
            console.log(this.dataTableList);
            this.initDatatable();
        });
    }


    popupEditData() {
        $("#modalEditData").modal("show");
        $("#modalInt062").modal("show");
        $("#idint").show();
        $("#id").show();
        $("#selectTrading").show();
    }

    closePopupEdit() {
        $("#selectTrading").show();
        $("#modalEditData").modal("hide");
        $("#modalInt062").modal("hide");
    }

    uploadData() {
        this.showData = true;
    }

    clearData() {
        this.showData = false;
    }

    initDatatable(): void {
        if (this.datatable != null) {
            this.datatable.destroy();
        }

        this.datatable = $("#dataTable").DataTable({
            lengthChange: false,
            searching: false,
            ordering: false,
            pageLength: 10,
            processing: true,
            serverSide: false,
            paging: false,
            data: this.dataTableList,
            columns: [

                {
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    },
                    className: "center"
                },
                {
                    render: function (data, type, row, meta) {
                        //console.log(row);
                        let year = parseInt(row.licDate.substring(0, 4)) + 543;
                        let month = row.licDate.substring(4, 6);
                        let day = row.licDate.substring(6);
                        return (row.licDate != null && row.licDate != '') ? `${day}/${month}/${year}` : "-";

                    },
                    className: "center"
                },
                //{ data: "licDate" },
                {
                    render: function (data, type, row, meta) {
                        //console.log(row);
                        let initDate = row.sendDate;
                        let year = parseInt(initDate.substring(0, 4)) + 543;
                        let month = initDate.substring(4, 6);
                        let day = initDate.substring(6);
                        return (initDate != null && initDate != '') ? `${day}/${month}/${year}` : "-";

                    },
                    className: "center"
                },
                //{ data: "sendDate" },
                { data: "licName" },
                { data: "printCode" },
                {
                    render: function (data, type, row, meta) {

                        return '<a class="licNo" >' + row.licNo + '</a >';

                    }
                },

                { data: "licPrice", render: $.fn.dataTable.render.number(',', '.', 2, '') },
                {
                    render: function (data, type, row, meta) {
                        const df = new DecimalFormat("###,###.00");
                        return row.licAmount == null || row.licAmount == undefined ? 0.00 : df.format(row.licAmount);

                    }
                },
                //{ data: "licAmount", render: $.fn.dataTable.render.number(',', '.', 2, '') },
                { data: "licFee", render: $.fn.dataTable.render.number(',', '.', 2, '') },
                { data: "licInterior", render: $.fn.dataTable.render.number(',', '.', 2, '') },
                {
                    render: function (data, type, row, meta) {
                        const df = new DecimalFormat("###,###.00");

                        return df.format(parseFloat(row.licPrice) + parseFloat(row.licFee) + parseFloat(row.licInterior));

                    }
                },
                {
                    render: function (data, type, row, meta) {
                        //console.log(row);
                        let initDate = row.startDate;
                        let year = parseInt(initDate.substring(0, 4)) + 543;
                        let month = initDate.substring(4, 6);
                        let day = initDate.substring(6);
                        return (initDate != null && initDate != '') ? `${day}/${month}/${year}` : "-";

                    }
                },
                {
                    render: function (data, type, row, meta) {
                        //console.log(row);
                        let initDate = row.expDate;
                        let year = parseInt(initDate.substring(0, 4)) + 543;
                        let month = initDate.substring(4, 6);
                        let day = initDate.substring(6);
                        return (initDate != null && initDate != '') ? `${day}/${month}/${year}` : "-";
                    }
                }

            ],
            columnDefs: [
                { targets: [0, 1, 2, 11, 12], className: "center aligned" },
                { targets: [6, 7, 8, 9], className: "right aligned" }
            ], rowCallback: (row, data, index) => {

                $("td > .licNo", row).bind("click", () => {
                    console.log('row', row);
                    console.log('data', data);
                    console.log('index', index);
                    this.lic = data;

                })
            }, createdRow: function (row, data, dataIndex) {
                console.log(row);
                console.log(data);
                if (data.licAmount != null && data.licAmount != data.licPrice) {
                    $(row).find('td:eq(6)').addClass('bg-c-red');
                    $(row).find('td:eq(7)').addClass('bg-c-red');
                }


            }
        });
    }

    dateFormat(date: string): string {
        let year = date.substring(0, 4);
        let month = date.substring(4, 6);
        let day = date.substring(6);
        return (date && date != null) ? `${day}/${month}/${year}` : "-";
    }

    df(what): string {
        const df = new DecimalFormat("###,###.00");
        return df.format(what);
    }

    round(num: number): string {
        return this.df(parseFloat(num.toString()).toFixed(2));
    }

    editDataInlist() {

        this.dataTableList.forEach(element => {
            if (element.licNo == this.lic.licNo) {
                element.printCode = this.lic.printCode;
                element.licAmount = this.lic.licAmount;
            }

        });
        this.lic = new Lic();
        this.initDatatable();
    }

    saveAllData() {
        this.ajax.post("ia/int012/save", { licenseList: this.dataTableList, offCode: this.offCode }, res => {
            let message = res.json();
            console.log(message);
            this.ajax.post("ia/int012/searchLicense", {
                offCode: this.offCode, startDate: this.startDate, endDate: this.endDate
            }, res => {
                this.dataTableList = res.json();
                this.ajax.post("ia/int012/searchLicense", {
                    offCode: this.offCode, startDate: this.startDate, endDate: this.endDate
                }, res => {
                    this.dataTableList = res.json();
                    console.log(this.dataTableList);
                    this.initDatatable();
                });
                this.messageBarService.successModal("บันทึกข้อมูลสำเร็จ");

            });
        });
    }



    nextPage() {
        this.router.navigate(["/int01/2/3"], {
            queryParams: {
                offCode: this.offCode,
                startDate: this.startDate,
                endDate: this.endDate
            }
        });
    }


}
export class Lic {
    printCode: any = '';
    licNo: any = '';
    licAmount: any = 0;

}
