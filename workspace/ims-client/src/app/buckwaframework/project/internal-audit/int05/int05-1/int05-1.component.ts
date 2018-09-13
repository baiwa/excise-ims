import { MessageBarService } from 'app/buckwaframework/common/services';
import { TextDateTH, formatter } from './../../../../common/helper/datepicker';
import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../common/services";
import { calendarFormat } from 'moment';
declare var $: any;
@Component({
  selector: "int05-1",
  templateUrl: "./int05-1.component.html",
  styleUrls: ["./int05-1.component.css"]
})
export class Int051Component implements OnInit {
  sector: any;
  area: any;
  branch: any;
  showData: boolean = true;
  $form: any;
  formModal: FormModal = new FormModal();
  listFileName : any;
  constructor(
    private ajax: AjaxService,
    private message: MessageBarService) {
  }


  ngOnInit() {
    this.$form = $("#formSearch");
    $(".ui.dropdown.ai").dropdown().css('width', '100%');
    this.sectorDropdown();
    this.calenda();
    this.table();
  }
  ngAfterViewInit() {
    $(".ui.dropdown.ai").dropdown().css('width', '100%');;
  }
  sectorDropdown = () => {
    const URL = "ia/int0511/sector";
    this.ajax.get(URL, res => {
      this.sector = res.json();
    });
  }
  sectorOnchange = (e) => {
    $("#area").dropdown('restore defaults');
    console.log(e);
    const URL = "ia/int0511/area";
    let params = e.target.value;
    if(params!=""){
      this.ajax.post(URL, params, res => {
        console.log("Id : ", res.json());
        this.area = res.json();
      });
    }
    
  }
  areaOnchange = (e) => {
    $("#branch").dropdown('restore defaults');
    const URL = "ia/int0511/area";    
    let params = e.target.value;

    if(params!=""){
    this.ajax.post(URL, params, res => {
      console.log("Id : ", res.json());
      this.branch = res.json();
    });
  }
  }

  onClear = () => {
    console.log("Clear");
    $(".ui.dropdown.ai").dropdown('restore defaults');
    $("#dateForm").val("");
    $("#dateTo").val("");
    $("#searchFlag").val("FALSE");
    $("#dataTable").DataTable().ajax.reload();
  }

  onSearch = () => {
    $("#searchFlag").val("TRUE");
    $("#dataTable").DataTable().ajax.reload();


  }

