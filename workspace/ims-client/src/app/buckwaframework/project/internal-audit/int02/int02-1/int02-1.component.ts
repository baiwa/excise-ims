import { Component, OnInit, ViewChild } from '@angular/core';
import { formatter, TextDateTH, toDateLocale, digit } from 'helpers/index';
import { AjaxService, IaService, AuthService } from 'services/index';
import { BreadCrumb } from 'models/index';
import { Router } from '@angular/router';
import { NgForm, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Int02Service } from '../int02.service';

declare var $: any;

const URL = {
  SAVE_MASTER: "ia/int02/save_qtn_master",
  DATATABLE: `${AjaxService.CONTEXT_PATH}ia/int02/qtn_master/datatable`,
  LOV_SECTOR: `combobox/controller/getDropByTypeAndParentId`
}

@Component({
  selector: 'app-int02-1',
  templateUrl: './int02-1.component.html',
  styleUrls: ['./int02-1.component.css'],
})
export class Int021Component implements OnInit {

  @ViewChild('f') form: NgForm;
  formGroups: FormGroup;
  datatable: any;
  dateTime: Date = new Date();
  breadcrumb: BreadCrumb[];
  isInit: string = "Y";
  submitted: boolean = false;
  constructor(
    private ajax: AjaxService,
    private router: Router,
    private iaService: IaService,
    private int02: Int02Service,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "แบบสอบถามระบบการควบคุมภายใน", route: "#" },
      { label: "สร้างแบบสอบทานระบบการควบคุมภายใน", route: "#" }
    ];
    this.formGroups = this.formBuilder.group({
      qtnFrom: [''],
      qtnTo: [''],
    });
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-02100');

    $(".ui.dropdown.ai").dropdown().css("width", "100%");

    this.initDatatable();

    $("#qtnFromCalendar").calendar({
      type: "date",
      endCalendar: $("#qtnToCalendar"),
      text: TextDateTH,
      formatter: formatter("date"),
      onChange: (date, text, mode) => {
        this.formGroups.get('qtnFrom').setValue(text);
        this.formGroups.get('qtnTo').setValidators([Validators.required]);
        this.formGroups.get('qtnFrom').setValidators([Validators.required]);
        this.formGroups.get('qtnTo').updateValueAndValidity();
        this.formGroups.get('qtnFrom').updateValueAndValidity();
      }
    });

    $("#qtnToCalendar").calendar({
      type: "date",
      startCalendar: $("#qtnFromCalendar"),
      text: TextDateTH,
      formatter: formatter("date"),
      onChange: (date, text, mode) => {
        this.formGroups.get('qtnTo').setValue(text);
        this.formGroups.get('qtnTo').setValidators([Validators.required]);
        this.formGroups.get('qtnFrom').setValidators([Validators.required]);
        this.formGroups.get('qtnTo').updateValueAndValidity();
        this.formGroups.get('qtnFrom').updateValueAndValidity();
      }
    });

    // Edited or Added ???
    $("#datatable tbody").on("click", "button", e => {
      const { id } = e.currentTarget;
      this.datatable.row($(e).parents("tr")).data();
      if ("edit" == id.split("-")[0]) {
        this.router.navigate(["/int02/2"], {
          queryParams: { id: id.split("-")[1] }
        });
      } else {
        console.log("Other ???");
      }
    });
  }

  initDatatable(): void {
    this.datatable = $("#datatable").DataTable({
      lengthChange: false,
      scrollX: true,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: true,
      ajax: {
        type: "POST",
        url: URL.DATATABLE,
        data: (d) => {
          d.qtnFrom = this.formGroups.value.qtnFrom
          d.qtnTo = this.formGroups.value.qtnTo
          d.isInit = this.isInit
        }
      },
      columns: [
        {
          data: "qtnMasterId",
          className: "center"
        },
        {
          render: (data, type, full, meta) => {
            return this.dateString(new Date(full.createdDate));
          },
          className: "center"
        },
        {
          data: "createdBy",
          className: "center"
        },
        {
          render: (data, type, full, meta) => {
            return this.dateString(new Date(full.updatedDate));
          },
          className: "center"
        },
        {
          render: (data, type, full, meta) => {
            return full.updatedBy;
          },
          className: "center"
        },
        {
          data: "qtnYear",
          className: "center"
        },
        {
          render: (data, type, full, meta) => { // data : "qtnFinished"
            let str = "";
            if (full.qtnFinished == "Y") {
              str = "ดำเนินการเสร็จสิ้น";
            } else {
              str = "รอดำเนินการ";
            }
            return str;
          },
          className: "center"
        },
        {
          render: (data, type, full, meta) => { // data : "qtnFinished"
            let str = "";
            if (full.qtnFinished == "Y") {
              str = "";
            } else {
              str = `<button class="ui  mini yellow button" id="edit-${full.qtnMasterId}" value="edit-${full.qtnMasterId}"><i class="edit icon"></i>แก้ไข</button>`;
            }
            return str;
          },
          className: "center"
        }
      ]
    });
  }

  onSubmit = event => {
    event.preventDefault();
    this.submitted = true;
    if (this.formGroups.valid) {
      this.datatable.destroy();
      this.isInit = "N";
      this.initDatatable();
    }
  }

  clear() {
    $("#qtnFromCalendar").calendar('refresh');
    $("#qtnToCalendar").calendar('refresh');
    this.formGroups.get('qtnTo').clearValidators();
    this.formGroups.get('qtnFrom').clearValidators();
    this.formGroups.get('qtnTo').updateValueAndValidity();
    this.formGroups.get('qtnFrom').updateValueAndValidity();
  }

  dateString(value: Date) {
    let day = value.getDate();
    let _month = toDateLocale(value)[0].split("/")[1];
    let _year = toDateLocale(value)[0].split("/")[2];
    return digit(day) + "/" + digit(_month) + "/" + _year.toString();
  }

  get invalidFrom() { return this.submitted && this.formGroups.get('qtnFrom').invalid }
  get invalidTo() { return this.submitted && this.formGroups.get('qtnTo').invalid }

}