import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Ope0422Service } from './ope04-2-2.service';
import { Utils } from 'helpers/utils';
import { Router, ActivatedRoute } from '@angular/router';
declare var $: any;
@Component({
  selector: 'app-ope04-2-2',
  templateUrl: './ope04-2-2.component.html',
  styleUrls: ['./ope04-2-2.component.css'],
  providers : [Ope0422Service]
})
export class Ope0422Component implements OnInit {
  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบกระดาษทำการรับวัตถุดิบ', route: '#' },
  ]

  // ==> params
  formControl: FormGroup;
  id: string = "";
  constructor(
    private myService: Ope0422Service,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.id = this.route.snapshot.queryParams["id"];
    if (Utils.isNull(this.id)) this.router.navigate(['/ope04/2-1']);
   }

  ngOnInit() {        
    this.datatable(this.id);
  }

  claer = () => {
  }

  datatable = (id: any) => {
    this.myService.datatable(id);
  }
}
