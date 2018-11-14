import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Tsl010200Service } from './tsl010200.service';
import { BreadCrumb } from 'models/breadcrumb';
import { AuthService } from 'services/auth.service';
import { IaService } from 'services/ia.service';
import { TextDateTH } from 'helpers/datepicker';
import { MessageBarService } from 'services/message-bar.service';
import { Router } from '@angular/router';


declare var $: any;
@Component({
  selector: 'app-tsl010200',
  templateUrl: './tsl010200.component.html',
  styleUrls: ['./tsl010200.component.css'],
  providers: [Tsl010200Service]
})
export class Tsl010200Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การคัดเลือกราย', route: '#' },
    { label: 'ตารางแสดงผลการคัดเลือกราย', route: '#' },
  ]
  month: number = 7;
  form: any;
  dispaly: any = {
    dateFrom: '',
    dateTo: '',
    month: ''
  }

  diff: any;
  toggle: boolean = true;
  loading: boolean = true;

  // ==== show table ===
  table1: boolean = false;
  table2: boolean = true;
  table3: boolean = true;
  constructor(
    private objService: IaService,
    private myService: Tsl010200Service,
    private authService: AuthService,
    private messege: MessageBarService,
    private router: Router
  ) {
    this.authService.reRenderVersionProgram('TSL-010200');
    this.form = this.objService.getData();
    console.log("form : ", this.form);

    if (this.form == null) {
      this.router.navigate(['/tax-audit-select-line/tsl0101-00']);
    } else {
      this.dispaly.dateFrom = TextDateTH.months[parseInt(this.form.dateFrom.substr(0, 2)) - 1];
      this.dispaly.dateTo = TextDateTH.months[parseInt(this.form.dateTo.substr(0, 2)) - 1];
      //this.dispaly.month = parseInt(this.form.dateTo.substr(0, 2)) - parseInt(this.form.dateFrom.substr(0, 2)) + 1;
      console.log("display : ", this.dispaly);
    }
  }

  ngOnInit() {
    $(".datatable1").show();
    $(".tableCompare").show();
    $(".tableCompare2Year").show();

    $("#table1").addClass('active');
  }

  ngAfterViewInit() {
    this.datatable(this.form);
    this.datatable2();
    //this.tableCompare();
  }

  activeTable(e) {
    if (e == 1) {
      $(".datatable1").show();
      $(".tableCompare").hide();
      $(".tableCompare2Year").hide();

      $("#table1").addClass('active');
      $("#table2").removeClass('active');
      $("#table3").removeClass('active');
    }
    if (e == 2) {
      $(".datatable1").hide();
      $(".tableCompare").show();
      $(".tableCompare2Year").hide();

      $("#table1").removeClass('active');
      $("#table2").addClass('active');
      $("#table3").removeClass('active');
    }
    if (e == 3) {
      $(".datatable1").hide();
      $(".tableCompare").hide();
      $(".tableCompare2Year").show();

      $("#table1").removeClass('active');
      $("#table2").removeClass('active');
      $("#table3").addClass('active');
    }
    this.myService.activeTable(e);
  }

  datatable(form) {

    this.myService.dataTaxNonpay(form).then((res) => {
      this.myService.dataTableCompareList(form).then((res) => {
        this.myService.datatable();
        this.loading = false;
        this.tableCompare();
        this.tableCompare2Year();
        $(".tableCompare").hide();
        $(".tableCompare2Year").hide();
      })
    });
  }

  tableCompare() {
    this.myService.tableCompare();
  }
  tableCompare2Year() {
    this.myService.tableCompare2Year();
  }
  save() {
    this.messege.comfirm((res) => {
      if (res) {
        this.myService.save().then(res => {
          this.messege.successModal("ทำรายสำเร็จ");
        }, err => {
          this.messege.errorModal("ทำรายการไม่สำเร็จ");
        })
      }
    }, "บันทึกรายการ");

  }
  datatable2() {
    this.myService.datatable2();
  }

  toggleBar() {
    if (this.toggle) {
      this.toggle = false;
    } else {
      this.toggle = true;
    }
  }

}
