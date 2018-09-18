import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AjaxService } from '../../../../common/services';
import { MessageBarService } from 'app/buckwaframework/common/services';
import { BreadCrumb } from '../../../../common/models';

declare var $: any;

@Component({
  selector: 'app-int07-6',
  templateUrl: './int07-6.component.html',
  styleUrls: ['./int07-6.component.css']
})
export class Int076Component implements OnInit {
  breadcrumb: BreadCrumb[];
  fileExel: File[];
  
  //Data
  dataList: Data[] = [];
  dataEdit: Data ;
  datatable: any;

  dataXIList:  Data[] = [];
  dataJ0List:  Data[] = [];

  view1: any;
  view2: any;

  constructor(
    private router: Router,
    private ajax: AjaxService,
    private message: MessageBarService) { 
      this.breadcrumb = [
        { label: "ตรวจสอบภายใน", route: "#" },
        { label: "ตรวจสอบบัญชี", route: "#" },
        { label: "ตรวจสอบการนำส่งเงินบัญชีเจ้าหนี้ อปท.", route: "#" }
      ];
    }

  ngOnInit() {
    this.dataEdit = new Data();  
    this.view1 = true;
    this.view2 = false;
  }

  ngAfterViewInit() {
    this.initDatatable();
  }

  onUpload = (event: any) => {
    event.preventDefault();

    console.log("อัพโหลด Excel");
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);

    let url = "ia/int076/excelINT076";
    this.ajax.upload(
      url,
      formBody,
      res => {
        console.log(res.json());
        res.json().forEach(element => {          
          this.dataList.push(element);
        });
       this.initDatatable();

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


  initDatatable(): void {

    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.dataList,
      columns: [
        { data: "datePosted" },
        { data: "docNumber" },
        { data: "docType" },
        { data: "docRefer"},
        { data: "actor"},
        { data: "determination"},
        { data: "payUnit"},
        { data: "debit"},
        { data: "credit"},
        { data: "liftUp"},
        {
          data: "id",
          render: function () {
            return '<button type="button" class="ui mini button primary edit"><i class="edit icon"></i> แก้ไข </button>';
          }
        }
      ],
      columnDefs: [
        { targets: [2,10], className: "center aligned" },
        { targets: [0,1,3,4,5,6,7,8,9], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {

      },
      rowCallback: (row, data, index) => {

        $("td > .edit", row).bind("click", () => {
          console.log(data);
          console.log(row);
          console.log(index);
          this.dataEdit= data;
         
          $('#editData').modal('show');
        });
      }

    });
  }


  editData(){
    this.dataList.forEach(element => {
      if(this.dataEdit.id===element.id){
        element = this.dataEdit;
        this.initDatatable();
      }
    }); 
  }


  checkData() {
    console.log("ตรวจสอบข้อมูล");
    this.view1 = false;
    this.view2 = true;
  }

  backPage() {
    console.log("กลับไปยังหน้าอัพโหลดดาต้า");
    this.view1 = true;
    this.view2 = false;
  }


  clearData() {
    this.dataList = [];
    this.initDatatable();
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
  datePosted: any = '';
  docNumber: any = '';
  docType: any = '';
  docRefer: any = '';
  actor: any = '';
  determination: any = '';
  payUnit: any = '';
  debit: any = '';
  credit: any = '';
  liftUp: any = '';

}

