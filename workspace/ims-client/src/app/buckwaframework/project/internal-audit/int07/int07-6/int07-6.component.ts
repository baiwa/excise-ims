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
export class Int076Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[];
  fileExel: File[];
  del: any;
  //Data Upload
  dataList: Data[] = [];
  dataEdit: Data;
  // dataTable
  datatable: any;
  datatableIx: any;
  datatableJ0: any;
  // type Data
  dataIxList: Data[] = [];
  dataJ0List: Data[] = [];

  //tmp
  tmp1: any[] = [];
  tmp2: any[] = [];
  tmp3: any[] = [];
  tmp4: any[] = [];
  tmp5: any[] = [];
  tmp6: any[] = [];
  tmp7: any[] = [];
  tmp8: any[] = [];
  tmp9: any[] = [];
  tmp10: any[] = [];
  tmp11: any[] = [];
  tmp12: any[] = [];

  view1: any;
  view2: any;
  viewButton: any;

  constructor(
    private router: Router,
    private ajax: AjaxService,
    private message: MessageBarService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบบัญชี", route: "#" },
      { label: "ตรวจสอบการนำส่งเงินบัญชีเจ้าหนี้ อปท.", route: "#" }
    ];
  }

  ngOnInit() {
    this.dataEdit = new Data();
    this.view1 = true;


    if (this.view1 === true) {
      this.viewButton = false;

    } else {
      this.viewButton = true;

    }
  }

  ngAfterViewInit() {
    this.initDatatable();
    this.initDatatableIx();
    this.initDatatableJ0();
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

  // dataTable upload
  initDatatable(): void {

    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }

    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 100,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.dataList,
      columns: [
        { data: "datePosted" },
        { data: "docNumber" },
        { data: "docType" },
        { data: "docRefer" },
        { data: "actor" },
        { data: "determination" },
        { data: "payUnit" },
        { data: "debit" },
        { data: "credit" },
        { data: "liftUp" },
        {
          data: "id",
          render: function () {
            return '<button type="button" class="ui mini button primary edit"><i class="edit icon"></i> แก้ไข </button>'
              + '<button type="button" class="ui mini button del"><i class="trash alternate icon"></i> ลบ</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [2, 10], className: "center aligned" },
        { targets: [0, 1, 3, 4, 5, 6, 7, 8, 9], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {

      },
      rowCallback: (row, data, index) => {

        $("td > .edit", row).bind("click", () => {
          console.log(data);
          console.log(row);
          console.log(index);
          this.dataEdit = data;

          $('#editData').modal('show');
        });

        $("td > .del", row).bind("click", () => {
          console.log(data);
          console.log(row);
          console.log(index);
          this.del = index;
          $('#delData').modal('show');
        });

      }

    });
  }
  //dataTable Type Ix
  initDatatableIx(): void {

    if (this.datatableIx != null || this.datatableIx != undefined) {
      this.datatableIx.destroy();
    }
    this.datatableIx = $("#datatableIx").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 100,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.dataIxList,
      columns: [
        { data: "datePosted" },
        { data: "docNumber" },
        { data: "docType" },
        { data: "docRefer" },
        { data: "actor" },
        { data: "determination" },
        { data: "payUnit" },
        { data: "debit" },
        { data: "credit" },
        { data: "liftUp" },
      ],
      columnDefs: [
        { targets: [2], className: "center aligned" },
        { targets: [0, 1, 3, 4, 5, 6, 7, 8, 9], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {
        // console.log("row");
        // console.log("data", data.color);
        // console.log("dataIndex", dataIndex);
        if (data.color == '1') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-01');
          }
        } else if (data.color == '2') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-02');
          }
        } else if (data.color == '3') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-03');
          }
        } else if (data.color == '4') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-04');
          }
        } else if (data.color == '5') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-05');
          }
        } else if (data.color == '6') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-06');
          }
        } else if (data.color == '7') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-07');
          }
        } else if (data.color == '8') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-08');
          }
        } else if (data.color == '9') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-09');
          }
        } else if (data.color == '10') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-10');
          }
        } else if (data.color == '11') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-11');
          }
        } else if (data.color == '12') {
          for (let i = 0; i <= 9; i++) {
            $(row).find('td:eq(' + i + ')').addClass('bg-m-12');
          }
        }
      },
      rowCallback: (row, data, index) => {
        if (data.color == '1') {
          this.tmp1.push(data.liftUp);
        } else if (data.color == '2') {
          this.tmp2.push(data.liftUp);
        } else if (data.color == '3') {
          this.tmp3.push(data.liftUp);
        } else if (data.color == '4') {
          this.tmp4.push(data.liftUp);
        } else if (data.color == '5') {
          this.tmp5.push(data.liftUp);
        } else if (data.color == '6') {
          this.tmp6.push(data.liftUp);
        } else if (data.color == '7') {
          this.tmp7.push(data.liftUp);
        } else if (data.color == '8') {
          this.tmp8.push(data.liftUp);
        } else if (data.color == '9') {
          this.tmp9.push(data.liftUp);
        } else if (data.color == '10') {
          this.tmp10.push(data.liftUp);
        } else if (data.color == '11') {
          this.tmp11.push(data.liftUp);
        } else if (data.color == '12') {
          this.tmp12.push(data.liftUp);
        }
      }

    });
  }
  //dataTable Type J0
  initDatatableJ0(): void {

    if (this.datatableJ0 != null || this.datatableJ0 != undefined) {
      this.datatableJ0.destroy();
    }
    this.datatableJ0 = $("#datatableJ0").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 100,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.dataJ0List,
      columns: [
        { data: "datePosted" },
        { data: "docNumber" },
        { data: "docType" },
        { data: "docRefer" },
        { data: "actor" },
        { data: "determination" },
        { data: "payUnit" },
        { data: "debit" },
        { data: "credit" },
        { data: "liftUp" },
      ],
      columnDefs: [
        { targets: [2], className: "center aligned" },
        { targets: [0, 1, 3, 4, 5, 6, 7, 8, 9], className: "right aligned" },
      ],

      createdRow: function (row, data, dataIndex) {
        // console.log("row");
        // console.log("data", data.credit);
        // console.log("data", data.color);
        // console.log("dataIndex", dataIndex);
        // if (data.color == '1') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-01');
        //   }
        // } else if (data.color == '2') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-02');
        //   }
        // } else if (data.color == '3') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-03');
        //   }
        // } else if (data.color == '4') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-04');
        //   }
        // } else if (data.color == '5') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-05');
        //   }
        // } else if (data.color == '6') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-06');
        //   }
        // } else if (data.color == '7') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-07');
        //   }
        // } else if (data.color == '8') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-08');
        //   }
        // } else if (data.color == '9') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-09');
        //   }
        // } else if (data.color == '10') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-10');
        //   }
        // } else if (data.color == '11') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-11');
        //   }
        // } else if (data.color == '12') {
        //   for (let i = 0; i <= 9; i++) {
        //     $(row).find('td:eq(' + i + ')').addClass('bg-m-12');
        //   }
        // }

      },
      rowCallback: (row, data, index) => {
        if (data.color == '1') {
          console.log(this.tmp12[this.tmp12.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp12[this.tmp12.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-01');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-01');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-01');
            }
          }
        } else if (data.color == '2') {
          console.log(this.tmp1[this.tmp1.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp1[this.tmp1.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-02');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-02');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-02');
            }
          }
        } else if (data.color == '3') {
          console.log(this.tmp2[this.tmp2.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp2[this.tmp2.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-03');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-03');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-03');
            }
          }
        } else if (data.color == '4') {
          console.log(this.tmp3[this.tmp3.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp3[this.tmp3.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-04');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-04');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-04');
            }
          }
        } else if (data.color == '5') {
          console.log(this.tmp4[this.tmp4.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp4[this.tmp4.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-05');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-05');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-05');
            }
          }
        } else if (data.color == '6') {
          console.log(this.tmp5[this.tmp5.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp5[this.tmp5.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-06');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-06');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-06');
            }
          }
        } else if (data.color == '7') {
          console.log(this.tmp6[this.tmp6.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp6[this.tmp6.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-07');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-07');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-07');
            }
          }
        } else if (data.color == '8') {
          console.log(this.tmp7[this.tmp7.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp7[this.tmp7.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-08');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-08');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-08');
            }
          }
        } else if (data.color == '9') {
          console.log(this.tmp8[this.tmp8.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp8[this.tmp8.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-09');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-09');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-09');
            }
          }
        } else if (data.color == '10') {
          console.log(this.tmp9[this.tmp9.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp9[this.tmp9.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-10');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-10');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-10');
            }
          }
        } else if (data.color == '11') {
          console.log(this.tmp10[this.tmp10.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp10[this.tmp10.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-11');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-11');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-11');
            }
          }
        } else if (data.color == '12') {
          console.log(this.tmp11[this.tmp11.length - 1]);
          if (data.credit == null || data.credit == undefined ||data.credit == ""){
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-red');
            }
          }else if (data.credit != this.tmp11[this.tmp11.length - 1] )  {
            $(row).find('td:eq(8)').addClass('bg-m-red');
            $(row).find('td:eq(9)').addClass('bg-m-12');
            for (let i = 0; i <= 7; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-12');
            }
          }else{
            for (let i = 0; i <= 9; i++) {
              $(row).find('td:eq(' + i + ')').addClass('bg-m-12');
            }
          }
        }

      }

    });
  }


  editData() {
    this.dataList.forEach(element => {
      if (this.dataEdit.id === element.id) {
        element = this.dataEdit;
        this.initDatatable();
      }
    });
  }

  delData() {
    console.log("ตำแหน่ง" + this.del);
    this.dataList.splice(this.del, 1);
    this.initDatatable();
  }

  backPage() {
    this.view1 = true;
    if (this.view1 === true) {
      this.viewButton = false;

    } else {
      this.viewButton = true;

    }
    this.dataList = [];
    this.dataIxList = [];
    this.dataJ0List = []
    this.initDatatable();
    this.initDatatableIx();
    this.initDatatableJ0();
    console.log("กลับหน้าอัปโหลด");
  }

  checkData() {
    this.view1 = false;
    if (this.view1 === true) {
      this.viewButton = false;

    } else {
      this.viewButton = true;

    }
    console.log("ตรวจสอบข้อมูล");
    this.dataList.forEach(element => {
      if (element.docType === "IX") {
        this.dataIxList.push(element);
      } else if (element.docType === "J0") {
        this.dataJ0List.push(element);
      }
    });
    this.initDatatableIx();
    this.initDatatableJ0();
  }


  clearData() {
    this.dataList = [];
    this.dataIxList = [];
    this.dataJ0List = [];
    this.initDatatable();
    this.initDatatableIx();
    this.initDatatableJ0();
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
  color: any = '';

}

