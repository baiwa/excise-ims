import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Tsl010200Service } from './tsl010200.service';
import { BreadCrumb } from 'models/breadcrumb';
import { AuthService } from 'services/auth.service';
import { IaService } from 'services/ia.service';
import { TextDateTH } from 'helpers/datepicker';

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
  toggle: boolean = false;
  loading: boolean = true;
  constructor(
    private objService: IaService,
    private myService: Tsl010200Service,
    private authService: AuthService
  ) {
    this.authService.reRenderVersionProgram('TSL-010200');
    this.form = this.objService.getData();
    console.log("form : ", this.form);

    this.dispaly.dateFrom = TextDateTH.months[parseInt(this.form.dateFrom.substr(0, 2))];
    this.dispaly.dateTo = TextDateTH.months[parseInt(this.form.dateTo.substr(0, 2))];
    this.dispaly.month = parseInt(this.form.dateTo.substr(0, 2)) - parseInt(this.form.dateFrom.substr(0, 2)) + 1;
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.datatable(this.form);
  }

  datatable(form) {

    this.myService.dataTaxNonpay(form).then((res) => {
      this.myService.datatable();
      //   this.myService.createTableHeader(form).then(res => {
      //     this.myService.createTableBody(res).then(res => {
      this.loading = false;

      //     })
      //   });
    });

  }
  toggleBar() {
    if (this.toggle) {
      this.toggle = false;
    } else {
      this.toggle = true;
    }
  }

}
