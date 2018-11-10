import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Ope0451Service } from './ope04-5-2.service';
import { BreadCrumb } from 'models/breadcrumb';
import { Router, ActivatedRoute } from '@angular/router';
import { Utils } from 'helpers/utils';
declare var $: any;
@Component({
  selector: 'app-ope04-5-2',
  templateUrl: './ope04-5-2.component.html',
  styleUrls: ['./ope04-5-2.component.css'],
  providers: [Ope0451Service]

})
export class Ope0452Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบกระดาษทำการจ่ายสินค้าสำเร็จรูป', route: '#' },
  ]

  taTaxReduceWsHdrId: string = "";

  constructor(
    private myService: Ope0451Service,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.taTaxReduceWsHdrId = this.route.snapshot.queryParams["taTaxReduceWsHdrId"];
    if (Utils.isNull(this.taTaxReduceWsHdrId)) this.router.navigate(['/ope04/6-2']);
    console.log(this.taTaxReduceWsHdrId);
    this.datatable(this.taTaxReduceWsHdrId);
  }
  datatable = (taTaxReduceWsHdrId: any) => {
    this.myService.datatable(taTaxReduceWsHdrId);
  }
}
