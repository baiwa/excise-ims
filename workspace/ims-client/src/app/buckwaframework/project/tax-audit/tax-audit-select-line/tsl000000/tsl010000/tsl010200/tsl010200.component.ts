import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Tsl010200Service } from './tsl010200.service';
import { BreadCrumb } from 'models/breadcrumb';
import { AuthService } from 'services/auth.service';
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
  month : number = 7;
  toggle: boolean = true;
  constructor(
    private myService: Tsl010200Service,
    private authService: AuthService
  ) {
    this.authService.reRenderVersionProgram('TSL-010200');    
  }

  ngOnInit() {    
  }

  ngAfterViewInit() {
    this.datatable(this.month);
  }

  add(){
    this.month++;
    this.datatable(this.month);
  }

  datatable(month){
    this.myService.createTableHeader(month).then(res=>{
      this.myService.createTableBody(month).then(res=>{
        this.myService.datatable();
      })
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
