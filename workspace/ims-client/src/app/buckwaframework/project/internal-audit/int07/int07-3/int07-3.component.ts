import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
import { AjaxService } from 'services/ajax.service';

declare var $: any;
@Component({
  selector: 'int07-3',
  templateUrl: './int07-3.component.html',
  styleUrls: ['./int07-3.component.css']
})
export class Int073Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบบัญชี", route: "#" },
    { label: "ตรวจสอบงบทดลองกระทบยอด เดบิต เครดิต บัญชีแยกประเภท", route: "#" },
  ];

  loading: boolean = false;
  fileExel: File[];
  dataTrialBalanceSheet: Data[] = [];
  dataLedgerSheet: Data[] = [];
  dataSheet: Data[] = [];

  // dataTable
  datatable1: any;
  datatable2: any;
  datatable3: any;
  // processList
  processList: Number;



  constructor(
    private authService: AuthService,
    private ajax: AjaxService,

  ) { }


  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07300');
    this.processList = 1;
    $("#tb2").hide();
    $("#tb3").hide();
  }


  ngAfterViewInit() {
    this.initDatatable1();
  }



  onUpload = (event: any) => {
    this.loading = true;
    console.log("อัพโหลด Excel");
    if (this.processList == 1) {
      this.dataTrialBalanceSheet = [];
      const form = $("#upload-form")[0];
      let formBody = new FormData(form);
      let url = "ia/int073/readFileExcelTrialBalanceSheet";

      this.ajax.upload(
        url,
        formBody,
        res => {
          console.log(res.json());
          res.json().forEach(element => {
            this.dataTrialBalanceSheet.push(element);
          });
          this.initDatatable1();
          setTimeout(() => {
            this.loading = false;
          }, 1000);
        }
      )

    } else if (this.processList == 2) {
     
      this.dataLedgerSheet = [];
      const form = $("#upload-form")[0];
      let formBody = new FormData(form);
      let url = "ia/int073/readFileExcelLedgerSheet";

      this.ajax.upload(
        url,
        formBody,
        res => {
          console.log(res.json());
          res.json().forEach(element => {

            this.dataLedgerSheet.push(element);
          });
          this.initDatatable2();
          if(this.dataLedgerSheet.length == 0){
            this.processList = 2;
          }else{
            this.processList = 3;
          }
          setTimeout(() => {
            this.loading = false;
          }, 1000);
        }
      )


    }


  };

  onChangeUpload = (event: any) => {
    if (event.target.files && event.target.files.length > 0) {
      let reader = new FileReader();
      reader.onload = (e: any) => {
        const f = {
          name: event.target.files[0].name,
          type: event.target.files[0].type,
          value: e.target.result
        };
        this.fileExel = [f];
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };


  // dataTable upload
  initDatatable1(): void {

    if (this.datatable1 != null || this.datatable1 != undefined) {
      this.datatable1.destroy();
    }

    this.datatable1 = $("#dataTable1").DataTableTh({
      lengthChange: true,
      searching: false,
      loading: true,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.dataTrialBalanceSheet,
      columns: [
        { data: "accountNumber" },
        { data: "accountName" },
        { data: "summitTest", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "debitTest", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "creditTest", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "liftUpTest", render: $.fn.dataTable.render.number(",", ".", 2, "") }

      ],
      columnDefs: [
        { targets: [1], className: "left aligned" },
        { targets: [0], className: "center aligned" },
        { targets: [2, 3, 4, 5], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {
      },
      rowCallback: (row, data, index) => {

      }

    });
  }


  // dataTable upload
  initDatatable2(): void {

    if (this.datatable2 != null || this.datatable2 != undefined) {
      this.datatable2.destroy();
    }

    this.datatable2 = $("#dataTable2").DataTableTh({
      lengthChange: true,
      searching: false,
      loading: true,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.dataLedgerSheet,
      columns: [
        { data: "accountNumber" },
        { data: "accountName" },
        { data: "debitType", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "creditType", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "liftUpType", render: $.fn.dataTable.render.number(",", ".", 2, "") }
      ],
      columnDefs: [
        { targets: [1], className: "left aligned" },
        { targets: [0], className: "center aligned" },
        { targets: [2, 3, 4], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {
      },
      rowCallback: (row, data, index) => {

      }

    });
  }

  // dataTable upload
  initDatatable3(): void {

    if (this.datatable3 != null || this.datatable3 != undefined) {
      this.datatable3.destroy();
    }

    this.datatable3 = $("#dataTable3").DataTableTh({
      lengthChange: true,
      searching: false,
      loading: true,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.dataSheet,
      columns: [
        { data: "accountNumber" },
        { data: "accountName" },
        { data: "summitTest", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "debitTest", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "creditTest", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "liftUpTest", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "debitType", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "creditType", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "liftUpType", render: $.fn.dataTable.render.number(",", ".", 2, "") },
        { data: "difference", render: $.fn.dataTable.render.number(",", ".", 2, "") }
      ],
      columnDefs: [
        { targets: [1], className: "left aligned" },
        { targets: [0], className: "center aligned" },
        { targets: [2, 3, 4, 5, 6, 7, 8,9], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {

        if(data.checkData =='Y'){
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
          }
        }else if(data.checkData =='T'){
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-blue');
          }
        }
      },
      rowCallback: (row, data, index) => {

      }

    });
  }

  clearData() {
    if (this.processList == 1) {
      this.dataTrialBalanceSheet = [];
      this.initDatatable1();
    } else if (this.processList == 2) {
      this.dataLedgerSheet = [];
      this.initDatatable2();
    }
  }

  nextData() {
    this.processList = 2;
    $("#upload-form").val("");
    $("#fileExel").val("");
    this.fileExel = [];
    $("#tb1").hide();
    $("#tb2").show();
    this.initDatatable2();
    console.log("Step ::" + this.processList);
  }

  checkData() {
    this.dataSheet = [];
    this.loading = true;
   
    $("#tb2").hide();
    $("#tbUpload").hide();
    $("#tb3").show();
    console.log("Step ::" + this.processList);

    let data = {
      "dataTrialBalanceSheet": this.dataTrialBalanceSheet,
      "dataLedgerSheet": this.dataLedgerSheet
    }

    const URL = "ia/int073/checkData";
    this.ajax.post(URL, data, res => {
      console.log(res.json());
     
      res.json().forEach(element => {
        this.dataSheet.push(element);
      });
      this.initDatatable3();
      this.processList = 4;
      setTimeout(() => {
        this.loading = false;
      }, 1000);
    }
    );

  }

  export() {
    const URL_DOWNLOAD = "ia/int073/export";
    this.ajax.download(URL_DOWNLOAD);
  }

}

class File {
  [x: string]: any;
  name: string;
  type: string;
  value: any;
}

class Data {
  id: any = 0;
  accountNumber: any = '';
  accountName: any = '';
  summitTest: any = '';
  debitTest: any = '';
  creditTest: any = '';
  liftUpTest: any = '';

  debitType: any = '';
  creditType: any = '';
  liftUpType: any = '';
  difference: any = '';
  checkData: any = '';
}

