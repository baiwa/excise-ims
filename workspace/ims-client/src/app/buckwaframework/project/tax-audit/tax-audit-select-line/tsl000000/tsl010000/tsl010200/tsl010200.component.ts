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
  constructor(
    private objService: IaService,
    private myService: Tsl010200Service,
    private authService: AuthService,
    private messege : MessageBarService,
    private router : Router
  ) {
    this.authService.reRenderVersionProgram('TSL-010200');
    this.form = this.objService.getData();
    console.log("form : ", this.form);

    if(this.form == null) {
      this.router.navigate(['/tax-audit-select-line/tsl0101-00']);
    }

    this.dispaly.dateFrom = TextDateTH.months[parseInt(this.form.dateFrom.substr(0, 2))];
    this.dispaly.dateTo = TextDateTH.months[parseInt(this.form.dateTo.substr(0, 2))];
    this.dispaly.month = parseInt(this.form.dateTo.substr(0, 2)) - parseInt(this.form.dateFrom.substr(0, 2)) + 1;
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.datatable(this.form);
    this.datatable2();
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
  save(){
    this.messege.comfirm((res)=>{
      if(res){
        this.myService.save().then(res=>{
          this.messege.successModal("ทำรายสำเร็จ");
        },err=>{
          this.messege.errorModal("ทำรายการไม่สำเร็จ");
        })
      }
    },"บันทึกรายการ");
   
  }
  datatable2(){
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
