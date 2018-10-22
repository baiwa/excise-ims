import { Component, OnInit, ViewChild } from '@angular/core';
import { formatter, TextDateTH, toDateLocale } from 'helpers/index';
import { AjaxService, IaService, AuthService } from 'services/index';
import { BreadCrumb } from 'models/index';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
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
  datatable: any;
  sectors: any[] = [];
  areas: any[] = [];
  qtnName: string;
  qtnYear: string;
  breadcrumb: BreadCrumb[];
  constructor(private ajax: AjaxService, private router: Router, private iaService: IaService, private int02: Int02Service,
    private authService: AuthService) {
    this.qtnName = null;
    this.qtnYear = null;
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "แบบสอบถามระบบการควบคุมภายใน", route: "#" },
      { label: "สร้างแบบสอบทานระบบการควบคุมภายใน", route: "#" }
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-02100');
    this.ajax.post(URL.LOV_SECTOR, { type: "SECTOR_VALUE" }, res => { // SECTOR
      this.sectors = res.json();
    });

    $(".ui.dropdown.ai").dropdown().css("width", "100%");

    this.datatable = $("#datatable").DataTable({
      lengthChange: false,
      scrollX: true,
      searching: false,
      select: true,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true
    });

    $("#calendar").calendar({
      maxDate: new Date(),
      type: "year",
      text: TextDateTH,
      formatter: formatter("year"),
      onChange: (date, text, mode) => {
        let _year = toDateLocale(date)[0].split("/")[2];
        this.form.value.calendar_data = _year;
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
      select: true,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      pagingType: "full_numbers",
      ajax: {
        type: "POST",
        url: URL.DATATABLE,
        data: {}
      },
      columns: [
        {
          data: "qtnMasterId",
          className: "center"
        },
        {
          data: "qtnName",
          className: "center"
        },
        {
          data: "createdBy",
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

  reDatatable = () => {
    this.datatable.destroy();
    this.initDatatable();
  }

  onSubmit = (form: NgForm) => {
    const { calendar_data, sector, area } = form.value;
    this.reDatatable();
  }

  checkValid(name, f: NgForm) {
    return f.value[name] == '' && !f.valid && f.submitted;
  }

  onSectorChange(e) {
    e.preventDefault();
    let id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.LOV_SECTOR, { type: "SECTOR_VALUE", lovIdMaster: id }, async res => {
        await $("#area").dropdown('restore defaults');
        this.areas = res.json();
      });
    }
  }

  clear() {
    $("#sector").dropdown('restore defaults');
    $("#area").dropdown('restore defaults');
    $("#calendar").calendar('refresh');
  }

}