import { Component, OnInit } from '@angular/core';
import { AjaxService, MessageBarService, AuthService } from 'app/buckwaframework/common/services';
import { TextDateTH, Utils } from 'app/buckwaframework/common/helper';
import { formatter } from 'app/buckwaframework/common/helper/datepicker';
import { BreadCrumb } from 'models/breadcrumb';
import { async } from '@angular/core/testing';
import { FormSearch } from 'projects/internal-audit/int06/int06-6/form-search.model';
declare var $: any;
const URL = {
  export: "ia/int0511/exportFile"
}
@Component({
  selector: 'app-int05-1-1',
  templateUrl: './int05-1-1.component.html',
  styleUrls: ['./int05-1-1.component.css']
})


export class Int0511Component implements OnInit {
  form: FormSearch = new FormSearch();
  sector: any;
  area: any;
  branch: any;
  showData: boolean = true;
  statusList: any;
  $form: any;
  listFileName: any;
  breadcrumb: BreadCrumb[]
  loading: boolean = false;
  formModal: FormModal = new FormModal();
  constructor(
    private authService: AuthService,
    private ajax: AjaxService,
    private message: MessageBarService) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "ตรวจสอบแสตมป์", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-05110');
    this.$form = $("#formSearch");
    $(".ui.dropdown.ai").dropdown().css('width', '100%');
    this.sectorDropdown();
    this.calenda();
    this.table();
    this.status();
  }
  ngAfterViewInit() {
    $(".ui.dropdown.ai").dropdown().css('width', '100%');;
  }
  sectorDropdown = () => {
    const URL = "ia/int05111/sector";
    this.ajax.get(URL, res => {
      this.sector = res.json();      
    });
  }
  sectorOnchange = (e) => {
    this.area = null;
    this.branch = null;
    $("#area").dropdown('restore defaults');
    const URL = "ia/int05111/area";
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL, params, res => {
        console.log("Id : ", res.json());
        this.area = res.json();
      });
    }
  }
  areaOnchange = (e) => {
    this.branch = null;
    $("#branch").dropdown('restore defaults');
    const URL = "ia/int05111/branch";
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL, params, res => {
        console.log("Id : ", res.json());
        this.branch = res.json();
      });
    }
  }

  status = () => {
    let url = "ia/int05111/status"
    this.ajax.get(url, res => {
      this.statusList = res.json();
    })
  }


  exportFile = () => {
    
    let param = "";
    param += "?sector=" + this.form.sector;
    param += "&area=" + this.form.area;
    param += "&branch=" + this.form.branch;
    param += "&dateFrom=" + this.form.dateFrom;
    param += "&dateTo=" + this.form.dateTo;
    param += "&budgetType=" + $("#budgetType").val();
    console.log(URL.export + param);
    this.ajax.download(URL.export + param);

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
      endCalendar: $("#dateT"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateF"),
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
            this.message.successModal("ทำรายการสำเร็จ", "แจ้งเตือน");
            $("#dataTable").DataTable().ajax.reload();
          }, error => {
            this.message.errorModal("ทำรายการไม่สำเร็จ", "แจ้งเตือน");
          });
      }
    }, "คุณต้องการแก้ไขข้อมูลใช่หรือไม่ ? ");



  }

  table = () => {
    const table = $("#dataTable").DataTableTh({
      "serverSide": true,
      "searching": false,
      "processing": true,
      "ordering": false,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int05111/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "sector": $("#sector").val(),//($("#sector option:selected").text()=="กรุณาเลือก" ? "":$("#sector option:selected").text()),
            "area": $("#area").val(),//($("#area option:selected").text()=="กรุณาเลือก" ? "":$("#area option:selected").text()),
            "branch": $("#branch").val(),//($("#branch option:selected").text()=="กรุณาเลือก" ? "":$("#branch option:selected").text()),
            "dateForm": $("#dateForm").val(),
            "dateTo": $("#dateTo").val(),
            "searchFlag": $("#searchFlag").val(),
            "status": $("#status").val()
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
          "data": "dateOfPay",
          "className": "ui center aligned"
        }, {
          "data": "status",
          "className": "ui center aligned",
        }, {
          "data": "departmentName",
          "className": "ui left aligned"
        }, {
          "data": "bookNumberWithdrawStamp",
          "className": "ui left aligned"
        }, {
          "data": "dateWithdrawStamp",
          "className": "ui center aligned"
        }, {
          "data": "bookNumberDeliverStamp",
          "className": "ui left aligned"
        }, {
          "data": "dateDeliverStamp",
          "className": "ui center aligned"
        }, {
          "data": "fivePartNumber",
          "className": "ui left aligned"
        }, {
          "data": "fivePartDate",
          "className": "ui center aligned"
        }, {
          "data": "stampCheckDate",
          "className": "ui center aligned"
        }, {
          "data": "stampChecker",
          "className": "ui left aligned"
        }, {
          "data": "stampChecker2",
          "className": "ui left aligned"
        }, {
          "data": "stampChecker3",
          "className": "ui left aligned"
        }, {
          "data": "stampBrand",
          "className": "ui left aligned"
        }, {
          "data": "numberOfBook",
          "className": "ui right aligned"
        }, {
          "data": "numberOfStamp",
          "className": "ui right aligned",
          "render": function (data) {
            return Utils.moneyFormatInt(data);
          }
        }, {
          "data": "valueOfStampPrinted",
          "className": "ui right aligned",
          "render": function (data) {
            return Utils.moneyFormat(data);
          }
        }, {
          "data": "sumOfValue",
          "className": "ui right aligned",
          "render": function (data) {
            return Utils.moneyFormat(data);
          }
        }, {
          "data": "taxStamp",
          "className": "ui right aligned",
          "render": function (data) {
            return Utils.moneyFormat(data);
          }
        }, {
          "data": "stampCodeStart",
          "className": "ui left aligned"
        }, {
          "data": "stampCodeEnd",
          "className": "ui left aligned"
        }, {
          "data": "note",
          "className": "ui left aligned"
        }, {
          "data": "note",
          "render": function (data, type, row) {
            var btn = '';
            // btn += '<button class="ui mini blue button btn-detail"><i class="eye icon"></i>รายละเอียด</button>';
            btn += '<button class="ui mini yellow button btn-edit"><i class="edit icon"></i>แก้ไข</button>';
            btn += '<button class="ui mini red button btn-delete"><i class="trash alternate icon"></i>ลบ</button>';
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
        onHidden: () => {
          this.formModal = new FormModal();
        },
        onDeny: () => {
          this.formModal = new FormModal();
        },
        onShow: () => {
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
          this.formModal.stampChecker2 = data.stampChecker2;
          this.formModal.stampChecker3 = data.stampChecker3;
          this.formModal.stampCodeEnd = data.stampCodeEnd;
          this.formModal.stampCodeStart = data.stampCodeStart;
          this.formModal.stampType = data.stampType;
          this.formModal.status = data.status;
          this.formModal.sumOfValue = data.sumOfValue;
          this.formModal.taxStamp = data.taxStamp;
          this.formModal.valueOfStampPrinted = data.valueOfStampPrinted;
          this.formModal.workSheetDetailId = data.workSheetDetailId;
          this.formModal.fileName = data.fileName;
          this.formModal.dateOfPay = data.dateOfPay;
          var url = 'ia/int0511/listFile';
          this.ajax.post(url, JSON.stringify(data.workSheetDetailId), res => {
            this.listFileName = res.json();
          });
        }

      }).modal('show');
    });
    table.on('click', 'tbody tr button.btn-edit', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
      this.loading = true;
      $('#modal-edit').modal({
        autofocus: false,
        onHidden: () => {
          this.formModal = new FormModal();
          this.loading = false;
        },
        onDeny: () => {
          this.formModal = new FormModal();
          this.loading = false;
        },
        onShow: async () => {

          console.log("FormModal : ", data);
          this.calenda();
          this.formModal.dateOfPay = await data.dateOfPay;
          this.formModal.bookNumberDeliverStamp = await data.bookNumberDeliverStamp;
          this.formModal.bookNumberWithdrawStamp = await data.bookNumberWithdrawStamp;
          this.formModal.createdDate = await data.createdDate;
          this.formModal.dateDeliverStamp = await data.dateDeliverStamp;
          this.formModal.dateWithdrawStamp = await data.dateWithdrawStamp;
          this.formModal.departmentName = await data.departmentName;
          this.formModal.exciseDepartment = await data.exciseDepartment;
          this.formModal.exciseDistrict = await data.exciseDistrict;
          this.formModal.exciseRegion = await data.exciseRegion;
          this.formModal.fivePartDate = await data.fivePartDate;
          this.formModal.fivePartNumber = await data.fivePartNumber;
          this.formModal.note = await data.note;
          this.formModal.numberOfBook = await data.numberOfBook;
          this.formModal.numberOfStamp = await data.numberOfStamp;
          this.formModal.serialNumber = await data.serialNumber;
          this.formModal.stampBrand = await data.stampBrand;
          this.formModal.stampCheckDate = await data.stampCheckDate;
          this.formModal.stampChecker = await data.stampChecker;
          this.formModal.stampChecker2 = await data.stampChecker2;
          this.formModal.stampChecker3 = await data.stampChecker3;
          this.formModal.stampCodeEnd = await data.stampCodeEnd;
          this.formModal.stampCodeStart = await data.stampCodeStart;
          this.formModal.stampType = await data.stampType;
          this.formModal.status = await data.status;
          this.formModal.sumOfValue = await data.sumOfValue;
          this.formModal.taxStamp = await data.taxStamp;
          this.formModal.valueOfStampPrinted = await data.valueOfStampPrinted;
          this.formModal.workSheetDetailId = await data.workSheetDetailId;
          this.formModal.fileName = await data.fileName;
          this.formModal.dateOfPay = await data.dateOfPay;

          await $("#status").dropdown('set selected', this.formModal.status);

        },
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
              this.message.successModal("ทำรายการสำเร็จ", "แจ้งเตือน");
              $("#dataTable").DataTable().ajax.reload();
            }, error => {
              this.message.errorModal("ทำรายการไม่สำเร็จ", "แจ้งเตือน");
            });
        }
      }, "คุณต้องการลบข้อมูลใช่หรือไม่ ? ");

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
  stampChecker2: string = null;
  stampChecker3: string = null;
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
