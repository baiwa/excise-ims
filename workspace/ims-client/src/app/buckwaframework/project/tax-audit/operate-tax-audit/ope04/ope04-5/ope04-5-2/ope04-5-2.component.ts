import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Ope0452Service } from './ope04-5-2.service';
import { BreadCrumb } from 'models/breadcrumb';
import { Router, ActivatedRoute } from '@angular/router';
import { Utils } from 'helpers/utils';
declare var $: any;
@Component({
  selector: 'app-ope04-5-2',
  templateUrl: './ope04-5-2.component.html',
  styleUrls: ['./ope04-5-2.component.css'],
  providers: [Ope0452Service]

})
export class Ope0452Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบกระดาษทำการจ่ายสินค้าสำเร็จรูป', route: '#' },
  ]

  id: string = "";

  constructor(
    private myService: Ope0452Service,
    private router: Router,
    private route: ActivatedRoute,
  ) { 
    this.id = this.route.snapshot.queryParams["id"];
    if (Utils.isNull(this.id)) this.router.navigate(['/ope04/5-1']);    
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
   
    this.datatable(this.id);
  }
  datatable = (id: any) => {
    this.myService.datatable(id);
  }
}
