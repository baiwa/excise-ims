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
export class Int073Component implements OnInit , AfterViewInit{

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบบัญชี", route: "#" },
    { label: "ตรวจสอบงบทดลองกระทบยอด เดบิต เครดิต บัญชีแยกประเภท", route: "#" },
  ];

  loading: boolean = false;
  fileExel: File[];
  dataTrialBalanceSheet: Data[] = [];
  // dataTable
  datatable: any;

  // processList
  processList:Number;
  
  constructor(
    private authService: AuthService,
    private ajax: AjaxService,
    
  ) { }


  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-07300');
    this.processList= 1;
  }


  ngAfterViewInit() {
    this.initDatatable();
  }



  onUpload = (event: any) => {
    this.loading = true;
    this.dataTrialBalanceSheet = [];
    console.log("อัพโหลด Excel");
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
        this.initDatatable();
        setTimeout(() => {
          this.loading = false;
        }, 1000);
      }
    )
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
    initDatatable(): void {
    
      if (this.datatable != null || this.datatable != undefined) {
        this.datatable.destroy();
      }
  
      this.datatable = $("#dataTable").DataTableTh({
        lengthChange: true,
        searching: false,
        loading:true,
        ordering: false,
        pageLength: 10,
        processing: true,
        serverSide: false,
        paging: true,
        data: this.dataTrialBalanceSheet,
        columns: [
          { data: "accountNumber" },
          { data: "accountName" },
          { data: "SummitTest" },
          { data: "debitTest" },
          { data: "creditTest" },
          { data: "liftUpTest" }

        ],
        columnDefs: [
          { targets: [1], className: "left aligned" },
          { targets: [0,2,3,4,5], className: "right aligned" },
        ],

        createdRow: function (row, data, dataIndex) {
        },
        rowCallback: (row, data, index) => {
  
        }
  
      });
    }

    clearData() {
      this.dataTrialBalanceSheet = [];
      this.initDatatable();
  
    }

    nextData() {
      this.processList= 2;
      $("#upload-form").val("");
      $("#fileExel").val("");
      this.fileExel = [];
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
  SummitTest: any = '';
  debitTest: any = '';
  creditTest: any = '';
  liftUpTest: any = '';

  debitType: any = '';
  creditType: any = '';
  liftUpType: any = '';
  difference: any = '';
  checkData: any = '';
}