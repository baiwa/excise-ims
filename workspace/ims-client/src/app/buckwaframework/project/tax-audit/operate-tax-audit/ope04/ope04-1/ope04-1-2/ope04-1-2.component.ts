import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope0412Service } from './ope04-1-2.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Utils } from 'helpers/utils';
declare var $: any;
@Component({
  selector: 'app-ope04-1-2',
  templateUrl: './ope04-1-2.component.html',
  styleUrls: ['./ope04-1-2.component.css'],
  providers:[Ope0412Service]
})
export class Ope0412Component implements OnInit {
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
    private ope0412Service: Ope0412Service,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.id = this.route.snapshot.queryParams["id"];
    if (Utils.isNull(this.id)) this.router.navigate(['/ope04/1-1']);
   }

  ngOnInit() {        
    this.datatable(this.id);
  }

  claer = () => {
  }

  datatable = (id: any) => {
    this.ope0412Service.datatable(id);
  }
}