  calenda = () => {
    $("#dateF").calendar({
      maxDate: new Date(),
      endCalendar: $("#dateTo"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateForm"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateOfPayForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    }, 'blur');
    $("#dateWithdrawStampForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateDeliverStampForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#fivePartDateForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#stampCheckDateForm").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  modalEditSubmit = () => {
    this.message.comfirm((res) => {
      if (res) {
        const URL = 'ia/int0511/save'

        let data = {
          "data": this.formModal
        };
        this.ajax.post(URL, JSON.stringify(data),
          res => {
            this.message.successModal("ทำรายสำเร็จ", "แจ้งเตือน");
            $("#dataTable").DataTable().ajax.reload();
          }, error => {
            this.message.errorModal("ทำรายไม่สำเร็จ", "แจ้งเตือน");
          });
      }
    }, "ยืนยันการทำรายการ");



  }

  table = () => {
    const table = $("#dataTable").DataTable({
      "serverSide": true,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int0511/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "sector": ($("#sector option:selected").text()=="กรุณาเลือก" ? "":$("#sector option:selected").text()),
            "area": ($("#area option:selected").text()=="กรุณาเลือก" ? "":$("#area option:selected").text()),
            "branch": ($("#branch option:selected").text()=="กรุณาเลือก" ? "":$("#branch option:selected").text()),
            "dateForm": $("#dateForm").val(),
            "dateTo": $("#dateTo").val(),
            "searchFlag": $("#searchFlag").val()
          }));
        },
      },

      "columns": [
        {
          "data": "dateOfPay",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned"
        }, {
          "data": "dateOfPay"
        }, {
          "data": "status",
          "className": "ui center aligned",
        }, {
          "data": "departmentName",
          "className": "ui center aligned"
        }, {
          "data": "bookNumberWithdrawStamp",
          "className": "ui center aligned"
        }, {
          "data": "dateWithdrawStamp",
          "className": "ui center aligned"
        }, {
          "data": "bookNumberDeliverStamp",
          "className": "ui center aligned"
        }, {
          "data": "dateDeliverStamp",
          "className": "ui center aligned"
        }, {
          "data": "fivePartNumber",
          "className": "ui center aligned"
        }, {
          "data": "fivePartDate",
          "className": "ui center aligned"
        }, {
          "data": "stampCheckDate",
          "className": "ui center aligned"
        }, {
          "data": "stampChecker",
          "className": "ui center aligned"
        }, {
          "data": "stampBrand",
          "className": "ui center aligned"
        }, {
          "data": "numberOfBook",
          "className": "ui center aligned"
        }, {
          "data": "numberOfStamp",
          "className": "ui center aligned"
        }, {
          "data": "valueOfStampPrinted",
          "className": "ui center aligned"
        }, {
          "data": "sumOfValue",
          "className": "ui center aligned"
        }, {
          "data": "serialNumber",
          "className": "ui center aligned"
        }, {
          "data": "note",
          "className": "ui center aligned"
        }, {
          "data": "note",
          "render": function (data, type, row) {
            var btn = '';
            btn += '<button class="ui mini blue button btn-detail">รายละเอียด</button>';
            btn += '<button class="ui mini yellow button btn-edit">แก้ไข</button>';
            btn += '<button class="ui mini red button btn-delete">ลบ</button>';
            return btn;
          },
          "className": "ui center aligned"
        }
      ]
    });
    table.on('click', 'tbody tr button.btn-detail', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      console.log(data);

      $('#modal-detail').modal({ 
        autofocus: false,
        onShow : ()=>{
          this.formModal.dateOfPay = data.dateOfPay;
          this.formModal.bookNumberDeliverStamp = data.bookNumberDeliverStamp;
          this.formModal.bookNumberWithdrawStamp = data.bookNumberWithdrawStamp;
          this.formModal.createdDate = data.createdDate;
          this.formModal.dateDeliverStamp = data.dateDeliverStamp;
          this.formModal.dateWithdrawStamp = data.dateWithdrawStamp;
          this.formModal.departmentName = data.departmentName;
          this.formModal.exciseDepartment = data.exciseDepartment;
          this.formModal.exciseDistrict = data.exciseDistrict;
          this.formModal.exciseRegion = data.exciseRegion;
          this.formModal.fivePartDate = data.fivePartDate;
          this.formModal.fivePartNumber = data.fivePartNumber;
          this.formModal.note = data.note;
          this.formModal.numberOfBook = data.numberOfBook;
          this.formModal.numberOfStamp = data.numberOfStamp;
          this.formModal.serialNumber = data.serialNumber;
          this.formModal.stampBrand = data.stampBrand;
          this.formModal.stampCheckDate = data.stampCheckDate;
          this.formModal.stampChecker = data.stampChecker;
          this.formModal.stampCodeEnd = data.stampCodeEnd;
          this.formModal.stampCodeStart = data.stampCodeStart;
          this.formModal.stampType = data.stampType;
          this.formModal.status = data.status;
          this.formModal.sumOfValue = data.sumOfValue;
          this.formModal.taxStamp = data.taxStamp;
          this.formModal.valueOfStampPrinted = data.valueOfStampPrinted;
          this.formModal.workSheetDetailId = data.workSheetDetailId;
          this.formModal.fileName = data.fileName;
          var url = 'ia/int0511/listFile';
          this.ajax.post(url,JSON.stringify(data.workSheetDetailId),res=>{
              this.listFileName = res.json();
          });
        }
      }).modal('show');
    });
    table.on('click', 'tbody tr button.btn-edit', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      $('#modal-edit').modal({
         autofocus: false,
         onShow : ()=>{
           
              console.log("FormModal : ", data);
              this.formModal.dateOfPay = data.dateOfPay;
              this.formModal.bookNumberDeliverStamp = data.bookNumberDeliverStamp;
              this.formModal.bookNumberWithdrawStamp = data.bookNumberWithdrawStamp;
              this.formModal.createdDate = data.createdDate;
              this.formModal.dateDeliverStamp = data.dateDeliverStamp;
              this.formModal.dateWithdrawStamp = data.dateWithdrawStamp;
              this.formModal.departmentName = data.departmentName;
              this.formModal.exciseDepartment = data.exciseDepartment;
              this.formModal.exciseDistrict = data.exciseDistrict;
              this.formModal.exciseRegion = data.exciseRegion;
              this.formModal.fivePartDate = data.fivePartDate;
              this.formModal.fivePartNumber = data.fivePartNumber;
              this.formModal.note = data.note;
              this.formModal.numberOfBook = data.numberOfBook;
              this.formModal.numberOfStamp = data.numberOfStamp;
              this.formModal.serialNumber = data.serialNumber;
              this.formModal.stampBrand = data.stampBrand;
              this.formModal.stampCheckDate = data.stampCheckDate;
              this.formModal.stampChecker = data.stampChecker;
              this.formModal.stampCodeEnd = data.stampCodeEnd;
              this.formModal.stampCodeStart = data.stampCodeStart;
              this.formModal.stampType = data.stampType;
              this.formModal.status = data.status;
              this.formModal.sumOfValue = data.sumOfValue;
              this.formModal.taxStamp = data.taxStamp;
              this.formModal.valueOfStampPrinted = data.valueOfStampPrinted;
              this.formModal.workSheetDetailId = data.workSheetDetailId;
              this.formModal.fileName = data.fileName;
              $("#status").dropdown('set selected');
              this.calenda();
         }
        }).modal('show');      

    });
    table.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      console.log(data);

      this.message.comfirm((res) => {
        if (res) {
          const URL = 'ia/int0511/delete'

          let Data = {
            "data": data
          };
          console.log(Data);
          this.ajax.post(URL, JSON.stringify(Data),
            res => {
              this.message.successModal("ทำรายสำเร็จ", "แจ้งเตือน");
              $("#dataTable").DataTable().ajax.reload();
            }, error => {
              this.message.errorModal("ทำรายไม่สำเร็จ", "แจ้งเตือน");
            });
        }
      }, "", "ยืนยันการลบ");

    });
  }
}

class FormModal {
  workSheetDetailId: string = null;
  exciseDepartment: string = null;
  exciseRegion: string = null;
  exciseDistrict: string = null;
  dateOfPay: string = null;
  status: string = null;
  departmentName: string = null;
  bookNumberWithdrawStamp: string = null;
  dateWithdrawStamp: string = null;
  bookNumberDeliverStamp: string = null;
  dateDeliverStamp: string = null;
  fivePartNumber: string = null;
  fivePartDate: string = null;
  stampCheckDate: string = null;
  stampChecker: string = null;
  stampType: string = null;
  stampBrand: string = null;
  numberOfBook: string = null;
  numberOfStamp: string = null;
  valueOfStampPrinted: string = null;
  sumOfValue: string = null;
  serialNumber: string = null;
  taxStamp: string = null;
  stampCodeStart: string = null;
  stampCodeEnd: string = null;
  note: string = null;
  createdDate: string = null;
  fileName: string = null;
}
