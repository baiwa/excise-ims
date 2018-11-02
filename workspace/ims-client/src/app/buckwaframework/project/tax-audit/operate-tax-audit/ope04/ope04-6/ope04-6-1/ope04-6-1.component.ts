import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope0461Service } from './ope04-6-1.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Utils } from 'helpers/utils';


declare var $: any;
@Component({
  selector: 'app-ope04-6-1',
  templateUrl: './ope04-6-1.component.html',
  styleUrls: ['./ope04-6-1.component.css'],
  providers: [Ope0461Service]
})
export class Ope0461Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบรายการวัตถุดิบที่ขอลดหย่อนภาษี', route: '#' },
  ]

  // ==> params
  formControl: FormGroup;
  taTaxReduceWsHdrId: string = "";
  constructor(
    private ope0461Service: Ope0461Service,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.taTaxReduceWsHdrId = this.route.snapshot.queryParams["taTaxReduceWsHdrId"];
    if (Utils.isNull(this.taTaxReduceWsHdrId)) this.router.navigate(['/ope04/6-2']);
    console.log(this.taTaxReduceWsHdrId);
    this.datatable(this.taTaxReduceWsHdrId);
  }

  claer = () => {
  }
  back(){
    this.router.navigate(['/ope04/6-2']);
  }
  datatable = (taTaxReduceWsHdrId: any) => {
    this.ope0461Service.datatable(taTaxReduceWsHdrId);
  }
}
